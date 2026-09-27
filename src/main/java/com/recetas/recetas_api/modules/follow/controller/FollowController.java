package com.recetas.recetas_api.modules.follow.controller;

import com.recetas.recetas_api.modules.follow.entity.Follow;
import com.recetas.recetas_api.modules.user.entity.User;
import com.recetas.recetas_api.shared.security.CurrentUserProvider;
import com.recetas.recetas_api.modules.follow.service.FollowService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/follow")
public class FollowController {

    @Autowired
    private FollowService followService;

    @Autowired
    private CurrentUserProvider currentUserProvider;

    @GetMapping("/{usuarioId}/seguidos")
    public List<Follow> obtenerSeguidos(@PathVariable Long usuarioId) {
        return followService.obtenerSeguidos(usuarioId);
    }

    @GetMapping("/{usuarioId}/seguidores")
    public List<Follow> obtenerSeguidores(@PathVariable Long usuarioId) {
        return followService.obtenerSeguidores(usuarioId);
    }

    @PostMapping("/{seguidoId}")
    public Follow seguir(@PathVariable Long seguidoId) {
        User usuario = currentUserProvider.obtenerUsuarioActual();
        return followService.seguir(usuario, seguidoId);
    }

    @DeleteMapping("/{seguidoId}")
    public ResponseEntity<Void> dejarDeSeguir(@PathVariable Long seguidoId) {
        User usuario = currentUserProvider.obtenerUsuarioActual();
        followService.dejarDeSeguir(usuario, seguidoId);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/seguidores/{seguidorId}")
    public ResponseEntity<Void> eliminarSeguidor(@PathVariable Long seguidorId) {
        User usuario = currentUserProvider.obtenerUsuarioActual();
        followService.eliminarSeguidor(usuario, seguidorId);
        return ResponseEntity.noContent().build();
    }
}