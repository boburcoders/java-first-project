package com.company.first_project.service.impl;

import com.company.first_project.dto.ApiResponse;
import com.company.first_project.dto.ErrorDto;
import com.company.first_project.dto.ProductDto;
import com.company.first_project.exceptions.ContentNotFoundException;
import com.company.first_project.module.Products;
import com.company.first_project.repository.ProductRepository;
import com.company.first_project.service.ProductService;
import com.company.first_project.service.mapper.ProdMapper;
import com.company.first_project.service.validation.ProductValidation;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {
    private final ProdMapper prodMapper;
    private final ProductValidation productValidation;
    private final ProductRepository productRepository;


    @Override
    public ApiResponse<ProductDto> createProduct(ProductDto dto) {
        List<ErrorDto> errorDtoList = this.productValidation.productValidation(dto);
        if (!errorDtoList.isEmpty()) {
            return ApiResponse.<ProductDto>builder()
                    .code(-2)
                    .message("Validation Error")
                    .errorDtoList(errorDtoList)
                    .build();
        }

        Products products = this.prodMapper.toEntity(dto);
        products.setCreatedAt(LocalDate.now());
        Products savedProduct = this.productRepository.save(products);

        return ApiResponse.<ProductDto>builder()
                .success(true)
                .message("Ok")
                .content(this.prodMapper.toDto(savedProduct))
                .build();
    }

    @Override
    public ApiResponse<ProductDto> getProductById(Integer prodId) {
        Optional<Products> optionalProduct = this.productRepository.findByIdAndDeletedAtIsNull(prodId);
        if (optionalProduct.isEmpty()) {
            throw new ContentNotFoundException(String.format("This prodId %d not found", prodId));
        }
        Products products = optionalProduct.get();
        return ApiResponse.<ProductDto>builder()
                .success(true)
                .message("ok")
                .content(this.prodMapper.toDto(products))
                .build();
    }

    @Override
    public ApiResponse<ProductDto> updateProduct(Integer prodId, ProductDto dto) {
        Optional<Products> optionalProduct = this.productRepository.findByIdAndDeletedAtIsNull(prodId);
        if (optionalProduct.isEmpty()) {
            throw new ContentNotFoundException(String.format("This prodId %d not found", prodId));
        }
        Products product = optionalProduct.get();
        product.setUpdatedAt(LocalDate.now());
        Products updatedProduct = this.prodMapper.updateProductsAllFields(product, dto);
        Products savedProduct = this.productRepository.save(updatedProduct);

        return ApiResponse.<ProductDto>builder()
                .success(true)
                .message("Ok")
                .content(this.prodMapper.toDto(savedProduct))
                .build();

    }

    @Override
    public ApiResponse<ProductDto> deleteProduct(Integer prodId) {
        Optional<Products> optionalProduct = this.productRepository.findByIdAndDeletedAtIsNull(prodId);
        if (optionalProduct.isEmpty()) {
            throw new ContentNotFoundException(String.format("This prod %d id not found", prodId));
        }
        Products products = optionalProduct.get();
        products.setDeletedAt(LocalDate.now());
        Products savedProduct = this.productRepository.save(products);
        return ApiResponse.<ProductDto>builder()
                .success(true)
                .message("Deleted successfully")
                .content(this.prodMapper.toDto(savedProduct))
                .build();
    }

    @Override
    public ApiResponse<List<ProductDto>> getAllProducts() {
        List<Products> productsList1 = this.productRepository.findAllByDeletedAtIsNull();
        if (productsList1.isEmpty()) {
            throw new ContentNotFoundException("ProductList is Empty");
        }

        return ApiResponse.<List<ProductDto>>builder()
                .success(true)
                .message("Ok")
                .content(this.prodMapper.toDtoWithList(productsList1))
                .build();
    }

    public List<ProductDto> getAllProductsByUserId(Integer userId) {
        return this.prodMapper.toDtoWithList(
                this.productRepository.findAllByUserIdAndDeletedAtIsNull(userId)
        );
    }
}
