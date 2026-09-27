package com.recetas.recetas_api.modules.ingredient.repository;

import com.recetas.recetas_api.modules.ingredient.entity.Ingredient;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IngredientRepository extends JpaRepository<Ingredient, Long> {
}