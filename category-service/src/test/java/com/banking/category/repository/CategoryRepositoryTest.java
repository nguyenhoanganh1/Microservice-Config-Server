package com.banking.category.repository;

import com.banking.category.entity.Category;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.BDDAssumptions.given;
import static org.junit.jupiter.api.Assertions.*;
import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@ExtendWith(MockitoExtension.class)
class CategoryRepositoryTest {

    @Autowired
    private CategoryRepository categoryRepository;

    Category category;

    @BeforeEach
    void setUp() {
        category = new Category("123", "123");
        List<Category> list = Arrays.asList(
                new Category("123", "123"),
                new Category("cat", "meo"),
                new Category("dog", "cho"),
                new Category("snack", "ran")
        );
        categoryRepository.saveAll(list);
    }

    @AfterEach
    void tearDown() {
        category = null;
        categoryRepository.deleteAll();
    }

    @Test
    void findAll_success() {
        Category category1 = new Category("123", "123");
        Category category2 = new Category("456", "456");
        categoryRepository.saveAll(Arrays.asList(category1, category2));
        List<Category> categories = categoryRepository.findAll();
        assertThat(categories.get(0).getName()).isEqualTo("123");
    }

    @Test
    void findById() {
    }
}