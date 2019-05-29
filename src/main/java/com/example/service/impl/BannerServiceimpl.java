package com.example.service.impl;

import com.example.dto.BannerDto;
import com.example.entity.Banner;
import com.example.service.BannerService;
import com.example.service.LocalBannerService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;
import java.lang.UnsupportedOperationException;

@Service
@Transactional
public class BannerServiceimpl implements BannerService, LocalBannerService {
    @Override
    public Collection<BannerDto> findAll() {
        throw new UnsupportedOperationException();
    }

    @Override
    public BannerDto findById(Integer bannerId) {
        throw new UnsupportedOperationException();
    }

    @Override
    public BannerDto create(BannerDto bannerDto) {
        throw new UnsupportedOperationException();
    }

    @Override
    public BannerDto update(BannerDto bannerDto) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void delete(Integer bannerId) {
        throw new UnsupportedOperationException();
    }

    @Override
    public Collection<Banner> findAllByCategoryId(Integer categoryId) {
        throw new UnsupportedOperationException();
    }
}
