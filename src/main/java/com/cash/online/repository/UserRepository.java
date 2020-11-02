package com.cash.online.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cash.online.model.User;

public interface UserRepository extends JpaRepository<User, Long>{

}
