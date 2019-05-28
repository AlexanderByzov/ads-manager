package com.example.controller;

import com.example.dto.BannerRequestDto;
import com.example.dto.BannerResponseDto;
import com.example.service.RequestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.util.Objects;

import static org.springframework.http.HttpHeaders.USER_AGENT;

@RestController
@RequestMapping("bid")
public class BannerRequestController {

    private final RequestService requestService;

    @Autowired
    public BannerRequestController(RequestService requestService) {
        this.requestService = requestService;
    }

    @GetMapping
    public BannerResponseDto getBanner(@RequestParam String category,
                                       @RequestHeader(name = USER_AGENT) String userAgent,
                                       HttpServletRequest request,
                                       HttpServletResponse response) {
        BannerRequestDto bannerRequestDto = BannerRequestDto.builder()
                .requestCategoryName(category)
                .clientIpAddress(request.getRemoteAddr())
                .userAgent(userAgent)
                .build();
        BannerResponseDto responseDto = requestService.getBanner(bannerRequestDto);
        if (Objects.isNull(responseDto)) {
            response.setStatus(HttpServletResponse.SC_NO_CONTENT);
        }
        return responseDto;
    }
}
