package com.bhaskar.singh.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * @author Bhaskar Singh
 * @date 6/9/2021 1:40 AM
 */

@RestController
public class ShoppingCarController {

    @RequestMapping(value = "/cart/add", method = RequestMethod.POST)
    public ResponseEntity<?> addProductToCart(
            @RequestBody Map<String, String> map
            ) {
        int productId = Integer.parseInt(map.get("productId"));
        int quantity = Integer.parseInt(map.get("quantity"));
        int userId = Integer.parseInt(map.get("userId"));

        return new ResponseEntity<>("Product Added Successfullt", HttpStatus.OK);
    }

}
