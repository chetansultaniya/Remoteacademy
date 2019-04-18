package com.robo.remoteacademy.service;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.web.multipart.MultipartFile;

import com.robo.remoteacademy.model.Student;



public interface StudentService {

	
	List <Student>getAllStudent();
	
	String save(Student student,MultipartFile image,HttpSession session);
}
