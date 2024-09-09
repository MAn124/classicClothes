package com.ttma.classicClothes.Service;

import com.ttma.classicClothes.dto.request.CategoryRequest;
import com.ttma.classicClothes.dto.response.ResponseCategory;
import com.ttma.classicClothes.dto.response.ResponsePage;

import java.util.List;

public interface CategoryService {
    long createCate(CategoryRequest request);
    ResponsePage<?> getAllCate(int pageNo, int pageSize);
    long updateCate(long id, CategoryRequest request);
    ResponseCategory getCateById(long id);
    void deleteCate(long id);
}
