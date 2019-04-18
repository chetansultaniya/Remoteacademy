package com.robo.remoteacademy.repository;

import java.util.Optional;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.robo.remoteacademy.model.Admin;
import com.robo.remoteacademy.model.Student;
import com.robo.remoteacademy.model.Teacher;

public interface TeacherRepository extends JpaRepository<Teacher, String>{

	@Query("select t from Teacher t where t.isDeleted=:isDeleted and t.email=:email")
	Optional<Teacher> findByEmail(String email,boolean isDeleted);
	
	
	@Query("select t from Teacher t where t.isDeleted=:isDeleted and t.teacherId=:teacherId")
	Optional<Teacher> findById(String teacherId,boolean isDeleted);
	
	@Query("select t.email from Teacher t where t.isDeleted=false")
	List<Teacher> findEmail();
	
	@Query("select t from Teacher t where t.isDeleted=false")
    Page<Teacher> findAll(Pageable pageable);
 
    @Query("select t from Teacher t where t.isDeleted=false")
    java.util.List<Teacher> findAll();
 
    @Query("select t from Teacher t where t.isDeleted=true")
    Page<Teacher> findAllDeleted(Pageable pageable);

    @Query("select t from Teacher t where t.isDeleted=true")
    java.util.List<Teacher> findAllDeleted();
	
}
