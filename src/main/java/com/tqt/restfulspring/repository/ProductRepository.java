package com.tqt.restfulspring.repository;

import com.tqt.restfulspring.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product,Long> {
}
