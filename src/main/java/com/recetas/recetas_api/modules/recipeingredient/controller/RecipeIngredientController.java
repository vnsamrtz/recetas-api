package com.recetas.recetas_api.modules.recipeingredient.controller;

import com.recetas.recetas_api.modules.recipeingredient.entity.RecipeIngredient;
import com.recetas.recetas_api.modules.recipeingredient.service.RecipeIngredientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/recipes/{recipeId}/ingredients")
public class RecipeIngredientController {

    @Autowired
    private RecipeIngredientService recipeIngredientService;

    @GetMapping
    public List<RecipeIngredient> obtenerPorReceta(@PathVariable Long recipeId) {
        return recipeIngredientService.obtenerPorReceta(recipeId);
    }

    @PostMapping
    public RecipeIngredient crear(@RequestBody RecipeIngredient recipeIngredient) {
        return recipeIngredientService.guardar(recipeIngredient);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long recipeId, @PathVariable Long id) {
        recipeIngredientService.eliminar(id);
        return ResponseEntity.noContent().build();
    }
}