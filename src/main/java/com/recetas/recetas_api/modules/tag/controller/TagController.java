package com.recetas.recetas_api.modules.tag.controller;

import com.recetas.recetas_api.modules.tag.entity.Tag;
import com.recetas.recetas_api.modules.tag.service.TagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/tags")
public class TagController {

    @Autowired
    private TagService tagService;

    @GetMapping
    public List<Tag> obtenerTodas() {
        return tagService.obtenerTodas();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Tag> obtenerPorId(@PathVariable Long id) {
        return tagService.obtenerPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public Tag crear(@RequestBody Tag tag) {
        return tagService.guardar(tag);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        tagService.eliminar(id);
        return ResponseEntity.noContent().build();
    }
}