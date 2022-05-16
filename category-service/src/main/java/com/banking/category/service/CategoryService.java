package com.banking.category.service;

import com.banking.category.entity.Category;
import com.banking.category.model.CategoryDTO;

import java.util.List;

public interface CategoryService {
    public Category getOneCategory(int categoryId);
    public List<Category> getAllCategories();
    public Category createCategory(Category categoryDTO);
    public Category updateCategory(Category categoryDTO);
    public void deleteCategory(int categoryId);
}
