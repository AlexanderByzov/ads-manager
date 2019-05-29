package com.example.service.mapper;

import com.example.dto.CategoryDto;
import com.example.entity.Category;
import org.springframework.stereotype.Service;

@Service
public class CategoryDtoMapper implements DtoMapper<CategoryDto, Category> {
    @Override
    public CategoryDto toDto(Category category) {
        return CategoryDto.builder()
                .id(category.getId())
                .name(category.getName())
                .requestName(category.getRequestName())
                .build();
    }

    @Override
    public Category fromDto(CategoryDto categoryDto) {
        Category category = new Category();
        category.setId(categoryDto.getId());
        category.setName(categoryDto.getName());
        category.setRequestName(categoryDto.getRequestName());
        //TODO: set default constructor value or set in service?
        category.setDeleted(false);
        return null;
    }
}
