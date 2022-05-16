package com.banking.category.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
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
