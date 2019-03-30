package com.robo.remoteacademy.controller;


import java.lang.management.MemoryType;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.Iterator;
import java.util.ListIterator;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

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

import com.fasterxml.uuid.Generators;
import com.robo.remoteacademy.model.Student;
import com.robo.remoteacademy.repository.StudentRepository;
import com.robo.remoteacademy.util.MyPagination;



@RestController
@RequestMapping("/student/")
public class StudentController {

	private static final Logger LOGGER = LogManager.getLogger(StudentController.class);
	
	@Autowired
	StudentRepository studentRepo;
	MyPagination myPagination=new MyPagination();


	public void createSession(HttpServletRequest request,Student student)
	{
		HttpSession session=request.getSession();
		LOGGER.info("Session Create for :"+student.getEmail());
		request.getSession().setAttribute("studentEmail", student.getEmail());
		request.getSession().setAttribute("studentName", student.getName());
		request.getSession().setAttribute("studentId", student.getStudentId());
	
		
	}
	public void removeSession(HttpServletRequest request)
	{
		HttpSession session=request.getSession();
		session.removeAttribute("studentEmail");
		session.removeAttribute("studentName");
		session.removeAttribute("studentId");
		LOGGER.info("\n-×-×-×-×-×-×-×-×-×-×-×-×-×-×-×-×-×-×-Session End for This Student-×-×-×-×-×-×-×-×-×-×-×-×-×-×-×-×-×-×-\n\n\n\n\n");
	}
	
	
	
	
	
	
	
	
	
	
	

	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public ModelAndView login() {
		ModelAndView mv = new ModelAndView("studentLogin");
		LOGGER.info("Opening Login Page for Student");
		return mv;
	}
	
	
	@RequestMapping(value="/logout",method=RequestMethod.POST)
	public ModelAndView logout(HttpServletRequest request)
	{
        ModelAndView mv=new ModelAndView("studentLogin");
		HttpSession session=request.getSession();
		removeSession(request);
		
		return mv;
	}
	
	
	@RequestMapping(value = "/index",method = RequestMethod.GET)
	public ModelAndView studentIndex()
	{
		ModelAndView mv=new ModelAndView("studentLogin");
		LOGGER.info("Opening Login Page for Student");
	
		return mv;
	}
	
	
	@RequestMapping(value="/show/studentdashboard",method=RequestMethod.GET)
	public ModelAndView dashboard()
	{
		ModelAndView mv=new ModelAndView("index");
		LOGGER.info("Opening Login Page for Student");
		
		return mv;
	}

	
	@RequestMapping(value="/show/studentdashboard",method=RequestMethod.POST)
	public ModelAndView studentDashboard(HttpServletRequest request,@RequestParam Map<String,String> requestParams)
	{
		ModelAndView mv2=new ModelAndView("studentLogin");
		ModelAndView mv=new ModelAndView("studentDashboard");
        HttpSession session=request.getSession();
		
		Student student;
		if(session.getAttribute("studentEmail")==null)
		{
			student = studentRepo.findByEmail(requestParams.get("email")).orElse(null);
			if(student!=null)
			{
				if (student.getPassword().equals(requestParams.get("password"))) {
					LOGGER.info("\n\n\n\n\n*************************************Session Start*****************************************\nAccessing By\nStudent Id : "+student.getStudentId()+"\nStudent Name : "+student.getName()+"\n");
					createSession(request, student);
					mv.addObject("studentDetail", student.getName());
					LOGGER.info("Opening Student DashBoard");
					return mv;
				} else {
					mv2.addObject("error", "You entered wrong Password");
					LOGGER.info(student.getEmail()+" : entered wrong Password");
					return mv2;
				}		
			}
			else
			{
				mv2.addObject("error", "Email not exist");
				LOGGER.info("Email Not Exist : "+requestParams.get("email"));
				return mv2;
			}
		}
		else
		{
		
			if(requestParams.get("email").equals((String)session.getAttribute("studentEmail")))
			{
				String sessionEmail=(String)session.getAttribute("studentEmail");
				student = studentRepo.findByEmail(sessionEmail).orElse(null);
				mv.addObject("studentDetail", student.getName());
				LOGGER.info("\n\n\n\n\n*************************************Session Start*****************************************\nAccessing By\nStudent Id : "+student.getStudentId()+"\nStudent Name : "+student.getName()+"\n");
				LOGGER.info("Opening Student DashBoard");
				return mv;
			}
			else
			{
				removeSession(request);
				student = studentRepo.findByEmail(requestParams.get("email")).orElse(null);
				LOGGER.info("\n\n\n\n\n*************************************Session Start*****************************************\nAccessing By\nStudent Id : "+student.getStudentId()+"\nStudent Name : "+student.getName()+"\n");
				createSession(request, student);
				mv.addObject("studentDetail", student.getName());
				LOGGER.info("Opening Student DashBoard");
				return mv;
			}
		}
     }
	
	
	@RequestMapping(value="/show/showdashboard",method=RequestMethod.GET)
	public ModelAndView showDashboard()
	{
		ModelAndView mv=new ModelAndView("index");
		LOGGER.info("Opening Login Page for Student");
		return mv;
	}

	
	@RequestMapping(value = "/show/deletestudent/{id}",method=RequestMethod.POST)
	public ModelAndView deleteStudents(@PathVariable("id") Object id)
	{
		ModelAndView mv=new ModelAndView("redirect:/student/show/students");
		String studentIdCollection=id.toString();

		Student student;
		if(studentIdCollection.length()<2)
		{
			student=studentRepo.findById(studentIdCollection).orElse(null);
			student.setDeleted(true);
			studentRepo.save(student);
			LOGGER.info("Student Deleted with ID : "+id.toString());
		}
		else
		{
		String 	studentId[]=studentIdCollection.split(",");
		for(String ids:studentId)
		{
			student=studentRepo.findById(ids).orElse(null);
			student.setDeleted(true);
			studentRepo.save(student);
			LOGGER.info("Student Deleted with ID : "+ids);
		}
		}
		
		return mv;
	}
	
	
	@RequestMapping(value="/recycle/restorestudents/{id}",method=RequestMethod.POST)
	public ModelAndView restoreStudents(@PathVariable("id") Object id)
	{
		ModelAndView mv=new ModelAndView("redirect:/student/recycle/student");
        String studentIdCollection=id.toString();
		
		Student student;
		if(studentIdCollection.length()<2)
		{
			student=studentRepo.findById(studentIdCollection).orElse(null);
			student.setDeleted(false);
			studentRepo.save(student);
			LOGGER.info("Student Restored with ID : "+id.toString());
		}
		else
		{
		String 	studentId[]=studentIdCollection.split(",");
		for(String ids:studentId)
		{
			student=studentRepo.findById(ids).orElse(null);
			student.setDeleted(false);
			studentRepo.save(student);
			LOGGER.info("Student Restored with ID : "+ids);
		}
		}
		
		return mv;
	}

	
	@RequestMapping(value="/recycle/harddeletestudents/{id}",method=RequestMethod.POST)
	public ModelAndView hardDeleteStudents(@PathVariable("id") Object id)
	{
		ModelAndView mv=new ModelAndView("redirect:/student/recycle/student");
        String studentIdCollection=id.toString();
		
		Student student;
		if(studentIdCollection.length()<2)
		{
			studentRepo.deleteById(studentIdCollection);
			LOGGER.info("Student Deleted Permanently with ID : "+id.toString());
		}
		else
		{
		String 	studentId[]=studentIdCollection.split(",");
		for(String ids:studentId)
		{
			studentRepo.deleteById(ids);
			LOGGER.info("Student Deleted Permanently with ID : "+ids);
		}
		}
		
		return mv;
	}

	
	@RequestMapping(value="/recycle/student",method=RequestMethod.GET)
	public ModelAndView studentRecycle(@RequestParam Map<String,String> requestParams,HttpServletRequest request)
	{
	    ModelAndView mv=new ModelAndView("recycleBin");
		Page page=studentRepo.findAllDeleted(myPagination.getPageable(requestParams));
		HttpSession session=request.getSession();
		
		mv.addObject("records", page.getContent());
		mv.addObject("contextDetail","Student");
		mv.addObject("totalPage",page.getTotalPages());
		mv.addObject("adminDetail",session.getAttribute("name"));
	 
	 return mv;
	}
	
	
	@RequestMapping(value = "/show/students",method=RequestMethod.GET)
	public ModelAndView showStudents(@RequestParam Map<String,String> requestParams,HttpServletRequest request)
	{
		ModelAndView mv=new ModelAndView("students");
		HttpSession session=request.getSession();
		Page page=studentRepo.findAll(myPagination.getPageable(requestParams));
		
		mv.addObject("studentList", page.getContent());
		mv.addObject("context","student");
		mv.addObject("totalPage",page.getTotalPages());
		mv.addObject("adminDetail",session.getAttribute("name"));
		LOGGER.info("Show Students List");
		
		return mv;
	}
	
	
	@RequestMapping(value = "/show/studentProfile/{id}",method=RequestMethod.GET)
	public ModelAndView studentProfile(@PathVariable("id") String id)
	{
		ModelAndView mv=new ModelAndView("studentProfile");
		Optional<Student> option=studentRepo.findById(id);
		Student student=option.get();
		mv.addObject("student",student);
		LOGGER.info("Open Student : "+student.getStudentId());

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
			UUID uuid=Generators.timeBasedGenerator().generate();
			student.setPassword(requestParams.get("password"));
			student.setStudentId(uuid.toString());
			student.setName(requestParams.get("firstname")+" "+requestParams.get("lastname"));
			LOGGER.info("\n\n\n\n******Adding New Student******");
			LOGGER.info("Student Id : "+student.getStudentId());
		}
		else
		{
			student=(Student)studentRepo.findById(requestParams.get("id")).orElse(null);
			student.setName(requestParams.get("name"));
			LOGGER.info("\n\n\n\n******Updating Student******\nStudent Id : \""+student.getStudentId()+"\"");
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
		Student saveResponse=null;
		try
		{
		saveResponse=studentRepo.save(student);
		LOGGER.info("Student Name : "+student.getName()+"\nStudent Password : "+student.getPassword()+"\nStudent Email : "+student.getEmail()+"\nStudent Mobile : "+student.getMobile()+"\nStudent DOB : "+student.getDob()+"\nStudent Gender : "+student.getGender()+"\nStudent Address : "+student.getAddress()+"\nStudent City : "+student.getCity()+"\nStudent State : "+student.getState()+"\nStudent Country : "+student.getCountry()+"\nStudent PinCode : "+student.getPincode()+"\nStudent Course : "+student.getCourse()+"\nStudent Class : "+student.getStudentClass()+"\nStudent Institute : "+student.getInstitute()+"\nStudent Saved Successfully\n\n\n\n");
		}
		catch(NullPointerException e)
		{
			LOGGER.info("Failed to Save Detail : You can't store Null");
		}
		catch(Exception e)
		{
			LOGGER.info("Failed to Save Detail : "+e);
		}
	}
		
		return mv;
	}
	
	
	@RequestMapping(value = "/show/studentRegistration",method = RequestMethod.POST)
	public ModelAndView studentRegistration()
	{
		ModelAndView mv=new ModelAndView("studentRegistration");
		LOGGER.info("Opening Student Registration");
		return mv;
	}
	
}



