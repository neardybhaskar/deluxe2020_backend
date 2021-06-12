package com.bhaskar.singh.service;

import com.bhaskar.singh.entity.CartItem;
import com.bhaskar.singh.entity.Product;
import org.springframework.stereotype.Service;

/**
 * @author Bhaskar Singh
 * @date 6/9/2021 9:10 PM
 */

@Service
public interface CartItemService {

    CartItem addProductToCart(Product product, int quantity, long userId);

}
