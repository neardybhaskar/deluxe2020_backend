package com.bhaskar.singh.service.impl;

import com.bhaskar.singh.entity.Product;
import com.bhaskar.singh.repository.ProductRepository;
import com.bhaskar.singh.service.ProductService;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;
import java.util.Optional;

/**
 * @author Bhaskar Singh
 * @date 5/29/2021 1:52 PM
 */

@Service
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;

    public ProductServiceImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public Product addProduct(Product product) {
        return productRepository.save(product);
    }

    @Override
    public Product findByProductName(String productName) {
        return productRepository.findByName(productName);
    }

    @Override
    public Optional<Product> findById(Long id) {
        return productRepository.findById(id);
    }

    @Override
    public void updateImage(Long id, String imageName) {
        Optional<Product> product = findById(id);
        if(!product.isPresent()) {
            throw new NoSuchElementException("Product with id " + id + "not found");
        }
        product.get().setImageUrl(imageName);
        productRepository.save(product.get());
    }

}
