package com.banking.category.service;

import com.banking.category.entity.Category;
import com.banking.category.exception.NotFoundException;
import com.banking.category.model.CategoryDTO;
import com.banking.category.repository.CategoryRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.willDoNothing;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class CategoryServiceTest {

    @Mock
    private CategoryRepository categoryRepository;
    @InjectMocks
    private CategoryServiceImpl categoryService;

    private AutoCloseable autoCloseable;
    private Category category1;
    private Category category2;
    private CategoryDTO categoryDTO;
    private List<Category> categoryList;

    @BeforeEach
    void setUp() {
//        this.autoCloseable = MockitoAnnotations.openMocks(CategoryRepository.class);
        this.categoryService = new CategoryServiceImpl(categoryRepository);
        category1 = new Category(1,"dog", "cho");
        category2 = new Category(2,"cat", "meo");
        categoryDTO = new CategoryDTO("dog", "cho");
        categoryList = Arrays.asList(category1, category2);
    }

    @AfterEach
    void tearDown() {
        categoryRepository.deleteAll();
    }

    @Test
    void getOneCategory() {
        // given
        Category cate = new Category(1, "Dog", "Cho");
        given(categoryRepository.findById(cate.getId())).willReturn(Optional.of(cate));

        // when
        Category category = categoryService.getOneCategory(cate.getId());
        System.out.println(category.getId() + " " + category.getName() + " " + category.getNameVN());

        //then
        assertThat(category).isNotNull();
    }

    @Test
    void getAllCategories() {
        // given
        given(categoryRepository.findAll()).willReturn(categoryList);

        //when
        List<Category> list = categoryService.getAllCategories();
        System.out.println(list);

        //then
        assertThat(list).isNotNull();
        assertThat(list.size()).isEqualTo(2);
    }

    @Test
    void createCategory() {
        //given
        Category cate = new Category(3 , "Frog", "Con Ech");
        given(categoryRepository.save(cate)).willReturn(cate);
        //then
        Category ca =categoryService.createCategory(cate);
        // when
        assertThat(ca).isNotNull();
    }

    @Test
    void updateCategory() {
        Category catego = new Category(1,"2", "3");
        given(categoryRepository.findById(catego.getId())).willReturn(Optional.of(catego));
        Category category = categoryService.getOneCategory(catego.getId());
        category.setName("Fuck");
        category.setNameVN("Dm");

        // when
        Category updateCategory = categoryService.updateCategory(category);

        assertThat(updateCategory.getName()).isEqualTo("Fuck");
        assertThat(updateCategory.getNameVN()).isEqualTo("Dm");
    }

    @Test
    void updateCategory_idCategoryNotFound() {
        int categoryId = 5;
        Category catego = new Category(1,"2", "3");
        given(categoryRepository.findById(catego.getId())).willReturn(Optional.of(catego));
        Category category = categoryService.getOneCategory(catego.getId());
        category.setName("Fuck");
        category.setNameVN("Dm");

        // when
        NotFoundException exception = assertThrows(NotFoundException.class, () -> {
            categoryService.deleteCategory(categoryId);
        });

        //then
        assertThat(exception.getMessage()).isEqualTo("delete category not found id: 5");
    }

    @Test
    void deleteCategory() {
        //given
        int categoryId = 1;
        Category catego = new Category(1,"2", "3");
        given(categoryRepository.findById(catego.getId())).willReturn(Optional.of(catego));
        Category category = categoryService.getOneCategory(catego.getId());

        willDoNothing().given(categoryRepository).delete(category);

        // when
        categoryService.deleteCategory(categoryId);

        //then
        verify(categoryRepository).delete(category);
    }

    @Test
    void deleteCategory_idCategoryNotFound() {
        //given
        int categoryId = 5;
        Category catego = new Category(1,"2", "3");
        given(categoryRepository.findById(catego.getId())).willReturn(Optional.of(catego));

        // when
        categoryService.getOneCategory(catego.getId());
        NotFoundException exception = assertThrows(NotFoundException.class, () -> {
            categoryService.deleteCategory(categoryId);
        });

        //then
        assertThat(exception.getMessage()).isEqualTo("delete category not found id: 5");
    }
}