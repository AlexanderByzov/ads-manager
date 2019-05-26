package com.example.service;

import com.example.dto.BannerDto;

import java.util.Collection;

public interface BannerService {

    Collection<BannerDto> findAll();

    BannerDto findById(Integer bannerId);

    BannerDto create(BannerDto bannerDto);

    BannerDto update(BannerDto bannerDto);

    void delete(Integer bannerId);
}
