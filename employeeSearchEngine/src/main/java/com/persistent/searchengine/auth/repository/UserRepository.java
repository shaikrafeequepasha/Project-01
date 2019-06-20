package com.persistent.searchengine.auth.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.persistent.searchengine.auth.model.User;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByUsername(String username);
   
    List<User> findAll();
    
    List<User> findByUsernameContaining(String username);
   
     
}
