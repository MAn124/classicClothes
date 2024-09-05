package com.ttma.classicClothes.Service;

import com.ttma.classicClothes.dto.request.ProductRequest;
import com.ttma.classicClothes.dto.response.ResponseProduct;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface ProductService {
    List<ResponseProduct> getAllProduct(int pageNo, int pageSize);
    long createProduct(ProductRequest request ,MultipartFile image) throws IOException;
    long updateProduct(long id,ProductRequest request, MultipartFile image) throws IOException;

    void deleteProduct(long id);
    ResponseProduct getProduct(long id);

    List<ResponseProduct> getProductByName(String name);
}
