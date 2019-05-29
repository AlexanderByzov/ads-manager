package com.example.service.impl;

import com.example.dto.BannerRequestDto;
import com.example.dto.BannerResponseDto;
import com.example.service.RequestService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class RequestServiceImpl implements RequestService {
    @Override
    public BannerResponseDto getBanner(BannerRequestDto bannerRequestDto) {
        throw new UnsupportedOperationException();
    }
}
