package com.banking.category.service;

import com.banking.category.exception.NotFoundException;
import com.banking.category.model.CategoryDTO;
import com.banking.category.entity.Category;
import com.banking.category.repository.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Transactional
@RequiredArgsConstructor
@Service
public class CategoryServiceImpl implements CategoryService{

    private final CategoryRepository categoryDAO;

    @Override
    public Category getOneCategory(int categoryId) {
        Category category = categoryDAO.findById(categoryId)
                .orElseThrow(() -> new NotFoundException("Get one category not found id: " + categoryId));
//        CategoryDTO categoryDTO = new CategoryDTO();
//        categoryDTO.setId(category.getId());
//        categoryDTO.setName(category.getName());
//        categoryDTO.setNameVN(category.getNameVN());
        return category;
    }

    @Override
    public List<Category> getAllCategories() {
        List<Category> list = categoryDAO.findAll();
//        List<CategoryDTO> categoryDTOS = new ArrayList<>();
//        if(list.size() > 0){
//            list.forEach(cate -> {
//                CategoryDTO category = new CategoryDTO();
//                category.setId(cate.getId());
//                category.setName(cate.getName());
//                category.setNameVN(cate.getNameVN());
//                categoryDTOS.add(category);
//            });
//        }
        return list;
    }

    @Override
    public Category createCategory(Category category) {
//        Category category = new Category();
//        category.setName(categoryDTO.getName());
//        category.setNameVN(categoryDTO.getNameVN());
        categoryDAO.save(category);
        return category;
    }

    @Override
    public Category updateCategory(Category cate) {
        Category category = categoryDAO.findById(cate.getId())
                .orElseThrow(() -> new NotFoundException("Update category not found id: " + cate.getId()));
        category.setName(cate.getName());
        category.setNameVN(cate.getNameVN());
        categoryDAO.save(category);
        return category;
    }

    @Override
    public void deleteCategory(int categoryId) {
        Category category = categoryDAO.findById(categoryId)
                .orElseThrow(() -> new NotFoundException("delete category not found id: " + categoryId));
        categoryDAO.delete(category);
    }
}