package com.bhaskar.singh.controllers;

import com.bhaskar.singh.entity.Product;
import com.bhaskar.singh.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Bhaskar on 17-01-2021
 */
@RestController
public class ProductController {

    @Autowired
    private ProductRepository productRepository;

    @RequestMapping(method = RequestMethod.GET, value = "/search")
    public String searchProduct(@RequestParam("name") String productName) {
        System.out.println("test");
        return "bhaskar";
    }

}
