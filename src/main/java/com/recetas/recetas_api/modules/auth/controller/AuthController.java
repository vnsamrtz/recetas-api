package com.recetas.recetas_api.modules.auth.controller;

import com.recetas.recetas_api.modules.auth.controller.dto.AuthResponseDTO;
import com.recetas.recetas_api.modules.auth.controller.dto.LoginDTO;
import com.recetas.recetas_api.modules.auth.controller.dto.RegisterDTO;
import com.recetas.recetas_api.modules.auth.service.AuthService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private AuthService authService;

    @PostMapping("/register")
    public AuthResponseDTO registrar(@Valid @RequestBody RegisterDTO dto) {
        return authService.registrar(dto);
    }

    @PostMapping("/login")
    public AuthResponseDTO login(@Valid @RequestBody LoginDTO dto) {
        return authService.login(dto);
    }
}