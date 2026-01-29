package com.tqt.restfulspring.service.impl;

import com.tqt.restfulspring.dto.ProductDTO;
import com.tqt.restfulspring.entity.Category;
import com.tqt.restfulspring.entity.Product;
import com.tqt.restfulspring.repository.CategoryRepository;
import com.tqt.restfulspring.repository.ProductRepository;
import com.tqt.restfulspring.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository;

    @Override
    public ProductDTO getProductById(Long id) {
        Product product = productRepository.findById(id).orElse(null);
        if (product != null) {
            Category category = product.getCategory();
            return new ProductDTO(
                    product.getId(),
                    product.getName(),
                    product.getPrice(),
                    product.getDescription(),
                    category == null ? null : category.getId()
            );
        }
        return null;
    }

    @Override
    public ProductDTO createProduct(ProductDTO productDTO) {
        if (productDTO == null) {
            return null;
        }
        Category category = null;
        if (productDTO.getCategoryId() != null) {
            category = categoryRepository.findById(productDTO.getCategoryId()).orElse(null);
        }

        Product product = new Product(
                productDTO.getName(),
                productDTO.getPrice(),
                productDTO.getDescription(),
                category
        );
        productRepository.save(product);
        productDTO.setId(product.getId());
        return null;
    }

    @Override
    public ProductDTO updateProduct(Long id, ProductDTO productDTO) {
        Product product = productRepository.findById(id).orElse(null);
        if (product != null) {
            product.setName(productDTO.getName());
            product.setPrice(productDTO.getPrice());
            product.setDescription(productDTO.getDescription());
            product.setCategory(categoryRepository.findById(productDTO.getCategoryId()).orElse(null));
            productRepository.save(product);
            return new ProductDTO(product.getId(), productDTO.getName(), productDTO.getPrice(), productDTO.getDescription(), productDTO.getCategoryId());
        }
        return null;
    }

    @Override
    public int deleteProduct(Long id) {
        Product product = productRepository.findById(id).orElse(null);
        if (product == null) {
            return 0;
        } else {
            productRepository.delete(product);
            return 1;
        }
    }

    @Override
    public List<ProductDTO> getAllProducts() {
        List<ProductDTO> productDTOList = productRepository.findAll()
                .stream().map(product -> {
                    Category category = product.getCategory();
                    return new ProductDTO(
                            product.getId(),
                            product.getName(),
                            product.getPrice(),
                            product.getDescription(),
                            category == null ? null : category.getId()
                    );
                }).toList();
        return productDTOList;
    }
}

