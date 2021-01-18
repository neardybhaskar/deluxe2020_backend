package com.bhaskar.singh.repository;

import com.bhaskar.singh.entity.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Optional;

/**
 * @author Bhaskar on 16-01-2021
 */
@Repository
@CrossOrigin("http://localhost:4200")
public interface ProductRepository extends CrudRepository<Product, Long> {

    Optional<Product> findById(@RequestParam("id") Long id);

    Page<Product> findByNameContaining(String name, org.springframework.data.domain.Pageable pageable);

}