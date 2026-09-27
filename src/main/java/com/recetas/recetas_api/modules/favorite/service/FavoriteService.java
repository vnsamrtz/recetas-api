package com.recetas.recetas_api.modules.favorite.service;

import com.recetas.recetas_api.modules.recipelist.entity.RecipeList;
import com.recetas.recetas_api.modules.user.entity.User;
import com.recetas.recetas_api.modules.recipelistitem.repository.RecipeListItemRepository;
import com.recetas.recetas_api.modules.recipelist.repository.RecipeListRepository;
import com.recetas.recetas_api.modules.recipelist.service.RecipeListService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FavoriteService {

    @Autowired
    private RecipeListRepository recipeListRepository;

    @Autowired
    private RecipeListItemRepository recipeListItemRepository;

    @Autowired
    private RecipeListService recipeListService;

    public void marcarFavorito(User usuario, Long recetaId) {
        RecipeList listaFavoritos = obtenerOCrearListaFavoritos(usuario);

        boolean yaEsta = recipeListItemRepository
                .findByListaIdAndRecetaId(listaFavoritos.getId(), recetaId)
                .isPresent();

        if (yaEsta) {
            return;
        }

        recipeListService.anadirReceta(listaFavoritos.getId(), recetaId, null);
    }

    public void quitarFavorito(User usuario, Long recetaId) {
        RecipeList listaFavoritos = obtenerOCrearListaFavoritos(usuario);

        recipeListItemRepository
                .findByListaIdAndRecetaId(listaFavoritos.getId(), recetaId)
                .ifPresent(item -> recipeListService.quitarReceta(item.getId()));
    }

    private RecipeList obtenerOCrearListaFavoritos(User usuario) {
        return recipeListRepository.findByPropietarioIdAndEsFavoritosTrue(usuario.getId())
                .orElseGet(() -> recipeListService.crearListaFavoritos(usuario));
    }
}