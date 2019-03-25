package com.robo.remoteacademy.controller;


import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
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

	private static final Logger LOGGER = LogManager.getLogger(DemoController.class);
	
	@Autowired
	AdminRepository adminRepo;
	
	@Autowired
	StudentRepository studentRepo;
	
	@Autowired
	TeacherRepository teacherRepo;
	


	@RequestMapping(value = "/show/index", method = RequestMethod.GET)
	public ModelAndView index(HttpServletRequest request) {
		ModelAndView mv = new ModelAndView("index");
		LOGGER.info("Opening admin dashboard");
		ModelAndView mv2=new ModelAndView("admindashboard");
		
		
		
HttpSession session=request.getSession();
		
		Admin admin;

		if(session.getAttribute("email")!=null)
		{
			String sessionEmail=(String)session.getAttribute("email");
			admin = adminRepo.findByEmail(sessionEmail).orElse(null);
			mv.addObject("adminDetail", admin);
			return mv2;
		}
		else
		{
			mv.addObject("title");
			return mv;
			
		}
	
	}


	

}
