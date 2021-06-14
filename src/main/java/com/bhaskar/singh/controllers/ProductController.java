package com.bhaskar.singh.controllers;

import com.bhaskar.singh.entity.Product;
import com.bhaskar.singh.entity.ProductCategory;
import com.bhaskar.singh.service.ProductCategoryService;
import com.bhaskar.singh.service.ProductService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.http.HttpServletRequest;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.*;

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
    public Object addProduct(@RequestBody HashMap<String, String> map) {

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

        Product temp = productService.addProduct(product);
        Map<String, Long> hashMap = new HashMap<>();
        hashMap.put("id", temp.getId());
        hashMap.put("productCategoryId", temp.getProductCategory().getId());
        return hashMap;
    }

    //Upload product image
    @RequestMapping(value = "/upload/image/{productId}/{productCategoryId}", method = RequestMethod.POST)
    public ResponseEntity<?> uploadImage(@PathVariable("productId") Long productId,
                              @PathVariable("productCategoryId") Long productCategoryId,
                              HttpServletRequest request) {

        Optional<Product> product = productService.findById(productId);
        if(!product.isPresent()) {
            throw new NoSuchElementException();
        }
        try {
            MultipartHttpServletRequest multipartHttpServletRequest;
            if(!(request instanceof MultipartHttpServletRequest)) {
                return new ResponseEntity<>("Problems while Uploading", HttpStatus.BAD_REQUEST);
            }

            multipartHttpServletRequest = (MultipartHttpServletRequest)(request);
            Iterator<String> iterator = multipartHttpServletRequest.getFileNames();
            String fileName = null;
            while(iterator.hasNext()) {
                fileName = iterator.next();
            }

            String imageName = productId+".jpg";
            assert fileName != null;
            MultipartFile multipartFile = multipartHttpServletRequest.getFile(fileName);
            assert multipartFile != null;
            byte[] bytes = multipartFile.getBytes();
            BufferedOutputStream bufferedOutputStream = new BufferedOutputStream
                    (new FileOutputStream(new File("src/main/resources/static/images/product/"
                            +imageName)));
            bufferedOutputStream.write(bytes);
            bufferedOutputStream.close();

            product.get().setImageUrl(imageName);
            productService.updateImage(productId,imageName);

            return new ResponseEntity<>("Uploaded Successfully", HttpStatus.OK);
        } catch (Exception exception) {
            exception.getStackTrace();
            return new ResponseEntity<>("Problems while Uploading", HttpStatus.BAD_REQUEST);
        }
    }

    @RequestMapping(value = "/product/productCategoryName/{productId}", method = RequestMethod.GET)
    public Map<String, Object> getProductDetailsWithCategoryName(@PathVariable(name = "productId") long productId) {
        Optional<Product> productOptional = productService.findById(productId);
        if(!productOptional.isPresent()) {
            throw new NoSuchElementException();
        }
        Product product = productOptional.get();
        Map<String, Object> map = new HashMap<>();
        map.put("product", product);

        //Fetching product and productCategoryName together to the ui
        Optional<ProductCategory> productCategoryOptional = productCategoryService.
                getProductCategory(product.getProductCategory().getId());
        if(!productCategoryOptional.isPresent()) {
            throw new NoSuchElementException();
        }
        ProductCategory productCategory = productCategoryOptional.get();
        map.put("productCategory", productCategory);
        return map;
    }


}
