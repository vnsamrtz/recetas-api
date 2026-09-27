package com.recetas.recetas_api.modules.block.controller;

import com.recetas.recetas_api.modules.block.entity.Block;
import com.recetas.recetas_api.modules.user.entity.User;
import com.recetas.recetas_api.shared.security.CurrentUserProvider;
import com.recetas.recetas_api.modules.block.service.BlockService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/block")
public class BlockController {

    @Autowired
    private BlockService blockService;

    @Autowired
    private CurrentUserProvider currentUserProvider;

    @GetMapping
    public List<Block> obtenerBloqueados() {
        User usuario = currentUserProvider.obtenerUsuarioActual();
        return blockService.obtenerBloqueados(usuario.getId());
    }

    @PostMapping("/{bloqueadoId}")
    public ResponseEntity<Void> bloquear(@PathVariable Long bloqueadoId) {
        User usuario = currentUserProvider.obtenerUsuarioActual();
        blockService.bloquear(usuario, bloqueadoId);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{bloqueadoId}")
    public ResponseEntity<Void> desbloquear(@PathVariable Long bloqueadoId) {
        User usuario = currentUserProvider.obtenerUsuarioActual();
        blockService.desbloquear(usuario, bloqueadoId);
        return ResponseEntity.noContent().build();
    }
}