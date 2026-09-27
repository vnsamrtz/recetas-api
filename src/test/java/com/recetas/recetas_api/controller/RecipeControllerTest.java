package com.recetas.recetas_api.controller;

import com.recetas.recetas_api.modules.recipe.controller.dto.RecipeResponseDTO;
import com.recetas.recetas_api.modules.recipe.controller.RecipeController;
import com.recetas.recetas_api.shared.security.CurrentUserProvider;
import com.recetas.recetas_api.shared.security.JwtService;
import com.recetas.recetas_api.shared.security.UserDetailsServiceImpl;
import com.recetas.recetas_api.modules.recipe.service.RecipeService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.webmvc.test.autoconfigure.WebMvcTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(RecipeController.class)
class RecipeControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private RecipeService recipeService;

    @MockitoBean
    private CurrentUserProvider currentUserProvider;

    @MockitoBean
    private JwtService jwtService;

    @MockitoBean
    private UserDetailsServiceImpl userDetailsService;

    @Test
    @WithMockUser
    void obtenerPublicas_devuelveListaConCodigo200() throws Exception {
        RecipeResponseDTO dto = new RecipeResponseDTO();
        dto.setId(1L);
        dto.setTitulo("Tortilla");

        when(recipeService.obtenerPublicas()).thenReturn(List.of(dto));

        mockMvc.perform(get("/recipes"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].titulo").value("Tortilla"));
    }
}