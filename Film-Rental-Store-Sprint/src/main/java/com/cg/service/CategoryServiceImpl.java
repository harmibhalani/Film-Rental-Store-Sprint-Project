package com.cg.service;

import com.cg.model.Category;
import com.cg.repositories.CategoryRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class CategoryServiceImpl implements CategoryService {

//    @Autowired
//    private CategoryRepository categoryRepository;
//
//    @Override
//    public Category updateCategoryName(Short categoryId, String newCategoryName) {
//        // Find the category by id
//        Category category = categoryRepository.findById(categoryId)
//                .orElseThrow(() -> new EntityNotFoundException("Category not found with id: " + categoryId));
//        
//        // Update the category name and last update timestamp
//        category.setName(newCategoryName);
//        category.setLastUpdate(LocalDateTime.now());
//        
//        // Save the updated category
//        return categoryRepository.save(category);
//    }
}
