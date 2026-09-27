package com.recetas.recetas_api.modules.tag.service;

import com.recetas.recetas_api.modules.tag.entity.Tag;
import com.recetas.recetas_api.modules.tag.repository.TagRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TagService {

    @Autowired
    private TagRepository tagRepository;

    public List<Tag> obtenerTodas() {
        return tagRepository.findAll();
    }

    public Optional<Tag> obtenerPorId(Long id) {
        return tagRepository.findById(id);
    }

    public Tag guardar(Tag tag) {
        return tagRepository.save(tag);
    }

    public void eliminar(Long id) {
        tagRepository.deleteById(id);
    }
}