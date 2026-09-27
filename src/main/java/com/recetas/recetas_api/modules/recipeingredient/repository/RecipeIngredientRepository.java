package com.recetas.recetas_api.modules.recipeingredient.repository;

import com.recetas.recetas_api.modules.recipeingredient.entity.RecipeIngredient;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RecipeIngredientRepository extends JpaRepository<RecipeIngredient, Long> {

    List<RecipeIngredient> findByRecetaIdOrderByOrdenAsc(Long recetaId);
}