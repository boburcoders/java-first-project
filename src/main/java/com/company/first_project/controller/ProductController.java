package com.company.first_project.controller;

import com.company.first_project.dto.ApiResponse;
import com.company.first_project.dto.ProductDto;
import com.company.first_project.module.Products;
import com.company.first_project.service.ProductService;
import com.company.first_project.service.mapper.ProdMapper;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(value = "products")
public class ProductController {
    private final ProductService productService;
    private final ProdMapper prodMapper;

    public ProductController(ProductService productService, ProdMapper prodMapper) {
        this.productService = productService;
        this.prodMapper = prodMapper;

    }

    @RequestMapping(method = RequestMethod.POST)
    public ApiResponse<ProductDto> createProduct(@RequestBody ProductDto dto) {
        return this.productService.createProduct(dto);

    }

    @RequestMapping(method = RequestMethod.GET)
    public ApiResponse<ProductDto> getProductById(@RequestParam(value = "id") Integer prodId) {
        return this.productService.getProductById(prodId);
    }

    @RequestMapping(method = RequestMethod.PUT)
    public ApiResponse<ProductDto> updateProduct(
            @RequestParam(value = "id") Integer prodId,
            @RequestBody ProductDto dto) {

        return this.productService.updateProduct(prodId, dto);
    }

    @RequestMapping(method = RequestMethod.DELETE)
    public ApiResponse<ProductDto> deleteProduct(@RequestParam(value = "id") Integer prodId) {

        return this.productService.deleteProduct(prodId);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/get-all")
    public ApiResponse<List<ProductDto>> getAllProducts() {
        return this.productService.getAllProducts();
    }
}
