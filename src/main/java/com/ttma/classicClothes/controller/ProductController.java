package com.ttma.classicClothes.controller;

import com.ttma.classicClothes.Service.ProductService;
import com.ttma.classicClothes.dto.request.ProductRequest;
import com.ttma.classicClothes.dto.response.ResponseData;
import com.ttma.classicClothes.dto.response.ResponseError;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/product")
public class ProductController {
    private final ProductService productService;
    @PostMapping("/create")
    public ResponseData<?> createProduct(@RequestPart(value = "product", required = false) ProductRequest request,
                                         @RequestPart(value = "image",  required = false) MultipartFile image){
        try {
            return new ResponseData<>(HttpStatus.CREATED.value(), "success", productService.createProduct(request,image));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    @PutMapping("/update/{id}")
    public ResponseData<?> updateProduct(@PathVariable("id") int id,
            @RequestPart(value = "product", required = false) ProductRequest request,
                                         @RequestPart(value = "image",  required = false) MultipartFile image){
        try {
            return new ResponseData<>(HttpStatus.CREATED.value(), "success", productService.createProduct(request,image));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    @GetMapping("/{id}")
    public  ResponseData<?> getProduct(@PathVariable("id")long id){
        try {
            return  new ResponseData<>(HttpStatus.OK.value(), "success", productService.getProduct(id));
        } catch (Exception e){
            return new ResponseError(HttpStatus.BAD_REQUEST.value(), "Failed");
        }
    }
    @GetMapping("/")
    public  ResponseData<?> getAllProduct(@RequestParam(defaultValue = "0", required = false) int pageNo,
                                          @RequestParam(defaultValue = "20", required = false) int pageSize){
        try {
            return  new ResponseData<>(HttpStatus.OK.value(), "success", productService.getAllProduct(pageNo, pageSize));
        } catch (Exception e){
            return new ResponseError(HttpStatus.BAD_REQUEST.value(), "Failed");
        }
    }
    @GetMapping("/search/{name}")
    public  ResponseData<?> getAllProduct(@PathVariable("name")String name){
        try {
            return  new ResponseData<>(HttpStatus.OK.value(), "success", productService.getProductByName(name));
        } catch (Exception e){
            return new ResponseError(HttpStatus.BAD_REQUEST.value(), "Failed");
        }
    }
    @DeleteMapping("/{id}")
    public  ResponseData<?> deleteProduct(@PathVariable("id")int id){
        try {
            productService.deleteProduct(id);
            return  new ResponseData<>(HttpStatus.OK.value(), "success");
        } catch (Exception e){
            return new ResponseError(HttpStatus.BAD_REQUEST.value(), "Failed");
        }
    }
}
