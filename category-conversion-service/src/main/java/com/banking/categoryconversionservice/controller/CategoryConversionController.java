package com.banking.categoryconversionservice.controller;

import com.banking.categoryconversionservice.model.CategoryDTO;
import com.banking.categoryconversionservice.service.CategoryServiceProxy;
import lombok.RequiredArgsConstructor;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@RestController
public class CategoryConversionController {
    private static final String URL = "http://localhost:8088/api/v1/categories";
    private final CategoryServiceProxy categoryServiceProxy;
    private final RestTemplate restTemplate;

    @GetMapping("/categories")
    public List<CategoryDTO> getAllCategories(){
        ParameterizedTypeReference<List<CategoryDTO>> responseType = new ParameterizedTypeReference<List<CategoryDTO>>() {};
        ResponseEntity<List<CategoryDTO>> resp = restTemplate.exchange(URL,
                HttpMethod.GET,
                null,
                responseType);
        List<CategoryDTO> data = resp.getBody();
        return data;
//        ResponseEntity<CategoryDTO[]> responseEntity = new RestTemplate()
//                .getForEntity("http://localhost:8088/api/v1/categories", CategoryDTO[].class);
//        CategoryDTO[] body = responseEntity.getBody();
//        return Arrays.stream(body).collect(Collectors.toList());
    }
}
