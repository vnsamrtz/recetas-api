package com.recetas.recetas_api.modules.profile.controller;

import com.recetas.recetas_api.modules.user.controller.dto.UserProfileDTO;
import com.recetas.recetas_api.modules.user.entity.User;
import com.recetas.recetas_api.shared.security.CurrentUserProvider;
import com.recetas.recetas_api.modules.profile.service.ProfileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
public class ProfileController {

    @Autowired
    private ProfileService profileService;

    @Autowired
    private CurrentUserProvider currentUserProvider;

    @GetMapping("/{id}/perfil")
    public UserProfileDTO obtenerPerfil(@PathVariable Long id) {
        User solicitante = currentUserProvider.obtenerUsuarioActual();
        return profileService.obtenerPerfil(id, solicitante.getId());
    }
}