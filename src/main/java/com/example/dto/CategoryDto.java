package com.example.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Getter
@AllArgsConstructor
@ToString
@Builder(toBuilder = true)
public class CategoryDto {

    private final Integer id;

    @NotBlank
    @Size(max = 255)
    private final String name;

    @NotBlank
    @Size(max = 255)
    private final String requestName;
}
