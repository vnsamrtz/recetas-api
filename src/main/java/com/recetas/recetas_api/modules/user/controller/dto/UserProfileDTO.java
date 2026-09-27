package com.recetas.recetas_api.modules.user.controller.dto;

import com.recetas.recetas_api.modules.recipe.controller.dto.RecipeResponseDTO;
import com.recetas.recetas_api.modules.recipelist.entity.RecipeList;

import java.util.List;

public class UserProfileDTO {

    private Long id;
    private String nombre;
    private String fotoPerfil;
    private String biografia;
    private List<RecipeResponseDTO> recetas;
    private List<RecipeList> listas;
    private int totalSeguidores;
    private int totalSeguidos;
    private boolean esPropio;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getFotoPerfil() {
        return fotoPerfil;
    }

    public void setFotoPerfil(String fotoPerfil) {
        this.fotoPerfil = fotoPerfil;
    }

    public String getBiografia() {
        return biografia;
    }

    public void setBiografia(String biografia) {
        this.biografia = biografia;
    }

    public List<RecipeResponseDTO> getRecetas() {
        return recetas;
    }

    public void setRecetas(List<RecipeResponseDTO> recetas) {
        this.recetas = recetas;
    }

    public List<RecipeList> getListas() {
        return listas;
    }

    public void setListas(List<RecipeList> listas) {
        this.listas = listas;
    }

    public int getTotalSeguidores() {
        return totalSeguidores;
    }

    public void setTotalSeguidores(int totalSeguidores) {
        this.totalSeguidores = totalSeguidores;
    }

    public int getTotalSeguidos() {
        return totalSeguidos;
    }

    public void setTotalSeguidos(int totalSeguidos) {
        this.totalSeguidos = totalSeguidos;
    }

    public boolean isEsPropio() {
        return esPropio;
    }

    public void setEsPropio(boolean esPropio) {
        this.esPropio = esPropio;
    }
}