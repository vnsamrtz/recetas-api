package com.recetas.recetas_api.modules.user.repository;

import com.recetas.recetas_api.modules.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByEmail(String email);
    Optional<User> findByNombre(String nombre);
    List<User> findByNombreContainingIgnoreCase(String nombre);
}