package com.recetas.recetas_api.modules.follow.entity;

import com.recetas.recetas_api.modules.user.entity.User;
import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
public class Follow {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "seguidor_id", nullable = false)
    private User seguidor;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "seguido_id", nullable = false)
    private User seguido;

    private LocalDateTime fecha;

    public Follow() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public User getSeguidor() {
        return seguidor;
    }

    public void setSeguidor(User seguidor) {
        this.seguidor = seguidor;
    }

    public User getSeguido() {
        return seguido;
    }

    public void setSeguido(User seguido) {
        this.seguido = seguido;
    }

    public LocalDateTime getFecha() {
        return fecha;
    }

    public void setFecha(LocalDateTime fecha) {
        this.fecha = fecha;
    }
}