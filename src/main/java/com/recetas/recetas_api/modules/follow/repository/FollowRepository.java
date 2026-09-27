package com.recetas.recetas_api.modules.follow.repository;

import com.recetas.recetas_api.modules.follow.entity.Follow;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface FollowRepository extends JpaRepository<Follow, Long> {
    List<Follow> findBySeguidorId(Long seguidorId);
    List<Follow> findBySeguidoId(Long seguidoId);
    Optional<Follow> findBySeguidorIdAndSeguidoId(Long seguidorId, Long seguidoId);
}