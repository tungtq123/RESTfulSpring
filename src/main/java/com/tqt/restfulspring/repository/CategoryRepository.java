package com.tqt.restfulspring.repository;

import com.tqt.restfulspring.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category,Long> {
}
