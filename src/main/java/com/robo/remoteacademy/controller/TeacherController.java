package com.robo.remoteacademy.controller;

import java.util.Enumeration;
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
import com.robo.remoteacademy.model.Teacher;
import com.robo.remoteacademy.repository.TeacherRepository;

@RestController
@RequestMapping("/teacher")
public class TeacherController {

	@Autowired
	TeacherRepository teacherRepo;
	

	
	
	
	public void createSession(HttpServletRequest request,Teacher teacher)
	{
		request.getSession().setAttribute("teacherEmail", teacher.getEmail());
		request.getSession().setAttribute("teacherName", teacher.getName());
		request.getSession().setAttribute("teacherId", teacher.getTeacherId());
	}
	
	public void removeSession(HttpServletRequest request)
	{
		HttpSession session=request.getSession();
		
		session.removeAttribute("teacherEmail");
		session.removeAttribute("teacherName");
		session.removeAttribute("teacherId");
		System.out.println("session removed for this Teacher");
	}
	
	
	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public ModelAndView login() {
		ModelAndView mv = new ModelAndView("teacherLogin");

		return mv;
	}
	
	
	
	@RequestMapping(value="/logout",method=RequestMethod.POST)
	public ModelAndView logOut(HttpServletRequest request){
		
		ModelAndView mv=new ModelAndView("teacherLogin");
		
		HttpSession session=request.getSession();
		removeSession(request);
	
		
		return mv;
		
	}
	
	
	@RequestMapping(value="/index",method=RequestMethod.GET)
	public ModelAndView adminIndex(){
		
		ModelAndView mv=new ModelAndView("teacherLogin");
		return mv;
		
	}
	
	
	
	@RequestMapping(value="/show/teacherdashboard",method=RequestMethod.GET)
	public ModelAndView dashboard()
	{
		ModelAndView mv=new ModelAndView("teacherLogin");
		return mv;
	}
	
	
	@RequestMapping(value = "/show/teacherdashboard", method = RequestMethod.POST)
	public ModelAndView teacherDashboard(HttpServletRequest request,@RequestParam Map<String,String> requestParams) {
		ModelAndView mv = new ModelAndView("teacherDashboard");
		ModelAndView mv2 = new ModelAndView("teacherLogin");

		HttpSession session=request.getSession();
		
		Teacher teacher;
		
		if(session.getAttribute("teacherEmail")==null)
		{
			teacher = teacherRepo.findByEmail(requestParams.get("email")).orElse(null);
			if(teacher!=null)
			{
				if (teacher.getPassword().equals(requestParams.get("password"))) {
					createSession(request, teacher);
					mv.addObject("teacherDetail", teacher.getName());
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
		
			if(requestParams.get("email").equals((String)session.getAttribute("teacherEmail")))
			{
				String sessionEmail=(String)session.getAttribute("teacherEmail");
				teacher = teacherRepo.findByEmail(sessionEmail).orElse(null);
				mv.addObject("teacherDetail", teacher.getName());
				return mv;
			}
			else
			{
				removeSession(request);
				teacher = teacherRepo.findByEmail(requestParams.get("email")).orElse(null);
				createSession(request, teacher);
				mv.addObject("teacherDetail", teacher.getName());
				return mv;
			}
			
		}

	}
	
	
	
	
	@RequestMapping(value="/show/showdashboard",method=RequestMethod.GET)
	public ModelAndView showDashboard()
	{
		ModelAndView mv=new ModelAndView("index");
		return mv;
	}
	

	
	@RequestMapping(value = "/show/deleteteacher/{id}",method=RequestMethod.POST)
	public ModelAndView deleteTeacher(@PathVariable("id") Object id)
	{
		ModelAndView mv=new ModelAndView("redirect:/teacher/show/teachers");
		
		String teacherIdCollection=id.toString();
		
		if(teacherIdCollection.length()<2)
		{
			
			teacherRepo.deleteById(teacherIdCollection);
		}
		else
		{
		String 	teacherId[]=teacherIdCollection.split(",");
		
		for(String ids:teacherId)
		{
			teacherRepo.deleteById(ids);
			
		}
		
		}
		return mv;
		
	}
	
	
	
	
	int x=4;
	int pageNo=0;
	int data=10;
	@RequestMapping(value = "/show/teachers",method=RequestMethod.GET)
	public ModelAndView showTeachers(@RequestParam Map<String, String> requestParams,HttpServletRequest request)
	{
		ModelAndView mv=new ModelAndView("teachers");
		
		
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
		Page page=teacherRepo.findAll(pageable);
		
		HttpSession session=request.getSession();
		
		mv.addObject("teacher", page.getContent());
		mv.addObject("totalPage",page.getTotalPages());
		mv.addObject("adminDetail",session.getAttribute("name"));
		
		
		
		return mv;
		
	}
	
	
	
	
	@RequestMapping(value = "/show/teacherProfile/{id}",method=RequestMethod.GET)
	public ModelAndView teacherProfile(@PathVariable("id") String id)
	{
		ModelAndView mv=new ModelAndView("teacherProfile");
		
		Optional<Teacher> option=teacherRepo.findById(id);
		Teacher teacher=option.get();
		
		
		mv.addObject("teacher",teacher);
		
		return mv;
		
	}
	
	
	
	
	@RequestMapping(value = "/show/save",method = RequestMethod.POST)
	public ModelAndView saveTeacher(@RequestParam Map<String,String> requestParams)
	{
		ModelAndView mv=new ModelAndView("redirect:/teacher/show/teachers");

		
Teacher teacher;
		
		
		if(requestParams.size()==17)
		{
			teacher=new Teacher();
			teacher.setPassword(requestParams.get("password"));
			teacher.setTeacherId(requestParams.get("id"));
			teacher.setName(requestParams.get("firstname")+" "+requestParams.get("lastname"));

			
		}
		else
		{
			
			
			teacher=(Teacher)teacherRepo.findById(requestParams.get("id")).orElse(null);
			teacher.setName(requestParams.get("name"));	
		}
		
	if(teacher!=null)
	{
		
			
		teacher.setMobile(requestParams.get("mobile"));
		teacher.setEmail(requestParams.get("email"));
		teacher.setDob(requestParams.get("dob"));
		teacher.setGender(requestParams.get("gender"));
		teacher.setAddress(requestParams.get("address"));
		teacher.setCity(requestParams.get("city"));
		teacher.setState(requestParams.get("state"));
		teacher.setCountry(requestParams.get("country"));
		teacher.setPincode(requestParams.get("pincode"));
		teacher.setOccupation(requestParams.get("occupation"));
		teacher.setQualification(requestParams.get("qualification"));
		teacher.setExperience(requestParams.get("experience"));
		Teacher saveResponse=teacherRepo.save(teacher);
		
		
		
	}
		
		return mv;
	}
	

	
	
	
	
	
	
}
