package com.recetas.recetas_api.modules.recipe.repository;

import com.recetas.recetas_api.modules.recipe.entity.Recipe;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface RecipeRepository extends JpaRepository<Recipe, Long> {

    List<Recipe> findByPublicadaTrue();

    List<Recipe> findByAutorId(Long autorId);

    List<Recipe> findByAutorIdAndPublicadaTrue(Long autorId);

    List<Recipe> findByPublicadaTrueOrderByFechaCreacionDesc();

    @Query("SELECT r FROM Recipe r WHERE r.publicada = true " +
            "AND (:titulo IS NULL OR LOWER(r.titulo) LIKE LOWER(CONCAT('%', :titulo, '%'))) " +
            "AND (:categoriaId IS NULL OR r.categoria.id = :categoriaId) " +
            "AND (:dificultad IS NULL OR r.dificultad = :dificultad)")
    List<Recipe> buscar(@Param("titulo") String titulo,
                        @Param("categoriaId") Long categoriaId,
                        @Param("dificultad") String dificultad);
}