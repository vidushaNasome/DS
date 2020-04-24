package com.example.REST.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.REST.model.user;

public interface user_repositary extends JpaRepository<user, Integer>{

	user findBylogin(String login);

}
