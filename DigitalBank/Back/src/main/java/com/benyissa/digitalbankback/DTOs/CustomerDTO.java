package com.benyissa.digitalbankback.DTOs;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor

public class CustomerDTO {
    private Long id;
    private String firstName;
    private String lastName;
    private String email;
}
