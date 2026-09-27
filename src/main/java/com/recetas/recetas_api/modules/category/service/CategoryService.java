package com.recetas.recetas_api.modules.category.service;

import com.recetas.recetas_api.modules.category.entity.Category;
import com.recetas.recetas_api.modules.category.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;

    public List<Category> obtenerTodas() {
        return categoryRepository.findAll();
    }

    public Optional<Category> obtenerPorId(Long id) {
        return categoryRepository.findById(id);
    }

    public Category guardar(Category category) {
        return categoryRepository.save(category);
    }

    public void eliminar(Long id) {
        categoryRepository.deleteById(id);
    }
}