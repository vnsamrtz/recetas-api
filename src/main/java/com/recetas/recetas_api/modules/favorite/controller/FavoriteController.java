package com.recetas.recetas_api.modules.favorite.controller;

import com.recetas.recetas_api.modules.user.entity.User;
import com.recetas.recetas_api.shared.security.CurrentUserProvider;
import com.recetas.recetas_api.modules.favorite.service.FavoriteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/recipes/{recipeId}/favorite")
public class FavoriteController {

    @Autowired
    private FavoriteService favoriteService;

    @Autowired
    private CurrentUserProvider currentUserProvider;

    @PostMapping
    public ResponseEntity<Void> marcarFavorito(@PathVariable Long recipeId) {
        User usuario = currentUserProvider.obtenerUsuarioActual();
        favoriteService.marcarFavorito(usuario, recipeId);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping
    public ResponseEntity<Void> quitarFavorito(@PathVariable Long recipeId) {
        User usuario = currentUserProvider.obtenerUsuarioActual();
        favoriteService.quitarFavorito(usuario, recipeId);
        return ResponseEntity.noContent().build();
    }
}