package com.micoservice.cateservice.model;

import lombok.*;

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
