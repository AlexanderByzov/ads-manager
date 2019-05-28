package com.example.service;

import com.example.dto.BannerRequestDto;
import com.example.dto.BannerResponseDto;

public interface RequestService {

    /**
     *
     * @param bannerRequestDto
     * @return {@code null} if there are no available banners for categoryRequestName and userAgent
     */
    BannerResponseDto getBanner(BannerRequestDto bannerRequestDto);
}
