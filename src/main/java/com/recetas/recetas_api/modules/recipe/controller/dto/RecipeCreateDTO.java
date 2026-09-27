package com.recetas.recetas_api.modules.recipe.controller.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;

public class RecipeCreateDTO {

    @NotBlank(message = "El título es obligatorio")
    private String titulo;

    private String descripcion;

    @Min(value = 1, message = "El tiempo de preparación debe ser mayor que 0")
    private Integer tiempoPreparacion;

    @Min(value = 1, message = "El tiempo de cocción debe ser mayor que 0")
    private Integer tiempoCoccion;

    public Integer getTiempoReposo() {
        return tiempoReposo;
    }

    public void setTiempoReposo(Integer tiempoReposo) {
        this.tiempoReposo = tiempoReposo;
    }

    @Min(value = 1, message = "El tiempo de reposo debe ser mayor que 0")
    private Integer tiempoReposo;

    @Min(value = 1, message = "Las raciones deben ser al menos 1")
    private Integer raciones;

    private String dificultad;

    private Boolean publicada;

    private String imagenPrincipal;

    private Long autorId;

    private Long categoriaId;

    public RecipeCreateDTO() {
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Integer getTiempoPreparacion() {
        return tiempoPreparacion;
    }

    public void setTiempoPreparacion(Integer tiempoPreparacion) {
        this.tiempoPreparacion = tiempoPreparacion;
    }

    public Integer getTiempoCoccion() {
        return tiempoCoccion;
    }

    public void setTiempoCoccion(Integer tiempoCoccion) {
        this.tiempoCoccion = tiempoCoccion;
    }

    public Integer getRaciones() {
        return raciones;
    }

    public void setRaciones(Integer raciones) {
        this.raciones = raciones;
    }

    public String getDificultad() {
        return dificultad;
    }

    public void setDificultad(String dificultad) {
        this.dificultad = dificultad;
    }

    public Boolean getPublicada() {
        return publicada;
    }

    public void setPublicada(Boolean publicada) {
        this.publicada = publicada;
    }

    public String getImagenPrincipal() {
        return imagenPrincipal;
    }

    public void setImagenPrincipal(String imagenPrincipal) {
        this.imagenPrincipal = imagenPrincipal;
    }

    public Long getAutorId() {
        return autorId;
    }

    public void setAutorId(Long autorId) {
        this.autorId = autorId;
    }

    public Long getCategoriaId() {
        return categoriaId;
    }

    public void setCategoriaId(Long categoriaId) {
        this.categoriaId = categoriaId;
    }
}