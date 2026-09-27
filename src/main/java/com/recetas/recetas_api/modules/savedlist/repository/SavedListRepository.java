package com.recetas.recetas_api.modules.savedlist.repository;

import com.recetas.recetas_api.modules.savedlist.entity.SavedList;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface SavedListRepository extends JpaRepository<SavedList, Long> {
    List<SavedList> findByUsuarioId(Long usuarioId);
    Optional<SavedList> findByUsuarioIdAndListaId(Long usuarioId, Long listaId);
    List<SavedList> findByListaId(Long listaId);
}