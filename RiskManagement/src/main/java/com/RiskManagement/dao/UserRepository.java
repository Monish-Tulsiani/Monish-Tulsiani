package com.RiskManagement.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.RiskManagement.entities.User;



public interface UserRepository extends JpaRepository<User,Integer> {
    User findByUserName(String username);
}