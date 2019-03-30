package com.robo.remoteacademy.util;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import com.robo.remoteacademy.controller.AdminController;
import com.robo.remoteacademy.controller.StudentController;
import com.robo.remoteacademy.controller.SubjectController;
import com.robo.remoteacademy.controller.TeacherController;
import com.robo.remoteacademy.model.Admin;
import com.robo.remoteacademy.model.Student;
import com.robo.remoteacademy.model.Subject;
import com.robo.remoteacademy.model.Teacher;
import com.robo.remoteacademy.repository.AdminRepository;
import com.robo.remoteacademy.repository.StudentRepository;
import com.robo.remoteacademy.repository.SubjectRepository;
import com.robo.remoteacademy.repository.TeacherRepository;

public class MyPagination {

	@Autowired
	StudentRepository studentRepo;
	@Autowired
	TeacherRepository teacherRepo;
	@Autowired
	AdminRepository adminRepo;
	@Autowired
	SubjectRepository subjectRepo;
	
	private Pageable pageable;
	private int pageNo=0;
	private int data=10;
	public Pageable getPageable(Map<String,String> requestParams)
	{		
		pageable=new PageRequest(pageNo,data);
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
		
		return pageable;
	}
	
}
