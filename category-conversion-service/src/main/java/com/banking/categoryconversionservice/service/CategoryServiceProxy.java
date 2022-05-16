package com.banking.categoryconversionservice.service;

import com.banking.categoryconversionservice.model.CategoryDTO;
import org.springframework.cloud.netflix.ribbon.RibbonClient;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@FeignClient(name="cate-service")
@RibbonClient(name="cate-service")
public interface CategoryServiceProxy {

    @GetMapping("/api/v1/categories")
    List<CategoryDTO> getAllCategories();
}
