package com.recetas.recetas_api.modules.recipe.entity;

import com.recetas.recetas_api.modules.recipeingredient.entity.RecipeIngredient;
import com.recetas.recetas_api.modules.recipestep.entity.RecipeStep;
import com.recetas.recetas_api.modules.tag.entity.Tag;
import com.recetas.recetas_api.modules.user.entity.User;
import com.recetas.recetas_api.modules.category.entity.Category;
import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import jakarta.persistence.CascadeType;

@Entity
public class Recipe {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String titulo;

    @Column(columnDefinition = "TEXT")
    private String descripcion;

    private Integer tiempoPreparacion;

    private Integer tiempoCoccion;

    public Integer getTiempoReposo() {
        return tiempoReposo;
    }

    public void setTiempoReposo(Integer tiempoReposo) {
        this.tiempoReposo = tiempoReposo;
    }

    private Integer tiempoReposo;

    private Integer raciones;

    private String dificultad;

    @Column(nullable = false)
    private Boolean publicada = false;

    private LocalDateTime fechaCreacion;

    private String imagenPrincipal;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User autor;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id")
    private Category categoria;

    @ManyToMany
    @JoinTable(
            name = "recipe_tags",
            joinColumns = @JoinColumn(name = "recipe_id"),
            inverseJoinColumns = @JoinColumn(name = "tag_id")
    )
    private Set<Tag> etiquetas = new HashSet<>();

    @OneToMany(mappedBy = "receta", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    private List<RecipeStep> pasos = new ArrayList<>();

    @OneToMany(mappedBy = "receta", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    private List<RecipeIngredient> ingredientes = new ArrayList<>();

    public Recipe() {
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

    public User getAutor() {
        return autor;
    }

    public void setAutor(User autor) {
        this.autor = autor;
    }

    public Category getCategoria() {
        return categoria;
    }

    public void setCategoria(Category categoria) {
        this.categoria = categoria;
    }

    public Set<Tag> getEtiquetas() {
        return etiquetas;
    }

    public void setEtiquetas(Set<Tag> etiquetas) {
        this.etiquetas = etiquetas;
    }

    public List<RecipeStep> getPasos() {
        return pasos;
    }

    public void setPasos(List<RecipeStep> pasos) {
        this.pasos = pasos;
    }

    public List<RecipeIngredient> getIngredientes() {
        return ingredientes;
    }

    public void setIngredientes(List<RecipeIngredient> ingredientes) {
        this.ingredientes = ingredientes;
    }
}