package com.robo.remoteacademy.controller;

import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.apache.jasper.tagplugins.jstl.core.Set;
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

@RestController
@RequestMapping("/admin/")
public class AdminController {

	@Autowired
	AdminRepository adminRepo;

	@RequestMapping(value = "/show/admin", method = RequestMethod.GET)
	public ModelAndView showAdmins() {
		ModelAndView mv = new ModelAndView("admins");

		mv.addObject("adminList", adminRepo.findAll());

		return mv;

	}

	@RequestMapping(value = "/show/admindashboard", method = RequestMethod.POST)
	public ModelAndView adminDashboard(@RequestParam("email") String email, @RequestParam("password") String password) {
		ModelAndView mv = new ModelAndView("admindashboard");
		ModelAndView mv2 = new ModelAndView("redirect:/remoteacademy/show/index");

		Admin admin = adminRepo.findByEmail(email).orElse(null);

		if (admin != null) {
			if (admin.getPassword().equals(password)) {
				return mv;
			} else {
				return mv2;
			}
		} else {
			return mv2;
		}

	}

	@RequestMapping(value = "/show/deleteadmin/{id}", method = RequestMethod.GET)
	public ModelAndView deleteAdmin(@PathVariable("id") String id) {
		ModelAndView mv = new ModelAndView("redirect:/admin/show/admins");

		adminRepo.deleteById(id);
		return mv;

	}
	
	
	
	
	
	
	
	
	int pageNo=0;
	int data=10;
	@RequestMapping(value = "/show/admins",method=RequestMethod.GET)
	public ModelAndView showStudents(@RequestParam Map<String,String> requestParams)
	{
		ModelAndView mv=new ModelAndView("admins");
		
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
		Page page=adminRepo.findAll(pageable);
		
		mv.addObject("admin", page.getContent());
		mv.addObject("totalPage",page.getTotalPages());
		
		return mv;
		
	}
	
	
	
	
	@RequestMapping(value = "/show/adminProfile/{id}",method=RequestMethod.GET)
	public ModelAndView studentProfile(@PathVariable("id") String id)
	{
		ModelAndView mv=new ModelAndView("adminProfile");
		
		Optional<Admin> option=adminRepo.findById(id);
		Admin admin=option.get();
		
		
		mv.addObject("admin",admin);
		
		return mv;
		
	}
	
	
	
	
	
	@RequestMapping(value = "/show/save",method = RequestMethod.POST)
	public ModelAndView saveStudent(@RequestParam Map<String,String> requestParams)
	{
		ModelAndView mv=new ModelAndView("redirect:/admin/show/admins");

		Admin admin;
		
		
		System.out.println(requestParams);
		
		if(requestParams.size()==6)
		{
			admin=new Admin();
			admin.setAdminId(requestParams.get("id"));
			admin.setPassword(requestParams.get("password"));
			admin.setName(requestParams.get("firstname")+" "+requestParams.get("lastname"));

		}
		else
		{
			admin=(Admin)adminRepo.findById(requestParams.get("id")).orElse(null);
			admin.setName(requestParams.get("name"));
		}
		
		
		if(admin!=null)
		{
			
			admin.setMobile(requestParams.get("mobile"));
			admin.setEmail(requestParams.get("email"));
			
			adminRepo.save(admin);
			
		}
		
		
		return mv;
	}
	
	

}
