package com.robo.remoteacademy.repository;

import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.robo.remoteacademy.model.Student;

import antlr.collections.List;

public interface StudentRepository extends JpaRepository<Student, String>{

	@Query("select s from Student s where s.isDeleted=:isDeleted and s.email=:email")
	Optional<Student> findByEmail(String email,boolean isDeleted);
	
	@Query("select s from Student s where s.isDeleted=:isDeleted and s.studentId=:studentId")
	Optional<Student> findById(String studentId,boolean isDeleted);
	
	 @Query("select s from Student s where s.isDeleted=false")
	    Page<Student> findAll(Pageable pageable);
	 
	 @Query("select s from Student s where s.isDeleted=false")
	    java.util.List<Student> findAll();
	 
	 @Query("select s from Student s where s.isDeleted=true")
	    Page<Student> findAllDeleted(Pageable pageable);
	
	 @Query("select s from Student s where s.isDeleted=true")
	    java.util.List<Student> findAllDeleted();

}
