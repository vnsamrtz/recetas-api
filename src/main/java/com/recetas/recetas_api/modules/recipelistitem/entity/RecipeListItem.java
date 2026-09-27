package com.recetas.recetas_api.modules.recipelistitem.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.recetas.recetas_api.modules.recipe.entity.Recipe;
import com.recetas.recetas_api.modules.recipelist.entity.RecipeList;
import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
public class RecipeListItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "recipe_list_id", nullable = false)
    @JsonIgnoreProperties({"propietario"})
    private RecipeList lista;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "recipe_id", nullable = false)
    private Recipe receta;

    private Integer orden;

    private LocalDateTime fechaAgregado;

    public RecipeListItem() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public RecipeList getLista() {
        return lista;
    }

    public void setLista(RecipeList lista) {
        this.lista = lista;
    }

    public Recipe getReceta() {
        return receta;
    }

    public void setReceta(Recipe receta) {
        this.receta = receta;
    }

    public Integer getOrden() {
        return orden;
    }

    public void setOrden(Integer orden) {
        this.orden = orden;
    }

    public LocalDateTime getFechaAgregado() {
        return fechaAgregado;
    }

    public void setFechaAgregado(LocalDateTime fechaAgregado) {
        this.fechaAgregado = fechaAgregado;
    }
}