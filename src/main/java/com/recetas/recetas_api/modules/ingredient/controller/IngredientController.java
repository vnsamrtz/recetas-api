package com.recetas.recetas_api.modules.ingredient.controller;

import com.recetas.recetas_api.modules.ingredient.entity.Ingredient;
import com.recetas.recetas_api.modules.ingredient.service.IngredientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/ingredients")
public class IngredientController {

    @Autowired
    private IngredientService ingredientService;

    @GetMapping
    public List<Ingredient> obtenerTodos() {
        return ingredientService.obtenerTodos();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Ingredient> obtenerPorId(@PathVariable Long id) {
        return ingredientService.obtenerPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public Ingredient crear(@RequestBody Ingredient ingredient) {
        return ingredientService.guardar(ingredient);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        ingredientService.eliminar(id);
        return ResponseEntity.noContent().build();
    }
}