package com.persistent.searchengine.auth.service;

import java.util.List;

import com.persistent.searchengine.auth.model.User;

public interface UserService {
    
	void save(User user);
    User findByUsername(String username);
    List<User> findAll();
    List<User> findByUsernameContaining(String username);
    
}
