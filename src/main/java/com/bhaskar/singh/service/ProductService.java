package com.bhaskar.singh.service;

import com.bhaskar.singh.entity.Product;
import org.springframework.stereotype.Service;

/**
 * @author Bhaskar Singh
 * @date 5/29/2021 1:48 PM
 */

@Service
public interface ProductService {

    void addProduct(Product product);

    Product findByProductName(String productName);

}
