package com.micoservice.cateservice.service;

import com.micoservice.cateservice.entity.Category;
import com.micoservice.cateservice.model.CategoryDTO;
import com.micoservice.cateservice.repository.CategoryRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.BDDMockito;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.BDDAssumptions.given;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class CategoryServiceImplTest {

    @Mock
    private CategoryRepository categoryRepository;
    private CategoryServiceImpl underTest;
    @Autowired
    private ModelMapper modelMapper;

    @BeforeEach
    void setUp() {
        underTest = new CategoryServiceImpl(categoryRepository, modelMapper);
    }


    @Test
    void getOneCategory() {

    }

    @Test
    void getAllCategories() {
        underTest.getAllCategories();
        verify(categoryRepository).findAll();
    }

    @Test
    void createCategory() {
        CategoryDTO dto = new CategoryDTO("viet", "Nam");
        underTest.createCategory(dto);

        ArgumentCaptor<Category> categoryDTOArgumentCaptor =
                ArgumentCaptor.forClass(Category.class);

        verify(categoryRepository)
                .save(categoryDTOArgumentCaptor.capture());

        Category captureCategory = categoryDTOArgumentCaptor.getValue();
        CategoryDTO cate = new CategoryDTO(captureCategory.getName(), captureCategory.getNameVN());
        assertThat(cate).isEqualTo(dto);
    }

    @Test
    void updateCategory() {
        Category category = new Category(1,"2", "3");
        BDDMockito.given(categoryRepository.findById(category.getId())).willReturn(Optional.of(category));
        CategoryDTO getCategoryDTO = underTest.getOneCategory(category.getId());
        getCategoryDTO.setName("Fuck");
        getCategoryDTO.setNameVN("Dm");

        // when
        CategoryDTO update = underTest.updateCategory(getCategoryDTO);

        assertThat(update.getName()).isEqualTo("Fuck");
        assertThat(update.getNameVN()).isEqualTo("Dm");
    }

    @Test
    void deleteCategory() {
        int categoryId = 1;
        CategoryDTO catego = new CategoryDTO(1,"2", "3");
//        underTest.createCategory(catego);

        BDDMockito.given(categoryRepository.findById(categoryId))
                .willReturn(Optional.ofNullable(new Category(catego.getId(),
                        catego.getName(),
                        catego.getNameVN())));

        CategoryDTO category = underTest.getOneCategory(catego.getId());
        // when
        underTest.deleteCategory(category.getId());

        //then
        verify(underTest).deleteCategory(category.getId());
    }
}