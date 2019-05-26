package com.example.dto;

import javax.validation.constraints.*;
import java.math.BigDecimal;

public class BannerDto {

    private Integer id;

    @NotBlank
    @Size(max = 255)
    private String name;

    @Digits(integer = 8, fraction = 2)
    private BigDecimal price;

    @NotNull
    private CategoryDto category;

    @NotBlank
    private String content;
}
