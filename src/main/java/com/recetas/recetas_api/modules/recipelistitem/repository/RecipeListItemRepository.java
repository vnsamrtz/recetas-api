package com.recetas.recetas_api.modules.recipelistitem.repository;

import com.recetas.recetas_api.modules.recipelistitem.entity.RecipeListItem;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface RecipeListItemRepository extends JpaRepository<RecipeListItem, Long> {
    List<RecipeListItem> findByListaIdOrderByOrdenAsc(Long listaId);
    Optional<RecipeListItem> findByListaIdAndRecetaId(Long listaId, Long recetaId);
    List<RecipeListItem> findByRecetaId(Long recetaId);
}