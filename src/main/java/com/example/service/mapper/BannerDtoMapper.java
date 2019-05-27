package com.example.service.mapper;

import com.example.dto.BannerDto;
import com.example.entity.Banner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BannerDtoMapper implements DtoMapper<BannerDto, Banner> {

    private final CategoryDtoMapper categoryDtoMapper;

    @Autowired
    public BannerDtoMapper(CategoryDtoMapper categoryDtoMapper) {
        this.categoryDtoMapper = categoryDtoMapper;
    }

    @Override
    public BannerDto toDto(Banner banner) {
        return BannerDto.builder()
                .id(banner.getId())
                .name(banner.getName())
                .price(banner.getPrice())
                //TODO: or clearly user CategoryMapper in service?)
                .category(categoryDtoMapper.toDto(banner.getCategory()))
                .content(banner.getContent())
                .build();
    }

    @Override
    public Banner fromDto(BannerDto bannerDto) {
        Banner banner = new Banner();
        banner.setId(bannerDto.getId());
        banner.setName(bannerDto.getName());
        banner.setPrice(bannerDto.getPrice());
        //TODO: or clearly user CategoryMapper in service?)
        banner.setCategory(categoryDtoMapper.fromDto(bannerDto.getCategory()));
        banner.setContent(bannerDto.getContent());
        //TODO: set default constructor value or set in service?
        banner.setDeleted(false);
        return banner;
    }
}
