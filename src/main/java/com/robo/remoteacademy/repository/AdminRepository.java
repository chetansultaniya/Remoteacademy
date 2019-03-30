package com.robo.remoteacademy.repository;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.robo.remoteacademy.model.Admin;
import com.robo.remoteacademy.model.Teacher;

public interface AdminRepository extends JpaRepository<Admin, String>{

	Optional<Admin> findByEmail(String email);


}
