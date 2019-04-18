package com.robo.remoteacademy.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.robo.remoteacademy.model.Student;
import com.robo.remoteacademy.model.Subject;
import com.robo.remoteacademy.model.SubjectJoiners;

public interface SubjectJoinersRepository extends JpaRepository<SubjectJoiners, String>{

	
	 @Query("select s from SubjectJoiners s where s.isDeleted=:isDeleted")
	    Page<SubjectJoiners> findAll(Pageable pageable,boolean isDeleted);
	 
	 @Query("select s from SubjectJoiners s where s.isDeleted=:isDeleted")
	    java.util.List<SubjectJoiners> findAll(boolean isDeleted);
	 
	 @Query("select new SubjectJoiners(s.subjectId,count(s.subjectJoinerId)) from SubjectJoiners s  where s.isDeleted=:isDeleted GROUP BY s.subjectId")
	 Page<SubjectJoiners> findAllSubjectWithGroupBy(Pageable pageable,boolean isDeleted);
	 @Query("select s from SubjectJoiners s where s.subjectId=:subjectId and s.isDeleted=:isDeleted")
	 Page<SubjectJoiners> findAllBySubjectId(Pageable pageable,Subject subjectId,boolean isDeleted);
	 
	 @Query("select s from SubjectJoiners s where s.subjectId=:subjectId and s.isDeleted=:isDeleted")
	 java.util.List<SubjectJoiners> findAllBySubjectId(Subject subjectId,boolean isDeleted);

	 @Query("select s from SubjectJoiners s where s.studentId=:studentId and s.isDeleted=:isDeleted")
	 java.util.List<SubjectJoiners> findAllByStudentId(Student studentId,boolean isDeleted);
	
}
