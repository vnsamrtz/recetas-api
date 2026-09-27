package com.recetas.recetas_api.modules.recipeingredient.service;

import com.recetas.recetas_api.modules.recipeingredient.entity.RecipeIngredient;
import com.recetas.recetas_api.modules.recipeingredient.repository.RecipeIngredientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RecipeIngredientService {

    @Autowired
    private RecipeIngredientRepository recipeIngredientRepository;

    public List<RecipeIngredient> obtenerPorReceta(Long recetaId) {
        return recipeIngredientRepository.findByRecetaIdOrderByOrdenAsc(recetaId);
    }

    public RecipeIngredient guardar(RecipeIngredient recipeIngredient) {
        return recipeIngredientRepository.save(recipeIngredient);
    }

    public void eliminar(Long id) {
        recipeIngredientRepository.deleteById(id);
    }
}