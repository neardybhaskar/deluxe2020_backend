package com.bhaskar.singh.service.impl;

import com.bhaskar.singh.entity.ProductCategory;
import com.bhaskar.singh.repository.ProductCategoryRepository;
import com.bhaskar.singh.service.ProductCategoryService;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * @author Bhaskar Singh
 * @date 5/29/2021 1:55 PM
 */

@Service
public class ProductCategoryServiceImpl implements ProductCategoryService {

    private final ProductCategoryRepository productCategoryRepository;

    public ProductCategoryServiceImpl(ProductCategoryRepository productCategoryRepository) {
        this.productCategoryRepository = productCategoryRepository;
    }

    @Override
    public Optional<ProductCategory> getProductCategory(Long productId) {
        return productCategoryRepository.findById(productId);
    }
}
