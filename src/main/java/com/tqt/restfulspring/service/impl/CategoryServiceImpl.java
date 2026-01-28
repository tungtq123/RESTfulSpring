package com.tqt.restfulspring.service.impl;

import com.tqt.restfulspring.dto.CategoryDTO;
import com.tqt.restfulspring.repository.CategoryRepository;
import com.tqt.restfulspring.service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepository;

    @Override
    public List<CategoryDTO> getAllCategories() {
        List<CategoryDTO> categoryDTOList = categoryRepository.findAll()
                .stream().map(category -> {
                    return new CategoryDTO(category.getId(), category.getName(), category.getDescription());
                }).toList();
        return categoryDTOList;
    }

    @Override
    public CategoryDTO getCategoryById(long id) {
        return null;
    }

    @Override
    public CategoryDTO createCategory(CategoryDTO categoryDTO) {
        return null;
    }

    @Override
    public CategoryDTO updateCategory(CategoryDTO categoryDTO) {
        return null;
    }

    @Override
    public int deleteCategory(Long id) {
        return 0;
    }
}
