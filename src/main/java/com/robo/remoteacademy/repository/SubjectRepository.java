package com.robo.remoteacademy.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.robo.remoteacademy.model.Student;
import com.robo.remoteacademy.model.Subject;
import com.robo.remoteacademy.model.Teacher;

public interface SubjectRepository extends JpaRepository<Subject, String>{
	
	
	
	 @Query("select s from Subject s where s.isDeleted=:isDeleted and s.teacherId=:teacherId")
	List<Subject> findByTeacherId(Teacher teacherId,boolean isDeleted);
	
	 @Query("select s from Subject s where s.isDeleted=:isDeleted and s.subjectId=:subjectId")
		Optional<Subject> findById(String subjectId,boolean isDeleted);
	 
	 @Query("select s from Subject s where s.isDeleted=false")
	    Page<Subject> findAll(Pageable pageable);
	 
	 @Query("select s from Subject s where s.isDeleted=false")
	    java.util.List<Subject> findAll();
	 
	 @Query("select s from Subject s where s.isDeleted=true")
	    Page<Subject> findAllDeleted(Pageable pageable);
	
	 @Query("select s from Subject s where s.isDeleted=true")
	    java.util.List<Subject> findAllDeleted();
}
