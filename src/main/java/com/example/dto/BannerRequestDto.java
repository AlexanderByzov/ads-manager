package com.example.dto;

import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
@Builder
public class BannerRequestDto {

    @NotBlank
    private final String requestCategoryName;

    @NotBlank
    private final String clientIpAddress;

    @NotBlank
    private final String userAgent;
}
