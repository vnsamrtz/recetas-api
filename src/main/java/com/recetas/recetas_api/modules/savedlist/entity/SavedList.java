package com.recetas.recetas_api.modules.savedlist.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.recetas.recetas_api.modules.user.entity.User;
import com.recetas.recetas_api.modules.recipelist.entity.RecipeList;
import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
public class SavedList {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User usuario;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "recipe_list_id", nullable = false)
    @JsonIgnoreProperties({"propietario"})
    private RecipeList lista;

    private LocalDateTime fechaGuardado;

    public SavedList() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public User getUsuario() {
        return usuario;
    }

    public void setUsuario(User usuario) {
        this.usuario = usuario;
    }

    public RecipeList getLista() {
        return lista;
    }

    public void setLista(RecipeList lista) {
        this.lista = lista;
    }

    public LocalDateTime getFechaGuardado() {
        return fechaGuardado;
    }

    public void setFechaGuardado(LocalDateTime fechaGuardado) {
        this.fechaGuardado = fechaGuardado;
    }
}