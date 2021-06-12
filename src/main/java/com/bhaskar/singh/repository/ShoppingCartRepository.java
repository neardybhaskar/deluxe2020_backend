package com.bhaskar.singh.repository;

import com.bhaskar.singh.entity.ShoppingCart;
import org.springframework.data.repository.CrudRepository;

/**
 * @author Bhaskar Singh
 * @date 6/9/2021 11:21 PM
 */
public interface ShoppingCartRepository extends CrudRepository<ShoppingCart, Long> {
}
