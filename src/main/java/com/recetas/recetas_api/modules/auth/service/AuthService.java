package com.recetas.recetas_api.modules.auth.service;

import com.recetas.recetas_api.modules.auth.controller.dto.AuthResponseDTO;
import com.recetas.recetas_api.modules.auth.controller.dto.LoginDTO;
import com.recetas.recetas_api.modules.auth.controller.dto.RegisterDTO;
import com.recetas.recetas_api.modules.user.entity.User;
import com.recetas.recetas_api.modules.recipelist.service.RecipeListService;
import com.recetas.recetas_api.modules.user.repository.UserRepository;
import com.recetas.recetas_api.shared.security.JwtService;
import com.recetas.recetas_api.shared.security.UserDetailsImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class AuthService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private JwtService jwtService;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private RecipeListService recipeListService;

    public AuthResponseDTO registrar(RegisterDTO dto) {
        if (userRepository.findByEmail(dto.getEmail()).isPresent()) {
            throw new RuntimeException("Ya existe un usuario con ese email");
        }
        if (userRepository.findByNombre(dto.getNombre()).isPresent()) {
            throw new RuntimeException("Ya existe un usuario con ese nombre");
        }

        User user = new User();
        user.setNombre(dto.getNombre());
        user.setEmail(dto.getEmail());
        user.setPassword(passwordEncoder.encode(dto.getPassword()));
        user.setFechaRegistro(LocalDateTime.now());

        User guardado = userRepository.save(user);

        recipeListService.crearListaFavoritos(guardado);

        UserDetailsImpl userDetails = new UserDetailsImpl(guardado);
        String token = jwtService.generarToken(userDetails);

        return new AuthResponseDTO(token, guardado.getId(), guardado.getNombre(), guardado.getEmail());
    }

    public AuthResponseDTO login(LoginDTO dto) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(dto.getEmail(), dto.getPassword())
        );

        User user = userRepository.findByEmail(dto.getEmail())
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));

        UserDetailsImpl userDetails = new UserDetailsImpl(user);
        String token = jwtService.generarToken(userDetails);

        return new AuthResponseDTO(token, user.getId(), user.getNombre(), user.getEmail());
    }
}