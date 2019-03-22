package com.robo.remoteacademy.controller;

import java.util.Enumeration;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

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

	

	public void createSession(HttpServletRequest request,Admin admin)
	{
		
		HttpSession session=request.getSession();
		request.getSession().setAttribute("email", admin.getEmail());
		request.getSession().setAttribute("name", admin.getName());
		request.getSession().setAttribute("id", admin.getAdminId());
	
	}
	public void removeSession(HttpServletRequest request)
	{
		HttpSession session=request.getSession();
		session.removeAttribute("email");
		session.removeAttribute("name");
		session.removeAttribute("id");
		System.out.println("session removed for this Admin");
	}
	
	
	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public ModelAndView login(HttpServletRequest request){
		
		ModelAndView mv=new ModelAndView("index");
		
		HttpSession session=request.getSession();
		removeSession(request);
		
		return mv;
		
	}
	
	
	@RequestMapping(value="/logout",method=RequestMethod.POST)
	public ModelAndView logOut(HttpServletRequest request){
		
		ModelAndView mv=new ModelAndView("index");
		
		HttpSession session=request.getSession();
		removeSession(request);
		
		return mv;
		
	}
	
	
	
	@RequestMapping(value = "/index",method = RequestMethod.GET)
	public ModelAndView adminIndex()
	{
		ModelAndView mv=new ModelAndView("index");
		
		return mv;
	}
	
	
	@RequestMapping(value="/show/admindashboard",method=RequestMethod.GET)
	public ModelAndView dashboard()
	{
		ModelAndView mv=new ModelAndView("index");
		return mv;
	}
	
	
	@RequestMapping(value = "/show/admindashboard", method = RequestMethod.POST)
	public ModelAndView adminDashboard(HttpServletRequest request,@RequestParam Map<String,String> requestParams) {
		ModelAndView mv = new ModelAndView("admindashboard");
		ModelAndView mv2 = new ModelAndView("index");

		HttpSession session=request.getSession();
		
		Admin admin;
		
		if(session.getAttribute("email")==null)
		{
			admin = adminRepo.findByEmail(requestParams.get("email")).orElse(null);
			if(admin!=null)
			{
				if (admin.getPassword().equals(requestParams.get("password"))) {
					createSession(request, admin);
					mv.addObject("adminDetail", admin.getName());
					return mv;
				} else {
					mv2.addObject("error", "You entered wrong Password");
					return mv2;
				}		
			}
			else
			{
				mv2.addObject("error", "Email not exist");
				return mv2;
			}
		}
		else
		{
		
			if(requestParams.get("email").equals((String)session.getAttribute("email")))
			{
				String sessionEmail=(String)session.getAttribute("email");
				admin = adminRepo.findByEmail(sessionEmail).orElse(null);
				mv.addObject("adminDetail", admin.getName());
				return mv;
			}
			else
			{
				removeSession(request);
				admin = adminRepo.findByEmail(requestParams.get("email")).orElse(null);
				createSession(request, admin);
				mv.addObject("adminDetail", admin.getName());
				return mv;
			}
			
		}

	}
	
	
	@RequestMapping(value="/show/showdashboard",method=RequestMethod.POST)
	public ModelAndView backToDashboard(HttpServletRequest request)
	{
		ModelAndView mv=new ModelAndView("admindashboard");
		
		HttpSession session=request.getSession();
		mv.addObject("adminDetail",session.getAttribute("name"));
		
		return mv;
	}
	
	@RequestMapping(value="/show/showdashboard",method=RequestMethod.GET)
	public ModelAndView showDashboard()
	{
		ModelAndView mv=new ModelAndView("index");
		return mv;
	}
	

	@RequestMapping(value = "/show/deleteadmin/{id}", method = RequestMethod.POST)
	public ModelAndView deleteAdmin(@PathVariable("id") Object id) {
		ModelAndView mv = new ModelAndView("redirect:/admin/show/admins");

       String adminIdCollection=id.toString();
		
		if(adminIdCollection.length()<2)
		{
			
			adminRepo.deleteById(adminIdCollection);
		}
		else
		{
		String 	adminId[]=adminIdCollection.split(",");
		
		for(String ids:adminId)
		{
			adminRepo.deleteById(ids);
		
		}
		
		}
		return mv;

	}
	
	
	
	
	int pageNo=0;
	int data=10;
	@RequestMapping(value = "/show/admins",method=RequestMethod.GET)
	public ModelAndView showAdmins(@RequestParam Map<String,String> requestParams,HttpServletRequest request)
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
		
		HttpSession session=request.getSession();
		
		
		mv.addObject("admin", page.getContent());
		mv.addObject("totalPage",page.getTotalPages());
		mv.addObject("adminDetail",session.getAttribute("name"));
		
		return mv;
		
	}
	
	
	
	
	@RequestMapping(value = "/show/adminProfile/{id}",method=RequestMethod.GET)
	public ModelAndView AdminProfile(@PathVariable("id") String id)
	{
		ModelAndView mv=new ModelAndView("adminProfile");
		
		Optional<Admin> option=adminRepo.findById(id);
		Admin admin=option.get();
		
		
		mv.addObject("admin",admin);
		
		return mv;
		
	}
	
	
	
	
	
	@RequestMapping(value = "/show/save",method = RequestMethod.POST)
	public ModelAndView saveAdmin(@RequestParam Map<String,String> requestParams,HttpServletRequest request)
	{
		ModelAndView mv=new ModelAndView("redirect:/admin/show/admins");

		Admin admin;
		
		
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
			
			HttpSession session=request.getSession();
			
			
			if(admin.getAdminId().equals(""+session.getAttribute("id")))
			{
				createSession(request, admin);	
			}
			
			
			
		}
		
		
		return mv;
	}
	
	

}
