package com.recetas.recetas_api.modules.recipe.mapper;

import com.recetas.recetas_api.modules.recipe.controller.dto.RecipeCreateDTO;
import com.recetas.recetas_api.modules.recipe.controller.dto.RecipeResponseDTO;
import com.recetas.recetas_api.modules.category.entity.Category;
import com.recetas.recetas_api.modules.recipe.entity.Recipe;
import com.recetas.recetas_api.modules.user.entity.User;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class RecipeMapper {

    public RecipeResponseDTO toDTO(Recipe recipe) {
        RecipeResponseDTO dto = new RecipeResponseDTO();

        dto.setId(recipe.getId());
        dto.setTitulo(recipe.getTitulo());
        dto.setDescripcion(recipe.getDescripcion());
        dto.setTiempoPreparacion(recipe.getTiempoPreparacion());
        dto.setTiempoCoccion(recipe.getTiempoCoccion());
        dto.setTiempoReposo(recipe.getTiempoReposo());
        dto.setRaciones(recipe.getRaciones());
        dto.setDificultad(recipe.getDificultad());
        dto.setPublicada(recipe.getPublicada());
        dto.setFechaCreacion(recipe.getFechaCreacion());
        dto.setImagenPrincipal(recipe.getImagenPrincipal());

        if (recipe.getAutor() != null) {
            dto.setAutorId(recipe.getAutor().getId());
            dto.setAutorNombre(recipe.getAutor().getNombre());
        }

        if (recipe.getCategoria() != null) {
            dto.setCategoriaId(recipe.getCategoria().getId());
            dto.setCategoriaNombre(recipe.getCategoria().getNombre());
        }

        return dto;
    }

    public Recipe toEntity(RecipeCreateDTO dto, User autor, Category categoria) {
        Recipe recipe = new Recipe();

        recipe.setTitulo(dto.getTitulo());
        recipe.setDescripcion(dto.getDescripcion());
        recipe.setTiempoPreparacion(dto.getTiempoPreparacion());
        recipe.setTiempoCoccion(dto.getTiempoCoccion());
        recipe.setTiempoReposo(dto.getTiempoReposo());
        recipe.setRaciones(dto.getRaciones());
        recipe.setDificultad(dto.getDificultad());
        recipe.setPublicada(dto.getPublicada() != null ? dto.getPublicada() : false);
        recipe.setImagenPrincipal(dto.getImagenPrincipal());
        recipe.setFechaCreacion(LocalDateTime.now());
        recipe.setAutor(autor);
        recipe.setCategoria(categoria);

        return recipe;
    }
}