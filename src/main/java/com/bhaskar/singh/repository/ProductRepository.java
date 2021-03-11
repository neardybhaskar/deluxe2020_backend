package com.bhaskar.singh.repository;

import com.bhaskar.singh.entity.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Optional;

/**
 * @author Bhaskar on 16-01-2021
 */
@Repository
public interface ProductRepository extends CrudRepository<Product, Long> {

    Optional<Product> findById(@RequestParam("id") Long id);

//    To search product by Name
    Page<Product> findByNameContaining(String name, org.springframework.data.domain.Pageable pageable);

//    To fetch list of records as per category_id, used when list of particular product is required
    Page<Product> findByProductCategoryId(Long category_id, Pageable pageable);

}
