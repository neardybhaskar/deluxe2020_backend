package com.bhaskar.singh.controllers;

import com.bhaskar.singh.entity.CartItem;
import com.bhaskar.singh.entity.Product;
import com.bhaskar.singh.entity.User;
import com.bhaskar.singh.service.CartItemService;
import com.bhaskar.singh.service.ProductService;
import com.bhaskar.singh.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Optional;

/**
 * @author Bhaskar Singh
 * @date 6/9/2021 1:40 AM
 */

@RestController
public class ShoppingCarController {

    private final CartItemService cartItemService;

    private final ProductService productService;

    private final UserService userService;

    public ShoppingCarController(CartItemService cartItemService,
                                 ProductService productService,
                                 UserService userService) {
        this.cartItemService = cartItemService;
        this.productService = productService;
        this.userService = userService;
    }

    @RequestMapping(value = "/cart/add", method = RequestMethod.POST)
    public ResponseEntity<?> addProductToCart(
            @RequestBody Map<String, String> map
            ) {
        long productId = Long.parseLong(map.get("productId"));
        int quantity = Integer.parseInt(map.get("quantity"));
        long userId = Long.parseLong(map.get("userId"));

        Optional<Product> optionalProduct = productService.findById(productId);

        if(!optionalProduct.isPresent()) {
            throw new NoSuchElementException("Cannot find Product with id: "+productId);
        }
        Product product = optionalProduct.get();
        if(quantity > product.getUnitsInStock()) {
            return new ResponseEntity<>("Not enough Stock!!", HttpStatus.BAD_REQUEST);
        }
        cartItemService.addProductToCart(product, quantity, userId);

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @RequestMapping(value = "/cartItem/getQuantity", method = RequestMethod.POST)
    public int getCartItemCount(@RequestBody Map<String, Long> id) {
        long userId = id.get("userId");
        User user = userService.findById(userId);
        return user.getShoppingCart().getCartItem()
                .stream().mapToInt(CartItem::getQuantity).sum();
    }

}
