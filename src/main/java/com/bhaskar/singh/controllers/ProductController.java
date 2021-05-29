package com.bhaskar.singh.controllers;

import com.bhaskar.singh.entity.Product;
import com.bhaskar.singh.entity.ProductCategory;
import com.bhaskar.singh.service.ProductCategoryService;
import com.bhaskar.singh.service.ProductService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.NoSuchElementException;
import java.util.Optional;

/**
 * @author Bhaskar on 17-01-2021
 */
@RestController
public class ProductController {

    private final ProductCategoryService productCategoryService;

    private final ProductService productService;

    public ProductController(ProductCategoryService productCategoryService, ProductService productService) {
        this.productCategoryService = productCategoryService;
        this.productService = productService;
    }

    @RequestMapping(value = "/addProduct", method = RequestMethod.POST)
    public ResponseEntity<?> addProduct(@RequestBody HashMap<String, String> map) {

        String productName = map.get("productName");
        String addedDate = map.get("addedDate");
        String productDescription = map.get("productDescription");
        long productCost = Long.parseLong(map.get("price"));
        int productQuantiy = Integer.parseInt(map.get("stock"));
        long productCategoryId = Long.parseLong(map.get("productCategory"));

        Optional<ProductCategory> productCategory =  productCategoryService.getProductCategory(productCategoryId);
        if(!productCategory.isPresent())
            throw new NoSuchElementException();

        Product existingProduct = productService.findByProductName(productName);
        if(existingProduct != null) {
            return new ResponseEntity<>("Product already exists",HttpStatus.BAD_REQUEST);
        }

        Product product = new Product();
        product.setName(productName);
        product.setActive(true);
        product.setDescription(productDescription);
        product.setProductCategory(productCategory.get());
        product.setDateCreated(LocalDate.parse(addedDate));
        product.setLastUpdated(LocalDate.parse(addedDate));
        product.setUnitPrice(BigDecimal.valueOf(productCost));
        product.setUnitsInStock(productQuantiy);

        productService.addProduct(product);

        return new ResponseEntity<>(HttpStatus.OK);
    }

}
