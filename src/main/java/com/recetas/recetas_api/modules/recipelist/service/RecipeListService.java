package com.recetas.recetas_api.modules.recipelist.service;

import com.recetas.recetas_api.modules.recipe.entity.Recipe;
import com.recetas.recetas_api.modules.recipelist.entity.RecipeList;
import com.recetas.recetas_api.modules.recipelistitem.entity.RecipeListItem;
import com.recetas.recetas_api.modules.user.entity.User;
import com.recetas.recetas_api.shared.exception.ResourceNotFoundException;
import com.recetas.recetas_api.modules.recipelistitem.repository.RecipeListItemRepository;
import com.recetas.recetas_api.modules.recipelist.repository.RecipeListRepository;
import com.recetas.recetas_api.modules.recipe.repository.RecipeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class RecipeListService {

    @Autowired
    private RecipeListRepository recipeListRepository;

    @Autowired
    private RecipeListItemRepository recipeListItemRepository;

    @Autowired
    private RecipeRepository recipeRepository;

    public RecipeList obtenerPorId(Long id) {
        return recipeListRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Lista no encontrada con id: " + id));
    }

    public List<RecipeList> obtenerPorUsuario(Long usuarioId) {
        return recipeListRepository.findByPropietarioId(usuarioId);
    }

    public List<RecipeList> obtenerPublicasPorUsuario(Long usuarioId) {
        return recipeListRepository.findByPropietarioIdAndPublicaTrue(usuarioId);
    }

    public RecipeList crear(RecipeList lista) {
        return recipeListRepository.save(lista);
    }

    public RecipeList actualizar(Long id, RecipeList datos) {
        RecipeList lista = obtenerPorId(id);

        lista.setNombre(datos.getNombre());
        lista.setPublica(datos.getPublica());

        return recipeListRepository.save(lista);
    }

    public void eliminar(Long id) {
        recipeListRepository.deleteById(id);
    }

    public RecipeList crearListaFavoritos(User usuario) {
        RecipeList favoritos = new RecipeList();
        favoritos.setNombre("Favoritos");
        favoritos.setPublica(false);
        favoritos.setEsFavoritos(true);
        favoritos.setPropietario(usuario);
        return recipeListRepository.save(favoritos);
    }

    public List<RecipeListItem> obtenerItemsDeLista(Long listaId) {
        return recipeListItemRepository.findByListaIdOrderByOrdenAsc(listaId);
    }

    public RecipeListItem anadirReceta(Long listaId, Long recetaId, Integer orden) {
        RecipeList lista = obtenerPorId(listaId);

        Recipe receta = recipeRepository.findById(recetaId)
                .orElseThrow(() -> new ResourceNotFoundException("Receta no encontrada con id: " + recetaId));

        RecipeListItem item = new RecipeListItem();
        item.setLista(lista);
        item.setReceta(receta);
        item.setOrden(orden);
        item.setFechaAgregado(LocalDateTime.now());

        return recipeListItemRepository.save(item);
    }

    public void quitarReceta(Long itemId) {
        recipeListItemRepository.deleteById(itemId);
    }

    public void reordenar(Long listaId, List<Long> itemIdsEnOrden) {
        List<RecipeListItem> items = recipeListItemRepository.findByListaIdOrderByOrdenAsc(listaId);

        for (int i = 0; i < itemIdsEnOrden.size(); i++) {
            Long itemId = itemIdsEnOrden.get(i);
            int nuevoOrden = i;

            for (RecipeListItem item : items) {
                if (item.getId().equals(itemId)) {
                    item.setOrden(nuevoOrden);
                    recipeListItemRepository.save(item);
                    break;
                }
            }
        }
    }
}