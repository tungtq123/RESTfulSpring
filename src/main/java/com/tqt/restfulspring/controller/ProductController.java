package com.tqt.restfulspring.controller;

import com.tqt.restfulspring.dto.ProductDTO;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.PostConstruct;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/products")
@Tag(name = "Product API", description = "Product API")
public class ProductController {

    private List<ProductDTO> products = new ArrayList<>();

    @PostConstruct
    public void init(){
        products.add(new ProductDTO(1L, "Laptop", 1200.00, "Powerful laptop for work and gaming"));
        products.add(new ProductDTO(2L, "Mouse", 25, "Wireless optical mouse"));
        products.add(new ProductDTO(3L, "Keyboard", 75, "Mechanical keyboard with lightning"));
    }

    @GetMapping("")
    public ResponseEntity<List<ProductDTO>> getAllProducts(){
        return ResponseEntity.ok(products);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductDTO> getProductById(@PathVariable Long id){
        Optional<ProductDTO> productDTO = products.stream().filter(p -> p.getId() == id).findFirst();
        return productDTO.map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<ProductDTO> createProduct(@RequestBody ProductDTO productDTO){
        long newId = products.size() + 1;
        productDTO.setId(newId);
        products.add(productDTO);
        return ResponseEntity.ok(productDTO);
    }

    @PutMapping("/{id}")
    public ResponseEntity<List<ProductDTO>> updateProduct(@PathVariable Long id, @RequestBody ProductDTO productDTO){
        Optional<ProductDTO> product = products.stream().filter(p -> p.getId() == id).findFirst();
        product.ifPresent(p -> {
            p.setName(productDTO.getName());
            p.setPrice(productDTO.getPrice());
            p.setDescription(productDTO.getDescription());
        });
        return ResponseEntity.ok(products);
    }

}
