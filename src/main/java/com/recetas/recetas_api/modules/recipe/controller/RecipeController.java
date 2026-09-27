package com.recetas.recetas_api.modules.recipe.controller;

import com.recetas.recetas_api.modules.recipe.controller.dto.RecipeCreateDTO;
import com.recetas.recetas_api.modules.recipe.controller.dto.RecipeResponseDTO;
import com.recetas.recetas_api.modules.user.entity.User;
import com.recetas.recetas_api.shared.security.CurrentUserProvider;
import com.recetas.recetas_api.modules.recipe.service.RecipeService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/recipes")
public class RecipeController {

    @Autowired
    private RecipeService recipeService;

    @Autowired
    private CurrentUserProvider currentUserProvider;

    @GetMapping
    public List<RecipeResponseDTO> obtenerPublicas() {
        return recipeService.obtenerPublicas();
    }

    @GetMapping("/mis-recetas")
    public List<RecipeResponseDTO> obtenerMisRecetas() {
        User usuario = currentUserProvider.obtenerUsuarioActual();
        return recipeService.obtenerMisRecetas(usuario);
    }

    @GetMapping("/buscar")
    public List<RecipeResponseDTO> buscar(
            @RequestParam(required = false) String titulo,
            @RequestParam(required = false) Long categoriaId,
            @RequestParam(required = false) String dificultad) {
        return recipeService.buscar(titulo, categoriaId, dificultad);
    }

    @GetMapping("/{id}")
    public ResponseEntity<RecipeResponseDTO> obtenerPorId(@PathVariable Long id) {
        return recipeService.obtenerPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/recientes")
    public List<RecipeResponseDTO> obtenerRecientes() {
        return recipeService.obtenerRecientes();
    }

    @PostMapping
    public RecipeResponseDTO crear(@Valid @RequestBody RecipeCreateDTO dto) {
        User autor = currentUserProvider.obtenerUsuarioActual();
        return recipeService.crear(dto, autor);
    }

    @PutMapping("/{id}")
    public RecipeResponseDTO actualizar(@PathVariable Long id, @Valid @RequestBody RecipeCreateDTO dto) {
        return recipeService.actualizar(id, dto);
    }

    @PostMapping("/{id}/imagen")
    public RecipeResponseDTO subirImagen(@PathVariable Long id, @RequestParam("archivo") MultipartFile archivo) {
        return recipeService.actualizarImagen(id, archivo);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        User usuario = currentUserProvider.obtenerUsuarioActual();
        recipeService.eliminar(id, usuario);
        return ResponseEntity.noContent().build();
    }
}