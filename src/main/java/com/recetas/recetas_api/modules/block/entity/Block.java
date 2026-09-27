package com.recetas.recetas_api.modules.block.entity;

import com.recetas.recetas_api.modules.user.entity.User;
import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
public class Block {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "bloqueador_id", nullable = false)
    private User bloqueador;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "bloqueado_id", nullable = false)
    private User bloqueado;

    private LocalDateTime fecha;

    public Block() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public User getBloqueador() {
        return bloqueador;
    }

    public void setBloqueador(User bloqueador) {
        this.bloqueador = bloqueador;
    }

    public User getBloqueado() {
        return bloqueado;
    }

    public void setBloqueado(User bloqueado) {
        this.bloqueado = bloqueado;
    }

    public LocalDateTime getFecha() {
        return fecha;
    }

    public void setFecha(LocalDateTime fecha) {
        this.fecha = fecha;
    }
}