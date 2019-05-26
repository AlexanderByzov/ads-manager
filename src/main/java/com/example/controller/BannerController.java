package com.example.controller;

import com.example.dto.BannerDto;
import com.example.service.BannerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@RestController
@RequestMapping("banners")
public class BannerController {

    private final BannerService bannerService;

    @Autowired
    public BannerController(BannerService bannerService) {
        this.bannerService = bannerService;
    }

    @GetMapping
    public Collection<BannerDto> getAll() {
        return bannerService.findAll();
    }

    @GetMapping("/{bannerId}")
    public BannerDto getById(@PathVariable Integer bannerId) {
        return bannerService.findById(bannerId);
    }

    @PostMapping
    public BannerDto create(@RequestBody BannerDto bannerDto) {
        return bannerService.create(bannerDto);
    }

    @PutMapping
    public BannerDto update(@RequestBody BannerDto bannerDto) {
        return bannerService.update(bannerDto);
    }

    @DeleteMapping("/{bannerId}")
    public void delete(@PathVariable Integer bannerId) {
        bannerService.delete(bannerId);
    }
}
