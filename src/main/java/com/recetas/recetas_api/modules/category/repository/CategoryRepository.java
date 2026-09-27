package com.recetas.recetas_api.modules.category.repository;

import com.recetas.recetas_api.modules.category.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, Long> {
}