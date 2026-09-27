package com.recetas.recetas_api.modules.recipestep.repository;

import com.recetas.recetas_api.modules.recipestep.entity.RecipeStep;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RecipeStepRepository extends JpaRepository<RecipeStep, Long> {

    List<RecipeStep> findByRecetaIdOrderByNumeroOrdenAsc(Long recetaId);
}