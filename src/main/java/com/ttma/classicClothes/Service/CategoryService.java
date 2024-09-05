package com.ttma.classicClothes.Service;

import com.ttma.classicClothes.dto.request.CategoryRequest;
import com.ttma.classicClothes.dto.response.ResponseCategory;

import java.util.List;

public interface CategoryService {
    long createCate(CategoryRequest request);
   List<ResponseCategory> getAllCate(int pageNo, int pageSize);
}
