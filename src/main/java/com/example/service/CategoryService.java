package com.example.service;

import com.example.dto.CategoryDto;

import java.util.Collection;

public interface CategoryService {

    Collection<CategoryDto> findAll();

    CategoryDto findById(Integer categoryId);

    CategoryDto create(CategoryDto categoryDto);

    CategoryDto update(CategoryDto categoryDto);

    void delete(Integer categoryId);
}
