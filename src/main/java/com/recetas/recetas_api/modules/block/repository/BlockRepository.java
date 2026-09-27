package com.recetas.recetas_api.modules.block.repository;

import com.recetas.recetas_api.modules.block.entity.Block;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface BlockRepository extends JpaRepository<Block, Long> {
    Optional<Block> findByBloqueadorIdAndBloqueadoId(Long bloqueadorId, Long bloqueadoId);
    boolean existsByBloqueadorIdAndBloqueadoId(Long bloqueadorId, Long bloqueadoId);
    List<Block> findByBloqueadorId(Long bloqueadorId);
    List<Block> findByBloqueadoId(Long bloqueadoId);
}