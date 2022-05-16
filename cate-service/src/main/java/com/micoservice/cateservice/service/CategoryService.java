package com.micoservice.cateservice.service;

import com.micoservice.cateservice.entity.Category;
import com.micoservice.cateservice.model.CategoryDTO;

import java.util.List;

public interface CategoryService {
     CategoryDTO getOneCategory(int categoryId);
     List<CategoryDTO> getAllCategories();
     CategoryDTO findCategoryByName(String name);
     void createCategory(CategoryDTO categoryDTO);
     CategoryDTO updateCategory(CategoryDTO categoryDTO);
     void deleteCategory(int categoryId);
}
