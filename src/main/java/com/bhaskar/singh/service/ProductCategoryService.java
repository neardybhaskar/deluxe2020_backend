package com.bhaskar.singh.service;

import com.bhaskar.singh.entity.ProductCategory;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * @author Bhaskar Singh
 * @date 5/29/2021 1:51 PM
 */

@Service
public interface ProductCategoryService {

    Optional<ProductCategory> getProductCategory(Long productId);

}
