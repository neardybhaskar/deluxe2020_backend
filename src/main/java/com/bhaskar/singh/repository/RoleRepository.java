package com.bhaskar.singh.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.bhaskar.singh.domain.security.Role;

@Repository
public interface RoleRepository extends CrudRepository<Role, Long> {

}
