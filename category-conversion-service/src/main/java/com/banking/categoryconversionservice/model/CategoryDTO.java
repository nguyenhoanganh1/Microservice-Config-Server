package com.banking.categoryconversionservice.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CategoryDTO {
    private Integer id;
    private String name;
    private String nameVN;

    public CategoryDTO(String name, String nameVN) {
        this.name = name;
        this.nameVN = nameVN;
    }

}
