package com.robo.remoteacademy.controller;

import java.util.Enumeration;
import java.util.Map;
import java.util.Optional;

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

import com.robo.remoteacademy.model.Student;
import com.robo.remoteacademy.model.Subject;
import com.robo.remoteacademy.model.Teacher;
import com.robo.remoteacademy.repository.SubjectRepository;
import com.robo.remoteacademy.repository.TeacherRepository;

@RestController
@RequestMapping("/subject/")
public class SubjectController {

	@Autowired
	SubjectRepository subjectRepo;
	@Autowired
	TeacherRepository teacherRepo;
	
	
	
	
	
	@RequestMapping(value = "/show/deletesubject/{id}",method=RequestMethod.POST)
	public ModelAndView deleteSubjects(@PathVariable("id") Object id)
	{
		ModelAndView mv=new ModelAndView("redirect:/subject/show/subjects");
		
		String subjectIdCollection=id.toString();
		
		
		if(subjectIdCollection.length()<2)
		{
		
			subjectRepo.deleteById(subjectIdCollection);
		}
		else
		{
		String 	subjectId[]=subjectIdCollection.split(",");
		
		for(String ids:subjectId)
		{
			subjectRepo.deleteById(ids);
			
		}
		
		}
		
		return mv;
		
	}
	
	
	
	
	
	
	
	int pageNo=0;
	int data=10;
	@RequestMapping(value = "/show/subjects",method=RequestMethod.GET)
	public ModelAndView showSubjects(@RequestParam Map<String,String> requestParams,HttpServletRequest request)
	{
		ModelAndView mv=new ModelAndView("subjects");
	
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
		Page page=subjectRepo.findAll(pageable);
		
		HttpSession session=request.getSession();
		
		mv.addObject("subjectList", page.getContent());
		mv.addObject("totalPage",page.getTotalPages());
		mv.addObject("adminDetail",session.getAttribute("name"));

		return mv;
		
	}
	
	
	@RequestMapping(value = "/show/subjectProfile/{id}",method=RequestMethod.GET)
	public ModelAndView subjectProfile(@PathVariable("id") String id)
	{
		ModelAndView mv=new ModelAndView("subjectProfile");
		
		Optional<Subject> option=subjectRepo.findById(id);
		Subject subject=option.get();
		
		mv.addObject("subject",subject);	
		return mv;	
	}
	
	
	
	@RequestMapping(value = "/show/save",method = RequestMethod.POST)
	public ModelAndView saveSubject(@RequestParam Map<String,String> requestParams)
	{
		ModelAndView mv=new ModelAndView("redirect:/subject/show/subjects");
		ModelAndView mv2=new ModelAndView("subjectRegistration");

		Subject subject;
		Teacher teacher;
		
		String subjectId=requestParams.get("subjectId");
		String teacherId=requestParams.get("teacherId");
		String subjectName=requestParams.get("subjectName");
		String subjectDuration=requestParams.get("subjectDuration");		
		String subjectDescription=requestParams.get("subjectDescription");
		String tutionFee=requestParams.get("tutionFee");
		
		subject=subjectRepo.findById(subjectId).orElse(null);
		teacher=teacherRepo.findById(teacherId).orElse(null);
		
		
		if(subject==null)
		{
			if(teacher!=null)
			{
				String subjectTimeFrom=requestParams.get("subjectTimeFrom");
				String subjectTimeTo=requestParams.get("subjectTimeTo");
				
				subject=new Subject();
				subject.setSubjectId(subjectId);
				subject.setTeacherId(teacher);
				subject.setSubjectName(subjectName);
				subject.setDuration(subjectDuration);
				subject.setTiming(subjectTimeFrom+" to "+subjectTimeTo);
				subject.setDescription(subjectDescription);
				subject.setTutionFee(tutionFee);
				subjectRepo.save(subject);
				mv.addObject("success","Subject Successfully Registered");
				return mv;
			}
			else
			{
				mv2.addObject("error","Teacher not found");
				return mv2;
			}
			
		}
		else if(requestParams.get("subjectTimeFrom")==null&&requestParams.get("subjectTimeTo")==null)
		{
			subject=subjectRepo.findById(subjectId).orElse(null);
			subject.setSubjectName(subjectName);
			subject.setDuration(subjectDuration);
			subject.setTiming(requestParams.get("timing"));
			subject.setDescription(subjectDescription);
			subject.setTutionFee(tutionFee);
			subjectRepo.save(subject);
			mv.addObject("success","Subject Update Successfully");
			return mv;
		}
		else
		{
			mv2.addObject("error","SubjectId Already Exist");
			return mv2;	
		}
		
	}
	
	
	
}
