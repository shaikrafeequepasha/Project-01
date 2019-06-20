package com.persistent.searchengine.auth.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.persistent.searchengine.auth.model.Role;

public interface RoleRepository extends JpaRepository<Role, Long>{
}