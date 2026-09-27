package com.recetas.recetas_api.modules.profile.service;

import com.recetas.recetas_api.modules.recipe.controller.dto.RecipeResponseDTO;
import com.recetas.recetas_api.modules.user.controller.dto.UserProfileDTO;
import com.recetas.recetas_api.modules.recipe.entity.Recipe;
import com.recetas.recetas_api.modules.recipelist.entity.RecipeList;
import com.recetas.recetas_api.modules.user.entity.User;
import com.recetas.recetas_api.shared.exception.ResourceNotFoundException;
import com.recetas.recetas_api.modules.recipe.mapper.RecipeMapper;
import com.recetas.recetas_api.modules.follow.repository.FollowRepository;
import com.recetas.recetas_api.modules.recipelist.repository.RecipeListRepository;
import com.recetas.recetas_api.modules.recipe.repository.RecipeRepository;
import com.recetas.recetas_api.modules.user.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProfileService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RecipeRepository recipeRepository;

    @Autowired
    private RecipeListRepository recipeListRepository;

    @Autowired
    private FollowRepository followRepository;

    @Autowired
    private RecipeMapper recipeMapper;

    public UserProfileDTO obtenerPerfil(Long usuarioId, Long solicitanteId) {
        User usuario = userRepository.findById(usuarioId)
                .orElseThrow(() -> new ResourceNotFoundException("Usuario no encontrado con id: " + usuarioId));

        boolean esPropio = usuarioId.equals(solicitanteId);

        List<Recipe> recetas = esPropio
                ? recipeRepository.findByAutorId(usuarioId)
                : recipeRepository.findByAutorIdAndPublicadaTrue(usuarioId);

        List<RecipeResponseDTO> recetasDTO = recetas.stream()
                .map(recipeMapper::toDTO)
                .collect(Collectors.toList());

        List<RecipeList> listas = esPropio
                ? recipeListRepository.findByPropietarioId(usuarioId)
                : recipeListRepository.findByPropietarioIdAndPublicaTrue(usuarioId);

        int totalSeguidores = followRepository.findBySeguidoId(usuarioId).size();
        int totalSeguidos = followRepository.findBySeguidorId(usuarioId).size();

        UserProfileDTO dto = new UserProfileDTO();
        dto.setId(usuario.getId());
        dto.setNombre(usuario.getNombre());
        dto.setFotoPerfil(usuario.getFotoPerfil());
        dto.setBiografia(usuario.getBiografia());
        dto.setRecetas(recetasDTO);
        dto.setListas(listas);
        dto.setTotalSeguidores(totalSeguidores);
        dto.setTotalSeguidos(totalSeguidos);
        dto.setEsPropio(esPropio);

        return dto;
    }
}