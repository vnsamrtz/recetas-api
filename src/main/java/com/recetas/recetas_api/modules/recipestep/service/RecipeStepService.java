package com.recetas.recetas_api.modules.recipestep.service;

import com.recetas.recetas_api.modules.recipestep.entity.RecipeStep;
import com.recetas.recetas_api.shared.exception.ResourceNotFoundException;
import com.recetas.recetas_api.modules.filestorage.service.FileStorageService;
import com.recetas.recetas_api.modules.recipestep.repository.RecipeStepRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Service
public class RecipeStepService {

    @Autowired
    private RecipeStepRepository recipeStepRepository;

    @Autowired
    private FileStorageService fileStorageService;

    public List<RecipeStep> obtenerPorReceta(Long recetaId) {
        return recipeStepRepository.findByRecetaIdOrderByNumeroOrdenAsc(recetaId);
    }

    public RecipeStep guardar(RecipeStep recipeStep) {
        return recipeStepRepository.save(recipeStep);
    }

    public void eliminar(Long id) {
        recipeStepRepository.deleteById(id);
    }

    public RecipeStep actualizarImagen(Long id, MultipartFile archivo) {
        RecipeStep step = recipeStepRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Paso no encontrado con id: " + id));

        String nombreArchivo = fileStorageService.guardarArchivo(archivo);
        step.setImagen("/uploads/" + nombreArchivo);

        return recipeStepRepository.save(step);
    }
}