package com.robo.remoteacademy.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.robo.remoteacademy.model.Student;

public interface StudentRepository extends JpaRepository<Student, String>{

	
	
}
