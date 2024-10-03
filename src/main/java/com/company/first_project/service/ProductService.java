package com.company.first_project.service;

import com.company.first_project.dto.ApiResponse;
import com.company.first_project.dto.ProductDto;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ProductService {
    ApiResponse<ProductDto> createProduct(ProductDto dto);
    ApiResponse<ProductDto> getProductById(Integer prodId);
    ApiResponse<ProductDto> updateProduct(Integer prodId, ProductDto dto);
    ApiResponse<ProductDto> deleteProduct(Integer prodId);
    ApiResponse<List<ProductDto>> getAllProducts();
}
