package com.recetas.recetas_api.modules.recipelist.repository;

import com.recetas.recetas_api.modules.recipelist.entity.RecipeList;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface RecipeListRepository extends JpaRepository<RecipeList, Long> {
    List<RecipeList> findByPropietarioId(Long propietarioId);
    List<RecipeList> findByPropietarioIdAndPublicaTrue(Long propietarioId);
    Optional<RecipeList> findByPropietarioIdAndEsFavoritosTrue(Long propietarioId);
}