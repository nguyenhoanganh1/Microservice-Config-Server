package com.micoservice.cateservice.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ResponseDTO {
    private boolean status;
    private String message;
    private Object data;

    public ResponseDTO(boolean status, String message) {
        this.status = status;
        this.message = message;
    }
}
