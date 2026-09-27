package com.recetas.recetas_api.modules.recipe.controller.dto;

import java.time.LocalDateTime;

public class RecipeResponseDTO {

    private Long id;
    private String titulo;
    private String descripcion;
    private Integer tiempoPreparacion;
    private Integer tiempoCoccion;
    private Integer tiempoReposo;
    private Integer raciones;
    private String dificultad;
    private Boolean publicada;
    private LocalDateTime fechaCreacion;
    private String imagenPrincipal;
    private Long autorId;
    private String autorNombre;
    private Long categoriaId;
    private String categoriaNombre;

    public RecipeResponseDTO() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public Integer getTiempoReposo() {
        return tiempoReposo;
    }

    public void setTiempoReposo(Integer tiempoReposo) {
        this.tiempoReposo = tiempoReposo;
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

    public LocalDateTime getFechaCreacion() {
        return fechaCreacion;
    }

    public void setFechaCreacion(LocalDateTime fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
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

    public String getAutorNombre() {
        return autorNombre;
    }

    public void setAutorNombre(String autorNombre) {
        this.autorNombre = autorNombre;
    }

    public Long getCategoriaId() {
        return categoriaId;
    }

    public void setCategoriaId(Long categoriaId) {
        this.categoriaId = categoriaId;
    }

    public String getCategoriaNombre() {
        return categoriaNombre;
    }

    public void setCategoriaNombre(String categoriaNombre) {
        this.categoriaNombre = categoriaNombre;
    }
}