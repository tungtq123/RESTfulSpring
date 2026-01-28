package com.tqt.restfulspring.controller;

import com.tqt.restfulspring.dto.ProductDTO;
import com.tqt.restfulspring.service.ProductService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/products")
@Tag(name = "Product API", description = "Product API")
public class ProductController {
    @Autowired
    private ProductService productService;

    @GetMapping("")
    public ResponseEntity<List<ProductDTO>> getAllProducts() {
        List<ProductDTO> products = productService.getAllProducts();
        return ResponseEntity.ok(products);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductDTO> getProductById(@PathVariable Long id) {
        ProductDTO product = productService.getProductById(id);
        return product != null ? ResponseEntity.ok(product) : ResponseEntity.notFound().build();
    }

    @PostMapping
    public ResponseEntity<ProductDTO> createProduct(@RequestBody ProductDTO productDTO) {
        ProductDTO createdProduct = productService.createProduct(productDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdProduct);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProductDTO> updateProduct(@PathVariable Long id, @RequestBody ProductDTO productDTO) {
        ProductDTO existingProduct = productService.updateProduct(id, productDTO);
        if (existingProduct != null) {
            return ResponseEntity.ok(existingProduct);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProduct(@PathVariable Long id) {
        int result = productService.deleteProduct(id);
        if (result > 0) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
