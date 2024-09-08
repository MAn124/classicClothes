package com.ttma.classicClothes.Service.Impl;

import com.ttma.classicClothes.Service.ProductService;
import com.ttma.classicClothes.dto.request.ProductRequest;
import com.ttma.classicClothes.dto.response.ResponsePage;
import com.ttma.classicClothes.dto.response.ResponseProduct;
import com.ttma.classicClothes.model.Category;
import com.ttma.classicClothes.model.Product;
import com.ttma.classicClothes.repository.CategoryRepository;
import com.ttma.classicClothes.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {
    private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository;
    private static final String UPLOAD_DIR = "src/main/resources/static/images";

    @Override
    public ResponsePage<?> getAllProduct(int pageNo, int pageSize) {
        Pageable pageable = PageRequest.of(pageNo, pageSize);
        Page<Product> products = productRepository.findAll(pageable);
      List<ResponseProduct> productList =  products.map(product -> ResponseProduct.builder()
                .name(product.getName())
                .description(product.getDescription())
                .price(product.getPrice())
                .quantity(product.getQuantity())
                .image(product.getImage())
                .categoryId(product.getCategory().getId())
                .build()).toList();
        return  ResponsePage.builder()
                .pageNo(pageNo)
                .pageSize(pageSize)
                .totalPage(products.getTotalPages())
                .items(productList)
                .build();
    }

    @Override
    public long createProduct(ProductRequest request, MultipartFile image) throws IOException {
        Product product = new Product();
        if(image != null && !image.isEmpty()){
            String fileName= saveImage(image);
            product.setImage("/images/"+fileName);
        }
        product.setName(request.getName());
        product.setDescription(request.getDescription());
        product.setPrice(request.getPrice());
        product.setQuantity(request.getQuantity());
        product.setCategory(categoryRepository.findById(request.getCategoryId()).orElseThrow(() -> new RuntimeException("Category not found")));
        productRepository.save(product);
        return product.getId();
    }

    @Override
    public long updateProduct(long id, ProductRequest request, MultipartFile image) throws IOException {
        Product product = findProductById(id);
        if(image != null && !image.isEmpty()){
            String fileName= saveImage(image);
            product.setImage("/images/"+fileName);
        }
        product.setName(request.getName());
        product.setDescription(request.getDescription());
        product.setPrice(request.getPrice());
        product.setQuantity(request.getQuantity());
        product.setCategory(categoryRepository.findById(request.getCategoryId()).orElseThrow(() -> new RuntimeException("Category not found")));
        productRepository.save(product);
        return product.getId();
    }

    @Override
    public void deleteProduct(long id) {
        productRepository.deleteById(id);
    }

    @Override
    public ResponseProduct getProduct(long id) {
        Product product = findProductById(id);
        return ResponseProduct.builder()
                .name(product.getName())
                .description(product.getDescription())
                .price(product.getPrice())
                .quantity(product.getQuantity())
                .categoryId(product.getCategory().getId())
                .build();
    }

    @Override
    public List<ResponseProduct> getProductByName(String name) {
          List<Product>  products =  productRepository.findProductByName(name);
        return products.stream().map(product -> ResponseProduct.builder()
                .name(product.getName())
                .description(product.getDescription())
                .price(product.getPrice())
                .quantity(product.getQuantity())
                .categoryId(product.getCategory().getId())
                .build()).toList();
    }


    private String saveImage(MultipartFile image) throws IOException {
        String fileName = UUID.randomUUID().toString()+"_" + image.getOriginalFilename();
        Path path = Paths.get(UPLOAD_DIR + fileName);
        Files.createDirectories(path.getParent());
        Files.write(path, image.getBytes());
        return fileName;
    }
    private Product findProductById(long id){
        return productRepository.findById(id).orElseThrow(() -> new RuntimeException("Product not found"));
    }
}
