package com.recetas.recetas_api.modules.follow.service;

import com.recetas.recetas_api.modules.follow.entity.Follow;
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
public class FollowService {

    @Autowired
    private FollowRepository followRepository;

    @Autowired
    private BlockRepository blockRepository;

    @Autowired
    private UserRepository userRepository;

    public List<Follow> obtenerSeguidos(Long usuarioId) {
        return followRepository.findBySeguidorId(usuarioId);
    }

    public List<Follow> obtenerSeguidores(Long usuarioId) {
        return followRepository.findBySeguidoId(usuarioId);
    }

    public Follow seguir(User seguidor, Long seguidoId) {
        if (seguidor.getId().equals(seguidoId)) {
            throw new RuntimeException("No puedes seguirte a ti mismo");
        }

        if (blockRepository.existsByBloqueadorIdAndBloqueadoId(seguidoId, seguidor.getId())) {
            throw new RuntimeException("No puedes seguir a este usuario");
        }

        boolean yaLoSigue = followRepository
                .findBySeguidorIdAndSeguidoId(seguidor.getId(), seguidoId)
                .isPresent();

        if (yaLoSigue) {
            throw new RuntimeException("Ya sigues a este usuario");
        }

        User seguido = userRepository.findById(seguidoId)
                .orElseThrow(() -> new ResourceNotFoundException("Usuario no encontrado con id: " + seguidoId));

        Follow follow = new Follow();
        follow.setSeguidor(seguidor);
        follow.setSeguido(seguido);
        follow.setFecha(LocalDateTime.now());

        return followRepository.save(follow);
    }

    public void dejarDeSeguir(User seguidor, Long seguidoId) {
        followRepository.findBySeguidorIdAndSeguidoId(seguidor.getId(), seguidoId)
                .ifPresent(followRepository::delete);
    }

    public void eliminarSeguidor(User usuario, Long seguidorId) {
        followRepository.findBySeguidorIdAndSeguidoId(seguidorId, usuario.getId())
                .ifPresent(followRepository::delete);
    }
}