package com.bhaskar.singh.repository;

import com.bhaskar.singh.entity.CartItem;
import com.bhaskar.singh.entity.ShoppingCart;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

/**
 * @author Bhaskar Singh
 * @date 6/9/2021 9:25 PM
 */
public interface CartItemRepository extends CrudRepository<CartItem, Long> {

    Optional<List<CartItem>> findByShoppingCart(ShoppingCart shoppingCart);

}
