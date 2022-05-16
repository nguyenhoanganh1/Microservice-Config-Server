package com.micoservice.cateservice.service;

import com.micoservice.cateservice.entity.Category;
import com.micoservice.cateservice.exception.NotFoundException;
import com.micoservice.cateservice.model.CategoryDTO;
import com.micoservice.cateservice.repository.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Transactional
@RequiredArgsConstructor
@Service
public class CategoryServiceImpl implements CategoryService{

    private final CategoryRepository categoryDAO;
    private final ModelMapper modelMapper;

    @Override
    public CategoryDTO getOneCategory(int categoryId) {
        Category category = categoryDAO.findById(categoryId).get();
//        CategoryDTO categoryDTO = new CategoryDTO(category.getId(),
//                                            category.getName(),
//                                            category.getNameVN());
        return modelMapper.map(category, CategoryDTO.class);
    }

    @Override
    public List<CategoryDTO> getAllCategories() {
        List<CategoryDTO> categoryDTOS = new ArrayList<>();
        categoryDAO.findAll().stream().forEach(cate ->{
            categoryDTOS.add(modelMapper.map(cate, CategoryDTO.class));
        });
        return categoryDTOS;
    }

    @Override
    public CategoryDTO findCategoryByName(String name) {
        Category category = categoryDAO.findCategoryByName(name);
//        return new CategoryDTO(category.getName(), category.getNameVN());
        return modelMapper.map(category, CategoryDTO.class);
    }

    @Override
    public void createCategory(CategoryDTO categoryDTO) {
//        Category category = new Category(categoryDTO.getName(), categoryDTO.getNameVN());
        Category category = modelMapper.map(categoryDTO, Category.class);
        categoryDAO.save(category);
    }

    @Override
    public CategoryDTO updateCategory(CategoryDTO categoryDTO) {
        Category category = categoryDAO.findById(categoryDTO.getId()).get();
        category.setName(categoryDTO.getName());
        category.setNameVN(categoryDTO.getNameVN());
        categoryDAO.save(category);
        return categoryDTO;
    }

    @Override
    public void deleteCategory(int categoryId) {
        Category category = categoryDAO.findById(categoryId).get();
        categoryDAO.delete(category);
    }
}