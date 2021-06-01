package com.bhaskar.singh.service;

import com.bhaskar.singh.entity.Product;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * @author Bhaskar Singh
 * @date 5/29/2021 1:48 PM
 */

@Service
public interface ProductService {

    Product addProduct(Product product);

    Product findByProductName(String productName);

    Optional<Product> findById(Long id);

    void updateImage(Long id, String imageName);

}
