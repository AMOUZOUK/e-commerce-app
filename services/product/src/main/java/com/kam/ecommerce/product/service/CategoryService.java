package com.kam.ecommerce.product.service;

import com.kam.ecommerce.product.exception.CategoryNotFoundException;
import com.kam.ecommerce.product.helper.CategoryRequeste;
import com.kam.ecommerce.product.helper.CategoryResponse;
import com.kam.ecommerce.product.mapper.CategoryMapper;
import com.kam.ecommerce.product.repository.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CategoryService {

    private CategoryRepository categoryRepository;
    private CategoryMapper categoryMapper;
    public Integer createCategory(CategoryRequeste categoryRequeste) {
        var category = categoryRepository.save(categoryMapper.toCategory(categoryRequeste));
        return category.getId();
    }

    public void updateCategory(CategoryRequeste categoryRequeste) {
        var category = categoryRepository.findById(categoryRequeste.id())
                .orElseThrow(()-> new CategoryNotFoundException(
                        String.format("Cannot update category:: No category found with the provided ID:: %s",categoryRequeste.id())
                ));
        categoryMapper.mergeCategory(category, categoryRequeste);
        categoryRepository.save(category);
    }

    public List<CategoryResponse> getAllCategories() {
        return categoryRepository.findAll()
                .stream().map(categoryMapper::fromCategory)
                .collect(Collectors.toList());
    }

    public Boolean existId(int id) {
        return categoryRepository.findById(id).isPresent();
    }

    public CategoryResponse findCategoryById(int id) {
        return categoryRepository.findById(id)
                .map(categoryMapper::fromCategory)
                .orElseThrow( ()-> new CategoryNotFoundException(
                        String.format("Cannot update category:: No category found with the provided ID:: %s", id)
                ));
    }

    public void deleteCategory(int id) {
        categoryRepository.deleteById(id);
    }
}
