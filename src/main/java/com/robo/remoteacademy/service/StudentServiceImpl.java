package com.robo.remoteacademy.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.robo.remoteacademy.model.Student;
import com.robo.remoteacademy.repository.StudentRepository;

@Service
public class StudentServiceImpl implements StudentService{

	@Autowired
	private StudentRepository studentRepo;
	
	public List<Student> getAllStudent()
	{
		
		
		return studentRepo.findAll();
	}
	
}
