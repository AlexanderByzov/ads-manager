package com.example.dto;

import lombok.*;

import javax.validation.constraints.Digits;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.math.BigDecimal;

@Getter
@AllArgsConstructor
@ToString
@Builder(toBuilder = true)
public class BannerDto {

    private final Integer id;

    @NotBlank
    @Size(max = 255)
    private final String name;

    @Digits(integer = 8, fraction = 2)
    private final BigDecimal price;

    @NotNull
    private final CategoryDto category;

    @NotBlank
    private final String content;
}
