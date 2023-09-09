package com.codingduo.cinemabookingticket.dto;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Service;

@Data
public class ComboDTO {
    private Long id;
    private String name;
    private String imagePath;
    private int price;
    private boolean deleted;
}
