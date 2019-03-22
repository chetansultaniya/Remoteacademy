package com.robo.remoteacademy.repository;

import java.util.Optional;


import org.springframework.data.jpa.repository.JpaRepository;


import com.robo.remoteacademy.model.Student;

public interface StudentRepository extends JpaRepository<Student, String>{

	Optional<Student> findByEmail(String email);
	
}
