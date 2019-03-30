package com.robo.remoteacademy.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.UUID;

import com.fasterxml.uuid.Generators;
import com.robo.remoteacademy.model.Student;
import com.robo.remoteacademy.repository.StudentRepository;

import antlr.collections.List;


@RestController
@RequestMapping("/test/")
public class TestController {

	ArrayList ar=new ArrayList();
	@RequestMapping("/")
	public String testId()
	{
		UUID uuid=Generators.timeBasedGenerator().generate();
		System.out.println("UUID : "+uuid);
		System.out.println("UUID Version : "+uuid.version());
		System.out.println("UUID Variant : "+uuid.variant());
		
		for(int i=0;i<=9;i++)
		{
		ar.add("UUID : "+uuid+"\nVersion : "+uuid.version()+"\nVariant : "+uuid.variant());
		}
		return ar.toString();
	}
	
	
	@Autowired
	StudentRepository studentRepo;
	
	@RequestMapping("/search")
	public String testWithDatabase()
	{
		
		return studentRepo.findAllDeleted().get(0).getEmail().toString();
	}
	
	
	
	
}
