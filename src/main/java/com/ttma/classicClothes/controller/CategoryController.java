package com.ttma.classicClothes.controller;

import com.ttma.classicClothes.Service.CategoryService;
import com.ttma.classicClothes.dto.request.CategoryRequest;
import com.ttma.classicClothes.dto.response.ResponseData;
import com.ttma.classicClothes.dto.response.ResponseError;
import com.ttma.classicClothes.model.Category;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/category")
@RequiredArgsConstructor
public class CategoryController {
    private final CategoryService categoryService;
    @PostMapping("/create")
    public ResponseData<?> createCate(@RequestBody CategoryRequest request){
        try {
            return new ResponseData<>(HttpStatus.CREATED.value(), "success",categoryService.createCate(request));
        } catch (Exception e){
            return new ResponseError(HttpStatus.BAD_REQUEST.value(), "Failed");
        }
    }

    @GetMapping("/")
    public ResponseData<?> getAllCate(@RequestParam(defaultValue = "0", required = false) int pageNo,
                                      @RequestParam(defaultValue = "20", required = false) int pageSize){
        try {
            return new ResponseData<>(HttpStatus.OK.value(), "success",categoryService.getAllCate(pageNo, pageSize));
        } catch (Exception e){
            return new ResponseError(HttpStatus.BAD_REQUEST.value(), "Failed");
        }
    }
}
