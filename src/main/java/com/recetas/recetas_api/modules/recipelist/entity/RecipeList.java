package com.recetas.recetas_api.modules.recipelist.entity;

import com.recetas.recetas_api.modules.user.entity.User;
import jakarta.persistence.*;

@Entity
public class RecipeList {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String nombre;

    @Column(nullable = false)
    private Boolean publica = false;

    @Column(nullable = false)
    private Boolean esFavoritos = false;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User propietario;

    public RecipeList() {
    }

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

    public Boolean getPublica() {
        return publica;
    }

    public void setPublica(Boolean publica) {
        this.publica = publica;
    }

    public Boolean getEsFavoritos() {
        return esFavoritos;
    }

    public void setEsFavoritos(Boolean esFavoritos) {
        this.esFavoritos = esFavoritos;
    }

    public User getPropietario() {
        return propietario;
    }

    public void setPropietario(User propietario) {
        this.propietario = propietario;
    }
}