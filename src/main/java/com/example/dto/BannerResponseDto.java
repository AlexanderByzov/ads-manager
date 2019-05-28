package com.example.dto;

import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
@Builder(toBuilder = true)
public class BannerResponseDto {
    @NotBlank
    private static String content;
}
