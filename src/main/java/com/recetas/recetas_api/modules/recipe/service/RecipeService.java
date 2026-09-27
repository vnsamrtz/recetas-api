package com.recetas.recetas_api.modules.recipe.service;

import com.recetas.recetas_api.modules.recipe.controller.dto.RecipeCreateDTO;
import com.recetas.recetas_api.modules.recipe.controller.dto.RecipeResponseDTO;
import com.recetas.recetas_api.modules.category.entity.Category;
import com.recetas.recetas_api.modules.recipe.entity.Recipe;
import com.recetas.recetas_api.modules.user.entity.User;
import com.recetas.recetas_api.shared.exception.ResourceNotFoundException;
import com.recetas.recetas_api.modules.recipe.mapper.RecipeMapper;
import com.recetas.recetas_api.modules.category.repository.CategoryRepository;
import com.recetas.recetas_api.modules.filestorage.service.FileStorageService;
import com.recetas.recetas_api.modules.recipe.repository.RecipeRepository;
import com.recetas.recetas_api.modules.recipeingredient.repository.RecipeIngredientRepository;
import com.recetas.recetas_api.modules.recipestep.repository.RecipeStepRepository;
import com.recetas.recetas_api.modules.recipelistitem.repository.RecipeListItemRepository;
import com.recetas.recetas_api.modules.user.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class RecipeService {

    @Autowired
    private RecipeRepository recipeRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private RecipeMapper recipeMapper;

    @Autowired
    private FileStorageService fileStorageService;

    @Autowired
    private RecipeIngredientRepository recipeIngredientRepository;

    @Autowired
    private RecipeStepRepository recipeStepRepository;

    @Autowired
    private RecipeListItemRepository recipeListItemRepository;

    public List<RecipeResponseDTO> obtenerTodas() {
        return recipeRepository.findAll()
                .stream()
                .map(recipeMapper::toDTO)
                .collect(Collectors.toList());
    }

    public List<RecipeResponseDTO> obtenerPublicas() {
        return recipeRepository.findByPublicadaTrue()
                .stream()
                .map(recipeMapper::toDTO)
                .collect(Collectors.toList());
    }

    public List<RecipeResponseDTO> obtenerMisRecetas(User usuario) {
        return recipeRepository.findByAutorId(usuario.getId())
                .stream()
                .map(recipeMapper::toDTO)
                .collect(Collectors.toList());
    }

    public List<RecipeResponseDTO> buscar(String titulo, Long categoriaId, String dificultad) {
        return recipeRepository.buscar(titulo, categoriaId, dificultad)
                .stream()
                .map(recipeMapper::toDTO)
                .collect(Collectors.toList());
    }

    public Optional<RecipeResponseDTO> obtenerPorId(Long id) {
        return recipeRepository.findById(id)
                .map(recipeMapper::toDTO);
    }

    public RecipeResponseDTO crear(RecipeCreateDTO dto, User autor) {
        Category categoria = null;
        if (dto.getCategoriaId() != null) {
            categoria = categoryRepository.findById(dto.getCategoriaId())
                    .orElseThrow(() -> new ResourceNotFoundException("Categoría no encontrada con id: " + dto.getCategoriaId()));
        }

        Recipe recipe = recipeMapper.toEntity(dto, autor, categoria);
        Recipe guardada = recipeRepository.save(recipe);

        return recipeMapper.toDTO(guardada);
    }

    public RecipeResponseDTO actualizar(Long id, RecipeCreateDTO dto) {
        Recipe recipe = recipeRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Receta no encontrada con id: " + id));

        Category categoria = null;
        if (dto.getCategoriaId() != null) {
            categoria = categoryRepository.findById(dto.getCategoriaId())
                    .orElseThrow(() -> new ResourceNotFoundException("Categoría no encontrada con id: " + dto.getCategoriaId()));
        }

        recipe.setTitulo(dto.getTitulo());
        recipe.setDescripcion(dto.getDescripcion());
        recipe.setTiempoPreparacion(dto.getTiempoPreparacion());
        recipe.setTiempoCoccion(dto.getTiempoCoccion());
        recipe.setRaciones(dto.getRaciones());
        recipe.setDificultad(dto.getDificultad());
        recipe.setPublicada(dto.getPublicada());
        recipe.setCategoria(categoria);

        Recipe actualizada = recipeRepository.save(recipe);
        return recipeMapper.toDTO(actualizada);
    }

    @Transactional
    public void eliminar(Long id, User usuarioActual) {
        Recipe recipe = recipeRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Receta no encontrada con id: " + id));

        if (!recipe.getAutor().getId().equals(usuarioActual.getId())) {
            throw new RuntimeException("No tienes permiso para eliminar esta receta");
        }

        recipeListItemRepository.deleteAll(recipeListItemRepository.findByRecetaId(id));
        recipeIngredientRepository.deleteAll(recipeIngredientRepository.findByRecetaIdOrderByOrdenAsc(id));
        recipeStepRepository.deleteAll(recipeStepRepository.findByRecetaIdOrderByNumeroOrdenAsc(id));

        recipeRepository.delete(recipe);
    }

    public RecipeResponseDTO actualizarImagen(Long id, MultipartFile archivo) {
        Recipe recipe = recipeRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Receta no encontrada con id: " + id));

        String nombreArchivo = fileStorageService.guardarArchivo(archivo);
        recipe.setImagenPrincipal("/uploads/" + nombreArchivo);

        Recipe actualizada = recipeRepository.save(recipe);
        return recipeMapper.toDTO(actualizada);
    }

    public List<RecipeResponseDTO> obtenerRecientes() {
        return recipeRepository.findByPublicadaTrueOrderByFechaCreacionDesc().stream()
                .map(recipeMapper::toDTO)
                .collect(Collectors.toList());
    }
}