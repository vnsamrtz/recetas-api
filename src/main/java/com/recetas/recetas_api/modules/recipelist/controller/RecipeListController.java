package com.recetas.recetas_api.modules.recipelist.controller;

import com.recetas.recetas_api.modules.recipelist.entity.RecipeList;
import com.recetas.recetas_api.modules.recipelistitem.entity.RecipeListItem;
import com.recetas.recetas_api.modules.recipelist.service.RecipeListService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/lists")
public class RecipeListController {

    @Autowired
    private RecipeListService recipeListService;

    @GetMapping("/{id}")
    public RecipeList obtenerPorId(@PathVariable Long id) {
        return recipeListService.obtenerPorId(id);
    }

    @GetMapping("/usuario/{usuarioId}")
    public List<RecipeList> obtenerPorUsuario(@PathVariable Long usuarioId) {
        return recipeListService.obtenerPorUsuario(usuarioId);
    }

    @GetMapping("/usuario/{usuarioId}/publicas")
    public List<RecipeList> obtenerPublicasPorUsuario(@PathVariable Long usuarioId) {
        return recipeListService.obtenerPublicasPorUsuario(usuarioId);
    }

    @PostMapping
    public RecipeList crear(@RequestBody RecipeList lista) {
        return recipeListService.crear(lista);
    }

    @PutMapping("/{id}")
    public RecipeList actualizar(@PathVariable Long id, @RequestBody RecipeList datos) {
        return recipeListService.actualizar(id, datos);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        recipeListService.eliminar(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{listaId}/recetas")
    public List<RecipeListItem> obtenerItems(@PathVariable Long listaId) {
        return recipeListService.obtenerItemsDeLista(listaId);
    }

    @PostMapping("/{listaId}/recetas/{recetaId}")
    public RecipeListItem anadirReceta(@PathVariable Long listaId, @PathVariable Long recetaId,
                                       @RequestParam(required = false) Integer orden) {
        return recipeListService.anadirReceta(listaId, recetaId, orden);
    }

    @DeleteMapping("/recetas/{itemId}")
    public ResponseEntity<Void> quitarReceta(@PathVariable Long itemId) {
        recipeListService.quitarReceta(itemId);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{listaId}/recetas/reordenar")
    public void reordenar(@PathVariable Long listaId, @RequestBody List<Long> itemIdsEnOrden) {
        recipeListService.reordenar(listaId, itemIdsEnOrden);
    }
}