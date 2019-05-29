package com.example.service.impl;

import com.example.dto.CategoryDto;
import com.example.entity.Banner;
import com.example.entity.Category;
import com.example.repository.BannerRepository;
import com.example.repository.CategoryRepository;
import com.example.service.CategoryService;
import com.example.service.mapper.CategoryDtoMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.MessageFormat;
import java.util.Collection;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepository;

    //TODO: use service instead?
    private final BannerRepository bannerRepository;

    private final CategoryDtoMapper categoryDtoMapper;

    @Autowired
    public CategoryServiceImpl(CategoryRepository categoryRepository,
                               BannerRepository bannerRepository, CategoryDtoMapper categoryDtoMapper) {
        this.categoryRepository = categoryRepository;
        this.bannerRepository = bannerRepository;
        this.categoryDtoMapper = categoryDtoMapper;
    }

    @Override
    public Collection<CategoryDto> findAll() {
        return categoryRepository.findAll().stream().map(categoryDtoMapper::toDto).collect(Collectors.toList());
    }

    @Override
    public CategoryDto findById(Integer categoryId) {
        return categoryRepository.findById(categoryId)
                .map(categoryDtoMapper::toDto)
                .orElseThrow(() -> new RuntimeException(
                        MessageFormat.format("Category not found by ID: {0}", categoryId)
                ));
    }

    @Override
    public CategoryDto create(CategoryDto categoryDto) {
        checkForCreate(categoryDto);
        return save(categoryDto);
    }

    private void checkForCreate(CategoryDto categoryDto) {
        Objects.requireNonNull(categoryDto);
        if (categoryDto.getId() != null) {
            throw new RuntimeException(MessageFormat.format("Create error. Category is not new: {0}", categoryDto));
        }
    }

    @Override
    public CategoryDto update(CategoryDto categoryDto) {
        checkForUpdate(categoryDto);
        return save(categoryDto);
    }

    private void checkForUpdate(CategoryDto categoryDto) {
        Objects.requireNonNull(categoryDto);
        if (categoryDto.getId() == null) {
            throw new RuntimeException(MessageFormat.format("Update error. Category is new: {0}", categoryDto));
        }
    }

    private CategoryDto save(CategoryDto categoryDto) {
        Category category = categoryRepository.save(categoryDtoMapper.fromDto(categoryDto));
        //TODO: unique save check? for valide UI message?
        return categoryDtoMapper.toDto(category);
    }

    @Override
    public void delete(Integer categoryId) {
        Optional<Category> category = categoryRepository.findById(categoryId);
        if (category.isPresent()) {
            this.softDelete(category.get());
        } else {
            throw new RuntimeException(MessageFormat.format(
                    "Can't delete Category by Id: {0} - entity already not exist", categoryId
            ));
        }
    }

    private void softDelete(Category category) {
        Collection<Banner> bannersByCategory = bannerRepository.findByCategoryId(category.getId());
        if (bannersByCategory.isEmpty()) {
            category.setDeleted(true);
        } else {
            List<Integer> bannersIds = bannersByCategory.stream().map(Banner::getId).collect(Collectors.toList());
            throw new RuntimeException(MessageFormat.format(
                    "Can't delete category '{'{0}'}'. Category links with banners {1}", category, bannersIds
            ));
        }
    }
}
