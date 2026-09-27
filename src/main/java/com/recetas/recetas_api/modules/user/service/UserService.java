package com.recetas.recetas_api.modules.user.service;

import com.recetas.recetas_api.modules.recipe.entity.Recipe;
import com.recetas.recetas_api.modules.recipelist.entity.RecipeList;
import com.recetas.recetas_api.modules.user.entity.User;
import com.recetas.recetas_api.shared.exception.ResourceNotFoundException;
import com.recetas.recetas_api.modules.block.repository.BlockRepository;
import com.recetas.recetas_api.modules.follow.repository.FollowRepository;
import com.recetas.recetas_api.modules.recipe.repository.RecipeRepository;
import com.recetas.recetas_api.modules.recipeingredient.repository.RecipeIngredientRepository;
import com.recetas.recetas_api.modules.recipelist.repository.RecipeListRepository;
import com.recetas.recetas_api.modules.recipelistitem.repository.RecipeListItemRepository;
import com.recetas.recetas_api.modules.recipestep.repository.RecipeStepRepository;
import com.recetas.recetas_api.modules.savedlist.repository.SavedListRepository;
import com.recetas.recetas_api.modules.user.repository.UserRepository;
import com.recetas.recetas_api.modules.filestorage.service.FileStorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private FileStorageService fileStorageService;

    @Autowired
    private RecipeRepository recipeRepository;

    @Autowired
    private RecipeIngredientRepository recipeIngredientRepository;

    @Autowired
    private RecipeStepRepository recipeStepRepository;

    @Autowired
    private RecipeListRepository recipeListRepository;

    @Autowired
    private RecipeListItemRepository recipeListItemRepository;

    @Autowired
    private SavedListRepository savedListRepository;

    @Autowired
    private FollowRepository followRepository;

    @Autowired
    private BlockRepository blockRepository;

    public List<User> obtenerTodos() {
        return userRepository.findAll();
    }

    public Optional<User> obtenerPorId(Long id) {
        return userRepository.findById(id);
    }

    public User guardar(User user) {
        return userRepository.save(user);
    }

    public User actualizarFotoPerfil(Long id, MultipartFile archivo) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Usuario no encontrado con id: " + id));

        String nombreArchivo = fileStorageService.guardarArchivo(archivo);
        user.setFotoPerfil("/uploads/" + nombreArchivo);

        return userRepository.save(user);
    }

    public User actualizarBiografia(Long id, String biografia) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Usuario no encontrado con id: " + id));

        user.setBiografia(biografia);

        return userRepository.save(user);
    }

    public User actualizarNombre(Long id, String nombre) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Usuario no encontrado con id: " + id));

        userRepository.findByNombre(nombre).ifPresent(existente -> {
            if (!existente.getId().equals(id)) {
                throw new RuntimeException("Ya existe un usuario con ese nombre");
            }
        });

        user.setNombre(nombre);
        return userRepository.save(user);
    }

    @Transactional
    public void eliminar(Long id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Usuario no encontrado con id: " + id));

        // 1. Relaciones sociales que apuntan a este usuario en cualquier sentido
        followRepository.deleteAll(followRepository.findBySeguidorId(id));
        followRepository.deleteAll(followRepository.findBySeguidoId(id));
        blockRepository.deleteAll(blockRepository.findByBloqueadorId(id));
        blockRepository.deleteAll(blockRepository.findByBloqueadoId(id));
        savedListRepository.deleteAll(savedListRepository.findByUsuarioId(id));

        // 2. Recetas del usuario: primero lo que depende de cada receta
        List<Recipe> recetas = recipeRepository.findByAutorId(id);
        for (Recipe receta : recetas) {
            recipeListItemRepository.deleteAll(recipeListItemRepository.findByRecetaId(receta.getId()));
            recipeIngredientRepository.deleteAll(recipeIngredientRepository.findByRecetaIdOrderByOrdenAsc(receta.getId()));
            recipeStepRepository.deleteAll(recipeStepRepository.findByRecetaIdOrderByNumeroOrdenAsc(receta.getId()));
        }
        recipeRepository.deleteAll(recetas);

        // 3. Listas del usuario: primero lo que depende de cada lista
        List<RecipeList> listas = recipeListRepository.findByPropietarioId(id);
        for (RecipeList lista : listas) {
            savedListRepository.deleteAll(savedListRepository.findByListaId(lista.getId()));
            recipeListItemRepository.deleteAll(recipeListItemRepository.findByListaIdOrderByOrdenAsc(lista.getId()));
        }
        recipeListRepository.deleteAll(listas);

        // 4. Por último, el propio usuario
        userRepository.delete(user);
    }

    public List<User> buscarPorNombre(String nombre) {
        return userRepository.findByNombreContainingIgnoreCase(nombre);
    }
}