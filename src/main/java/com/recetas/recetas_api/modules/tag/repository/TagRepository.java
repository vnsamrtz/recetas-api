package com.recetas.recetas_api.modules.tag.repository;

import com.recetas.recetas_api.modules.tag.entity.Tag;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TagRepository extends JpaRepository<Tag, Long> {
}