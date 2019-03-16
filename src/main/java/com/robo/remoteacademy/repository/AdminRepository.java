package com.robo.remoteacademy.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.robo.remoteacademy.model.Admin;

public interface AdminRepository extends JpaRepository<Admin, String>{

	Optional<Admin> findByEmail(String email);
	
}
