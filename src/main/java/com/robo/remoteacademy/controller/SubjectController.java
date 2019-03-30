package com.robo.remoteacademy.controller;

import java.util.Enumeration;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.jasper.tagplugins.jstl.core.Set;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
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
import com.robo.remoteacademy.model.Subject;
import com.robo.remoteacademy.model.Teacher;
import com.robo.remoteacademy.repository.SubjectRepository;
import com.robo.remoteacademy.repository.TeacherRepository;
import com.robo.remoteacademy.util.MyPagination;

@RestController
@RequestMapping("/subject/")
public class SubjectController {

	private static final Logger LOGGER = LogManager.getLogger(SubjectController.class);
	
	@Autowired
	SubjectRepository subjectRepo;
	@Autowired
	TeacherRepository teacherRepo;
	MyPagination myPagination=new MyPagination();
	
	
	
	@RequestMapping(value = "/show/deletesubject/{id}",method=RequestMethod.POST)
	public ModelAndView deleteSubjects(@PathVariable("id") Object id)
	{
		ModelAndView mv=new ModelAndView("redirect:/subject/show/subjects");
		
		String subjectIdCollection=id.toString();
		if(subjectIdCollection.length()<2)
		{
			subjectRepo.deleteById(subjectIdCollection);
			LOGGER.info("Subject Deleted with ID : "+id.toString());
		}
		else
		{
		String 	subjectId[]=subjectIdCollection.split(",");
		for(String ids:subjectId)
		{
			subjectRepo.deleteById(ids);
			LOGGER.info("Subject Deleted with ID : "+id.toString());
		}
		}
		
		return mv;
	}
	
	
	@RequestMapping(value = "/show/subjects",method=RequestMethod.GET)
	public ModelAndView showSubjects(@RequestParam Map<String,String> requestParams,HttpServletRequest request)
	{
		ModelAndView mv=new ModelAndView("subjects");
		Page page=subjectRepo.findAll(myPagination.getPageable(requestParams));
		HttpSession session=request.getSession();
		mv.addObject("subjectList", page.getContent());
		mv.addObject("totalPage",page.getTotalPages());
		mv.addObject("adminDetail",session.getAttribute("name"));

		LOGGER.info("Show Subjects");
		return mv;
	}
	
	
	@RequestMapping(value = "/show/subjectProfile/{id}",method=RequestMethod.GET)
	public ModelAndView subjectProfile(@PathVariable("id") String id)
	{
		ModelAndView mv=new ModelAndView("subjectProfile");
		Optional<Subject> option=subjectRepo.findById(id);
		Subject subject=option.get();
		mv.addObject("subject",subject);	
		LOGGER.info("Open Subject : "+subject.getSubjectId());

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
		String teacherEmail=requestParams.get("teacherEmail");
		String subjectName=requestParams.get("subjectName");
		String subjectDuration=requestParams.get("subjectDuration");		
		String subjectDescription=requestParams.get("subjectDescription");
		String tutionFee=requestParams.get("tutionFee");
		
		subject=subjectRepo.findById(subjectId).orElse(null);
		teacher=teacherRepo.findByEmail(teacherEmail).orElse(null);
		if(subject==null)
		{
			if(teacher!=null)
			{
				String subjectTimeFrom=requestParams.get("subjectTimeFrom");
				String subjectTimeTo=requestParams.get("subjectTimeTo");
				UUID uuid=Generators.timeBasedGenerator().generate();
				subject=new Subject();
				subject.setSubjectId(uuid.toString());
				subject.setTeacherId(teacher);
				subject.setSubjectName(subjectName);
				subject.setDuration(subjectDuration);
				subject.setTiming(subjectTimeFrom+" to "+subjectTimeTo);
				subject.setDescription(subjectDescription);
				subject.setTutionFee(tutionFee);
				subjectRepo.save(subject);
				mv.addObject("success","Subject Successfully Registered");
				LOGGER.info("\n\n\n\n******Adding New Subject******");
				LOGGER.info("Subject Id : "+subject.getSubjectId());
				LOGGER.info("Subject Name : "+subject.getSubjectName()+"\nTeacher Name : "+teacher.getName()+"\nTeacher Email : "+teacher.getEmail()+"\nDuration : "+subject.getDuration()+"\nSubject Timing : "+subject.getTiming()+"\nSubject Fee : "+subject.getTutionFee()+"\nSubject Description : "+subject.getDescription()+"\n");
				return mv;
			}
			else
			{
				mv2.addObject("error","Teacher not found");
				LOGGER.info("Teacher Not Found : "+teacherEmail);
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
			LOGGER.info("\n\n\n\n******Updating Subject******\nSubject Id : \""+subject.getSubjectId()+"\"");
			LOGGER.info("Subject Name : "+subject.getSubjectName()+"\nDuration : "+subject.getDuration()+"\nSubject Timing : "+subject.getTiming()+"\nSubject Fee : "+subject.getTutionFee()+"\nSubject Description : "+subject.getDescription()+"\n");
			return mv;
		}
		else
		{
			mv2.addObject("error","SubjectId Already Exist");
			LOGGER.info("Subject Id Already Exist :"+subject.getSubjectId());
			return mv2;	
		}
	}
	
}
