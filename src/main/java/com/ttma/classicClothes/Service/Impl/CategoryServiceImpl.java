package com.ttma.classicClothes.Service.Impl;

import com.ttma.classicClothes.Service.CategoryService;
import com.ttma.classicClothes.dto.request.CategoryRequest;
import com.ttma.classicClothes.dto.response.ResponseCategory;
import com.ttma.classicClothes.model.Category;
import com.ttma.classicClothes.repository.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService {
    private final CategoryRepository categoryRepository;
    @Override
    public long createCate(CategoryRequest request) {
        Category category = Category.builder()
                .name(request.getName())
                .description(request.getDescription())
                .build();
        categoryRepository.save(category);
        return category.getId();
    }

    @Override
    public List<ResponseCategory> getAllCate(int pageNo, int pageSize) {
        Pageable pageable = PageRequest.of(pageNo, pageSize);
        Page<Category> categories = categoryRepository.findAll(pageable);
        return categories.map(category -> ResponseCategory.builder()
                .id(category.getId())
                .name(category.getName())
                .description(category.getDescription())
                .build()).toList();
    }
}
