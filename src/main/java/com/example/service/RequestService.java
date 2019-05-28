package com.example.service;

import com.example.dto.BannerRequestDto;

public interface RequestService {

    /**
     *
     * @param categoryRequestName
     * @param remoteAddress
     * @param userAgent
     * @return {@code null} if there are no available banners for categoryRequestName and userAgent
     */
    BannerRequestDto getBanner(String categoryRequestName, String remoteAddress, String userAgent);
}
