package com.recetas.recetas_api.modules.ingredient.service;

import com.recetas.recetas_api.modules.ingredient.entity.Ingredient;
import com.recetas.recetas_api.modules.ingredient.repository.IngredientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class IngredientService {

    @Autowired
    private IngredientRepository ingredientRepository;

    public List<Ingredient> obtenerTodos() {
        return ingredientRepository.findAll();
    }

    public Optional<Ingredient> obtenerPorId(Long id) {
        return ingredientRepository.findById(id);
    }

    public Ingredient guardar(Ingredient ingredient) {
        return ingredientRepository.save(ingredient);
    }

    public void eliminar(Long id) {
        ingredientRepository.deleteById(id);
    }
}