package com.recetas.recetas_api.service;

import com.recetas.recetas_api.modules.recipe.controller.dto.RecipeResponseDTO;
import com.recetas.recetas_api.modules.recipe.entity.Recipe;
import com.recetas.recetas_api.modules.recipe.mapper.RecipeMapper;
import com.recetas.recetas_api.modules.recipe.repository.RecipeRepository;
import com.recetas.recetas_api.modules.recipe.service.RecipeService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class RecipeServiceTest {

    @Mock
    private RecipeRepository recipeRepository;

    @Mock
    private RecipeMapper recipeMapper;

    @InjectMocks
    private RecipeService recipeService;

    @Test
    void obtenerPorId_cuandoExiste_devuelveReceta() {
        Recipe recipe = new Recipe();
        recipe.setId(1L);
        recipe.setTitulo("Tortilla");

        RecipeResponseDTO dto = new RecipeResponseDTO();
        dto.setId(1L);
        dto.setTitulo("Tortilla");

        when(recipeRepository.findById(1L)).thenReturn(Optional.of(recipe));
        when(recipeMapper.toDTO(recipe)).thenReturn(dto);

        Optional<RecipeResponseDTO> resultado = recipeService.obtenerPorId(1L);

        assertTrue(resultado.isPresent());
        assertEquals("Tortilla", resultado.get().getTitulo());
    }

    @Test
    void obtenerPorId_cuandoNoExiste_devuelveVacio() {
        when(recipeRepository.findById(99L)).thenReturn(Optional.empty());

        Optional<RecipeResponseDTO> resultado = recipeService.obtenerPorId(99L);

        assertTrue(resultado.isEmpty());
    }

    @Test
    void obtenerPublicas_devuelveListaCorrectamente() {
        Recipe recipe1 = new Recipe();
        recipe1.setId(1L);
        Recipe recipe2 = new Recipe();
        recipe2.setId(2L);

        RecipeResponseDTO dto1 = new RecipeResponseDTO();
        dto1.setId(1L);
        RecipeResponseDTO dto2 = new RecipeResponseDTO();
        dto2.setId(2L);

        when(recipeRepository.findByPublicadaTrue()).thenReturn(List.of(recipe1, recipe2));
        when(recipeMapper.toDTO(recipe1)).thenReturn(dto1);
        when(recipeMapper.toDTO(recipe2)).thenReturn(dto2);

        List<RecipeResponseDTO> resultado = recipeService.obtenerPublicas();

        assertEquals(2, resultado.size());
    }
}