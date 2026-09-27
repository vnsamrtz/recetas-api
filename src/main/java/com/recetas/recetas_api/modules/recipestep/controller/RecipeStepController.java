package com.recetas.recetas_api.modules.recipestep.controller;

import com.recetas.recetas_api.modules.recipestep.entity.RecipeStep;
import com.recetas.recetas_api.modules.recipestep.service.RecipeStepService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/recipes/{recipeId}/steps")
public class RecipeStepController {

    @Autowired
    private RecipeStepService recipeStepService;

    @GetMapping
    public List<RecipeStep> obtenerPorReceta(@PathVariable Long recipeId) {
        return recipeStepService.obtenerPorReceta(recipeId);
    }

    @PostMapping
    public RecipeStep crear(@RequestBody RecipeStep recipeStep) {
        return recipeStepService.guardar(recipeStep);
    }

    @PostMapping("/{stepId}/imagen")
    public RecipeStep subirImagen(@PathVariable Long recipeId, @PathVariable Long stepId,
                                  @RequestParam("archivo") MultipartFile archivo) {
        return recipeStepService.actualizarImagen(stepId, archivo);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long recipeId, @PathVariable Long id) {
        recipeStepService.eliminar(id);
        return ResponseEntity.noContent().build();
    }
}