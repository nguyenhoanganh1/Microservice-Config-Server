package com.banking.category.controller;

import com.banking.category.entity.Category;
import com.banking.category.model.CategoryDTO;
import com.banking.category.model.ResponseDTO;
import com.banking.category.service.CategoryService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.websocket.server.PathParam;

@CrossOrigin("*")
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1")
public class CategoryController {
    private static final Logger LOGGER = LoggerFactory.getLogger(CategoryController.class);
    private final CategoryService categoryService;

    @Operation(summary = "Get Category By Id",responses = {
            @ApiResponse(description = "Get One Category Success", responseCode = "200",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Category.class))),
            @ApiResponse(description = "Get one category not found", responseCode = "404", content = @Content)
    })
    @GetMapping("/categories/{id}")
    public ResponseEntity<ResponseDTO> getOneCategoryById( @PathVariable(value = "id") int categoryId){
        return ResponseEntity.ok(new ResponseDTO(true,
                "Get one category success",
                categoryService.getOneCategory(categoryId)));
    }

    @Operation(summary = "Get All Category", responses = {
            @ApiResponse(description = "Get ALL Category Success", responseCode = "200",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Category.class)))
    })
    @GetMapping("/categories")
    public ResponseEntity<ResponseDTO> getAllCategories(){
        return ResponseEntity.ok(new ResponseDTO(true,
                "Get all category Success",
                categoryService.getAllCategories()));
    }

    @Operation(summary = "Create Category", responses = {
            @ApiResponse(description = "Create Category Success", responseCode = "200",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Category.class))),
    })
    @PostMapping("/categories")
    public ResponseEntity<ResponseDTO> createCategory(@RequestBody CategoryDTO categoryDTO){
        Category category = new Category(categoryDTO.getName(), categoryDTO.getNameVN());
        return ResponseEntity.ok(new ResponseDTO(true,
                "create category Success",
                categoryService.createCategory(category)));
    }

    @Operation(summary = "Update Category", responses = {
            @ApiResponse(description = "Update Category Success", responseCode = "200",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Category.class))),
            @ApiResponse(description = "Update category not found id", responseCode = "404", content = @Content)
    })
    @PutMapping("/categories")
    public ResponseEntity<ResponseDTO> updateCategory(@RequestBody CategoryDTO categoryDTO){
        Category category = new Category(categoryDTO.getId(), categoryDTO.getName(), categoryDTO.getNameVN());
        return ResponseEntity.ok(new ResponseDTO(true,
                "update category Success",
                categoryService.updateCategory(category)));
    }

    @Operation(summary = "Delete Category", responses = {
            @ApiResponse(description = "Delete Category Success", responseCode = "200",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Category.class))),
            @ApiResponse(description = "Delete category not found id", responseCode = "404", content = @Content)
    })
    @DeleteMapping("/categories/{id}")
    public ResponseEntity<ResponseDTO> deleteCategory(@PathVariable(value = "id") int categoryId){
        categoryService.deleteCategory(categoryId);
        return ResponseEntity.ok(new ResponseDTO(true,
                "Delete category Success"));
    }
}