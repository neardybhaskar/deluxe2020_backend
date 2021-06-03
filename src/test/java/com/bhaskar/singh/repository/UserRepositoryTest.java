package com.bhaskar.singh.repository;

import com.bhaskar.singh.domain.security.Role;
import com.bhaskar.singh.domain.security.UserRole;
import com.bhaskar.singh.entity.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author Bhaskar Singh
 * @date 6/2/2021 10:21 PM
 */
class UserRepositoryTest {

    @Autowired
    UserRepository userRepository;



    @Test
    void findByEmail() {


        User user = new User();
        user.setId(10L);
        user.setEmail("abc@gmail.com");
        user.setFirstName("test");
        user.setLastName("test");
        user.setPassword("test");
        user.setEnabled(false);

        Role role = new Role();
        role.setId(1L);
        role.setName("USER");

        UserRole userRole = new UserRole(user, role);
        Set<UserRole> userRoleSet = new HashSet<>();
        userRoleSet.add(userRole);
        user.setUserRoles(userRoleSet);

        userRepository.save(user);

        userRepository.findByEmail("singhbhaskar209@gmail.com");

    }
}
