package com.recetas.recetas_api.modules.savedlist.service;

import com.recetas.recetas_api.modules.recipelist.entity.RecipeList;
import com.recetas.recetas_api.modules.savedlist.entity.SavedList;
import com.recetas.recetas_api.modules.user.entity.User;
import com.recetas.recetas_api.shared.exception.ResourceNotFoundException;
import com.recetas.recetas_api.modules.recipelist.repository.RecipeListRepository;
import com.recetas.recetas_api.modules.savedlist.repository.SavedListRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class SavedListService {

    @Autowired
    private SavedListRepository savedListRepository;

    @Autowired
    private RecipeListRepository recipeListRepository;

    public List<SavedList> obtenerGuardadasPorUsuario(Long usuarioId) {
        return savedListRepository.findByUsuarioId(usuarioId);
    }

    public SavedList guardarLista(User usuario, Long listaId) {
        boolean yaGuardada = savedListRepository
                .findByUsuarioIdAndListaId(usuario.getId(), listaId)
                .isPresent();

        if (yaGuardada) {
            return savedListRepository.findByUsuarioIdAndListaId(usuario.getId(), listaId).get();
        }

        RecipeList lista = recipeListRepository.findById(listaId)
                .orElseThrow(() -> new ResourceNotFoundException("Lista no encontrada con id: " + listaId));

        if (!lista.getPublica()) {
            throw new RuntimeException("No se puede guardar una lista privada");
        }

        SavedList savedList = new SavedList();
        savedList.setUsuario(usuario);
        savedList.setLista(lista);
        savedList.setFechaGuardado(LocalDateTime.now());

        return savedListRepository.save(savedList);
    }

    public void quitarListaGuardada(User usuario, Long listaId) {
        savedListRepository.findByUsuarioIdAndListaId(usuario.getId(), listaId)
                .ifPresent(savedListRepository::delete);
    }
}