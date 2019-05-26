package com.example.controller;

import com.example.dto.CategoryDto;
import com.example.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@RestController
@RequestMapping("categories")
public class CategoryController {

    private final CategoryService categoryService;

    @Autowired
    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @GetMapping
    public Collection<CategoryDto> getAll() {
        return categoryService.findAll();
    }

    @GetMapping("/{categoryId}")
    public CategoryDto getById(@PathVariable Integer categoryId) {
        return categoryService.findById(categoryId);
    }

    @PostMapping
    public CategoryDto create(@RequestBody CategoryDto categoryDto) {
        return categoryService.create(categoryDto);
    }

    @PutMapping
    public CategoryDto update(@RequestBody CategoryDto categoryDto) {
        return categoryService.update(categoryDto);
    }

    @DeleteMapping("/{categoryId}")
    public void delete(@PathVariable Integer categoryId) {
        categoryService.delete(categoryId);
    }
}
