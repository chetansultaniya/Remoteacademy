package com.robo.remoteacademy.controller;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
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
import com.robo.remoteacademy.repository.TeacherRepository;


@RestController
@RequestMapping("/remoteacademy/")
public class DemoController {

	@Autowired
	AdminRepository adminRepo;
	
	@Autowired
	StudentRepository studentRepo;
	
	@Autowired
	TeacherRepository teacherRepo;
	

	 


	@RequestMapping(value = "/show/index", method = RequestMethod.GET)
	public ModelAndView index() {
		ModelAndView mv = new ModelAndView("index");
		mv.addObject("title");
		return mv;
	}


	

}
