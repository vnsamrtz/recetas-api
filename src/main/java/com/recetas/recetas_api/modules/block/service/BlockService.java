package com.recetas.recetas_api.modules.block.service;

import com.recetas.recetas_api.modules.block.entity.Block;
import com.recetas.recetas_api.modules.user.entity.User;
import com.recetas.recetas_api.shared.exception.ResourceNotFoundException;
import com.recetas.recetas_api.modules.block.repository.BlockRepository;
import com.recetas.recetas_api.modules.follow.repository.FollowRepository;
import com.recetas.recetas_api.modules.user.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class BlockService {

    @Autowired
    private BlockRepository blockRepository;

    @Autowired
    private FollowRepository followRepository;

    @Autowired
    private UserRepository userRepository;

    public List<Block> obtenerBloqueados(Long usuarioId) {
        return blockRepository.findByBloqueadorId(usuarioId);
    }

    public Block bloquear(User bloqueador, Long bloqueadoId) {
        if (bloqueador.getId().equals(bloqueadoId)) {
            throw new RuntimeException("No puedes bloquearte a ti mismo");
        }

        User bloqueado = userRepository.findById(bloqueadoId)
                .orElseThrow(() -> new ResourceNotFoundException("Usuario no encontrado con id: " + bloqueadoId));

        followRepository.findBySeguidorIdAndSeguidoId(bloqueador.getId(), bloqueadoId)
                .ifPresent(followRepository::delete);
        followRepository.findBySeguidorIdAndSeguidoId(bloqueadoId, bloqueador.getId())
                .ifPresent(followRepository::delete);

        Block block = new Block();
        block.setBloqueador(bloqueador);
        block.setBloqueado(bloqueado);
        block.setFecha(LocalDateTime.now());

        return blockRepository.save(block);
    }

    public void desbloquear(User bloqueador, Long bloqueadoId) {
        blockRepository.findByBloqueadorIdAndBloqueadoId(bloqueador.getId(), bloqueadoId)
                .ifPresent(blockRepository::delete);
    }
}