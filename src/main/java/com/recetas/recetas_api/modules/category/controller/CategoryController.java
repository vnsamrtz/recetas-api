package com.recetas.recetas_api.modules.category.controller;

import com.recetas.recetas_api.modules.category.entity.Category;
import com.recetas.recetas_api.modules.category.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/categories")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    @GetMapping
    public List<Category> obtenerTodas() {
        return categoryService.obtenerTodas();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Category> obtenerPorId(@PathVariable Long id) {
        return categoryService.obtenerPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public Category crear(@RequestBody Category category) {
        return categoryService.guardar(category);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        categoryService.eliminar(id);
        return ResponseEntity.noContent().build();
    }
}