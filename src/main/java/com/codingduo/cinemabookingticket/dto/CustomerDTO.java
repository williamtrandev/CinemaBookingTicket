package com.codingduo.cinemabookingticket.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CustomerDTO {
    private String name;
    private String email;
    private String password;
    private String repeatPassword;
    private int point;
}
