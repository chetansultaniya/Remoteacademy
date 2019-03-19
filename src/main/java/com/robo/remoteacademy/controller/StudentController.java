package com.robo.remoteacademy.controller;


import java.util.ArrayList;
import java.util.Iterator;
import java.util.ListIterator;
import java.util.Map;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.robo.remoteacademy.model.Admin;
import com.robo.remoteacademy.model.Student;
import com.robo.remoteacademy.repository.AdminRepository;
import com.robo.remoteacademy.repository.StudentRepository;
import com.robo.remoteacademy.service.StudentService;

import antlr.collections.List;

@RestController
@RequestMapping("/student/")
public class StudentController {
	
	@Autowired
	StudentRepository studentRepo;
	
	

	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public ModelAndView adminDashboard() {
		ModelAndView mv = new ModelAndView("studentLogin");

		return mv;
	}

	
	@RequestMapping(value = "/index",method = RequestMethod.GET)
	public ModelAndView student()
	{
		ModelAndView mv=new ModelAndView("index");
		
		return mv;
	}
	
	int pageNo=0;
	int data=10;
	@RequestMapping(value = "/show/students",method=RequestMethod.GET)
	public ModelAndView showStudents(@RequestParam Map<String,String> requestParams,HttpServletRequest request)
	{
		ModelAndView mv=new ModelAndView("students");
		
		
		
		Pageable pageable=new PageRequest(pageNo,data);
		
		
		
		try
		{
		if(requestParams.isEmpty())
		{
		pageNo=0;
		data=10;
		 pageable=new PageRequest(pageNo, data);
	
		}
		else if(requestParams.containsKey("data")&&requestParams.containsKey("pageNo"))
		{
			if(requestParams.get("data").equals("")&&requestParams.get("pageNo").equals(""))
			{
				pageNo=0;
				data=10;
			}
			else if(requestParams.get("data").equals(""))
			{
				
				data=10;
		     pageNo=Integer.parseInt(requestParams.get("pageNo"))-1;
			}
			
			else if(requestParams.get("pageNo").equals(""))
			{
				pageNo=0;
				
				data=Integer.parseInt(requestParams.get("data"));	
			}
			else
			{
				
				data=Integer.parseInt(requestParams.get("data"));
				pageNo=Integer.parseInt(requestParams.get("pageNo"))-1;
			}
			
		 pageable=new PageRequest(pageNo, data);
		
		}
		else if(requestParams.containsKey("data"))
		{
		if(requestParams.get("data").equals(""))
		{
			pageNo=0;
			data=10;
		}
		else
		{
			pageNo=0;
			
			data=Integer.parseInt(requestParams.get("data"));
				
		}
		 pageable=new PageRequest(pageNo, data);
		
		}
		else if(requestParams.containsKey("pageNo"))
		{
	     if(requestParams.get("pageNo").equals(""))
	     {
	    	 pageNo=0;
	 		data=10;
	     }
	     else
	     {
	 		data=10;
	    	 pageNo=Integer.parseInt(requestParams.get("pageNo"))-1;
	 			 
	     }
		 pageable=new PageRequest(pageNo, data);
		}
		}
		catch(IllegalArgumentException e)
		{
			pageNo=0;
			data=10;
			pageable=new PageRequest(pageNo, data);
		}
		Page page=studentRepo.findAll(pageable);
		
		HttpSession session=request.getSession();
		
		
		
		mv.addObject("studentList", page.getContent());
		mv.addObject("totalPage",page.getTotalPages());
		mv.addObject("adminDetail",session.getAttribute("name"));

		
		return mv;
		
	}
	
	
	@RequestMapping(value = "/show/studentRegistration",method = RequestMethod.POST)
	public ModelAndView studentRegistration()
	{
		ModelAndView mv=new ModelAndView("studentRegistration");
		
		return mv;
	}
	
	
	@RequestMapping(value = "/show/save",method = RequestMethod.POST)
	public ModelAndView saveStudent(@RequestParam Map<String,String> requestParams)
	{
		ModelAndView mv=new ModelAndView("redirect:/student/show/students");

		Student student;
		
		
		if(requestParams.size()==17)
		{
			student=new Student();
			student.setPassword(requestParams.get("password"));
			student.setStudentId(requestParams.get("id"));
			student.setName(requestParams.get("firstname")+" "+requestParams.get("lastname"));

		
		}
		else
		{
			
			
			student=(Student)studentRepo.findById(requestParams.get("id")).orElse(null);
			student.setName(requestParams.get("name"));		
			
		}
		
	if(student!=null)
	{
		
		
		student.setMobile(requestParams.get("mobile"));
		student.setEmail(requestParams.get("email"));
		student.setDob(requestParams.get("dob"));
		student.setGender(requestParams.get("gender"));
		student.setAddress(requestParams.get("address"));
		student.setCity(requestParams.get("city"));
		student.setState(requestParams.get("state"));
		student.setCountry(requestParams.get("country"));
		student.setPincode(requestParams.get("pincode"));
		student.setCourse(requestParams.get("course"));
		student.setStudentClass(requestParams.get("class"));
		student.setInstitute(requestParams.get("institute"));
		Student saveResponse=studentRepo.save(student);
		
		
		
	}

		
		return mv;
	}
	
	
	@RequestMapping(value = "/show/deletestudent/{id}",method=RequestMethod.GET)
	public ModelAndView deleteStudent(@PathVariable("id") String id)
	{
		ModelAndView mv=new ModelAndView("redirect:/student/show/students");
		
		studentRepo.deleteById(id);
		return mv;
		
	}
	
	
	
	@RequestMapping(value = "/show/studentProfile/{id}",method=RequestMethod.GET)
	public ModelAndView studentProfile(@PathVariable("id") String id)
	{
		ModelAndView mv=new ModelAndView("studentProfile");
		
		Optional<Student> option=studentRepo.findById(id);
		Student student=option.get();
		
		
		mv.addObject("student",student);
		
		return mv;
		
	}
	
	
}



