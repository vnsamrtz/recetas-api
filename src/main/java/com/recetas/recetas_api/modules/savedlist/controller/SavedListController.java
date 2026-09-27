package com.recetas.recetas_api.modules.savedlist.controller;

import com.recetas.recetas_api.modules.savedlist.entity.SavedList;
import com.recetas.recetas_api.modules.user.entity.User;
import com.recetas.recetas_api.shared.security.CurrentUserProvider;
import com.recetas.recetas_api.modules.savedlist.service.SavedListService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/saved-lists")
public class SavedListController {

    @Autowired
    private SavedListService savedListService;

    @Autowired
    private CurrentUserProvider currentUserProvider;

    @GetMapping
    public List<SavedList> obtenerMisGuardadas() {
        User usuario = currentUserProvider.obtenerUsuarioActual();
        return savedListService.obtenerGuardadasPorUsuario(usuario.getId());
    }

    @PostMapping("/{listaId}")
    public SavedList guardar(@PathVariable Long listaId) {
        User usuario = currentUserProvider.obtenerUsuarioActual();
        return savedListService.guardarLista(usuario, listaId);
    }

    @DeleteMapping("/{listaId}")
    public ResponseEntity<Void> quitar(@PathVariable Long listaId) {
        User usuario = currentUserProvider.obtenerUsuarioActual();
        savedListService.quitarListaGuardada(usuario, listaId);
        return ResponseEntity.noContent().build();
    }
}