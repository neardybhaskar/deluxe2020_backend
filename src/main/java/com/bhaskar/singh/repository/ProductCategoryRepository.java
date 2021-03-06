package com.bhaskar.singh.repository;

import com.bhaskar.singh.entity.ProductCategory;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * @author Bhaskar on 16-01-2021
 */
@Repository
public interface ProductCategoryRepository extends CrudRepository<ProductCategory, Long> {

}
