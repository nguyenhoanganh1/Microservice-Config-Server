package com.micoservice.cateservice.controller;

import com.micoservice.cateservice.entity.Category;
import com.micoservice.cateservice.model.CategoryDTO;
import com.micoservice.cateservice.model.ResponseDTO;
import com.micoservice.cateservice.service.CategoryService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

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
    public ResponseEntity<CategoryDTO> getOneCategoryById( @PathVariable(value = "id") int categoryId){
        return Optional.of(new ResponseEntity<>(categoryService.getOneCategory(categoryId),HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @Operation(summary = "Get All Category", responses = {
            @ApiResponse(description = "Get ALL Category Success", responseCode = "200",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Category.class)))
    })
    @GetMapping("/categories")
    public ResponseEntity<?> getAllCategories(){
//        return ResponseEntity.ok(new ResponseDTO(true,
//                "Get all category Success",
//                categoryService.getAllCategories()));
        return ResponseEntity.ok(categoryService.getAllCategories());
    }

    @Operation(summary = "Create Category", responses = {
            @ApiResponse(description = "Create Category Success", responseCode = "200",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Category.class))),
    })
    @PostMapping("/categories")
    public ResponseEntity<ResponseDTO> createCategory(@RequestBody CategoryDTO categoryDTO){
        categoryService.createCategory(categoryDTO);
        return ResponseEntity.ok(new ResponseDTO(true,
                "create category Success",
                categoryDTO));
    }

    @Operation(summary = "Update Category", responses = {
            @ApiResponse(description = "Update Category Success", responseCode = "200",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Category.class))),
            @ApiResponse(description = "Update category not found id", responseCode = "404", content = @Content)
    })
    @PutMapping("/categories")
    public ResponseEntity<ResponseDTO> updateCategory(@RequestBody CategoryDTO categoryDTO){
        categoryService.updateCategory(categoryDTO);
        return ResponseEntity.ok(new ResponseDTO(true,
                "update category Success",
                categoryDTO));
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