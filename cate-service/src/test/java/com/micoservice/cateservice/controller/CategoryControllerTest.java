package com.micoservice.cateservice.controller;

import com.micoservice.cateservice.entity.Category;
import com.micoservice.cateservice.model.CategoryDTO;
import com.micoservice.cateservice.service.CategoryService;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class CategoryControllerTest {
    @Mock
    private CategoryService categoryService;
    @InjectMocks
    private CategoryController categoryController;
    @Autowired
    private MockMvc mockMvc;

    private Category category;
    private Category category2;
    private List<Category> categoryList;

    protected String mapToJson(Object obj) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.writeValueAsString(obj);
    }

    protected <T> T mapFromJson(String json, Class<T> clazz)
            throws JsonParseException, JsonMappingException, IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.readValue(json, clazz);
    }

    @BeforeEach
    void setUp() {
        category = new Category(1, "dog", "cho");
        category2 = new Category(2, "cat", "meo");
        categoryList = Arrays.asList(category, category2);
        mockMvc = MockMvcBuilders.standaloneSetup(categoryController).build();
    }

    @AfterEach
    void tearDown() {
        category = null;
    }

    @Test
    void getOneCategoryById() throws Exception {
        int categoryId = 1;
        String uri = "/api/v1/categories/" + categoryId;
//        when(categoryService.getOneCategory(categoryId)).thenReturn(category);
        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.get(uri)
                .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andReturn();
        int status = mvcResult.getResponse().getStatus();
        String content = mvcResult.getResponse().getContentAsString();
        System.out.println(content);
        assertEquals(200, status);
    }

    @Test
    void getAllCategories() throws Exception {
        String uri = "/api/v1/categories";
//        when(categoryService.getAllCategories()).thenReturn(categoryList);
        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.get(uri)
                .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andReturn();
        int status = mvcResult.getResponse().getStatus();
        String content = mvcResult.getResponse().getContentAsString();
        System.out.println(content);
        assertEquals(200, status);
    }

    @Test
    void createCategory() throws Exception {
        String uri = "/api/v1/categories";
        CategoryDTO categoryDTO = new CategoryDTO("frog", "ech");
        String json = mapToJson(categoryDTO);
//        when(categoryService.createCategory(any())).thenReturn(categoryDTO);
        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.post(uri)
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .content(json))
                        .andReturn();
        int status = mvcResult.getResponse().getStatus();
        String content = mvcResult.getResponse().getContentAsString();
        System.out.println(content);
        assertEquals(200, status);
    }

    @Test
    void updateCategory() throws Exception {
        String uri = "/api/v1/categories/";
        CategoryDTO cate = new CategoryDTO(1,"frog", "ech");
        cate.setName("HoLa");
        String json = mapToJson(cate);
        when(categoryService.updateCategory(cate)).thenReturn(cate);
        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.put(uri)
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(json))
                .andReturn();
        int status = mvcResult.getResponse().getStatus();
        String content = mvcResult.getResponse().getContentAsString();
        System.out.println(content);
        assertEquals(200, status);
    }

    @Test
    void deleteCategory() throws Exception {
        int categoryId = 1;
        String uri = "/api/v1/categories/" + categoryId;
//        when(categoryService.deleteCategory(categoryId));
        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.delete(uri)
                .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andReturn();
        int status = mvcResult.getResponse().getStatus();
        String content = mvcResult.getResponse().getContentAsString();
        System.out.println(content);
        assertEquals(200, status);
    }
}