package com.recetas.recetas_api.modules.auth.controller.dto;

public class AuthResponseDTO {

    private String token;
    private Long userId;
    private String nombre;
    private String email;

    public AuthResponseDTO(String token, Long userId, String nombre, String email) {
        this.token = token;
        this.userId = userId;
        this.nombre = nombre;
        this.email = email;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}