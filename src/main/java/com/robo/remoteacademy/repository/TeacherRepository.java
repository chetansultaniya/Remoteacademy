package com.robo.remoteacademy.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.robo.remoteacademy.model.Admin;
import com.robo.remoteacademy.model.Teacher;

public interface TeacherRepository extends JpaRepository<Teacher, String>{

	Optional<Teacher> findByEmail(String email);
}
