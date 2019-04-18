package com.robo.remoteacademy.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

import com.fasterxml.uuid.Generators;
import com.robo.remoteacademy.model.Student;
import com.robo.remoteacademy.model.Subject;
import com.robo.remoteacademy.model.SubjectJoiners;
import com.robo.remoteacademy.model.Teacher;
import com.robo.remoteacademy.repository.StudentRepository;
import com.robo.remoteacademy.repository.SubjectJoinersRepository;
import com.robo.remoteacademy.repository.TeacherRepository;
import com.robo.remoteacademy.util.MyPagination;

import java.util.List;
import java.util.Map;


@RestController
@RequestMapping("/test/")
public class TestController {

	@Autowired
	StudentRepository studentRepo;
	@Autowired
	TeacherRepository teacherRepo;
	
	@Autowired
	SubjectJoinersRepository subjectJoinersRepo;
	
	MyPagination myPagination=new MyPagination();
	

	ArrayList ar=new ArrayList();

	
	@RequestMapping("/")
	public String testId()
	{
		UUID uuid=Generators.timeBasedGenerator().generate();
		System.out.println("UUID : "+uuid);
		System.out.println("UUID Version : "+uuid.version());
		System.out.println("UUID Variant : "+uuid.variant());
		
		for(int i=0;i<=9;i++)
		{
		ar.add("UUID : "+uuid+"\nVersion : "+uuid.version()+"\nVariant : "+uuid.variant());
		}
		return ar.toString();
	}
	
	
	
	@RequestMapping("/search")
	public String testWithDatabase()
	{
		
		return studentRepo.findAllDeleted().get(0).getEmail().toString();
	}
	
	
	@RequestMapping("/addteacher")
	public String addTeacher()
	{
		
		Teacher t=new Teacher();
		Subject subject=new Subject();
		Set<Subject> s=new <Subject>HashSet();
		UUID uuid=Generators.timeBasedGenerator().generate();
		
		t.setTeacherId(uuid.toString());
		t.setName("sandeep");
		t.setDob("10/10/19");
		t.setMobile("9559999999");
		t.setEmail("teacher@gmail.com");
		t.setPassword("teacher");
		UUID uuid2=Generators.timeBasedGenerator().generate();
		
		subject.setSubjectId(uuid2.toString());
		subject.setTeacherId(t);
		subject.setSubjectName("java");
		subject.setDuration("7 PM to 8 PM");
		s.add(subject);
		t.setSubjects(s);
		teacherRepo.save(t);
		return "";
	}
	
	
	
	
	@RequestMapping("/file")
	public String fileTest()throws IOException
	{
		String uploadBase="data/images/web_uploads";
		String entityName="student";
		File f=new File(uploadBase);
		String beforeUploadBase=""+f.exists();
		if(!f.exists()){
			f.mkdirs();
		}
		
		File f2=new File(uploadBase,entityName);
		String beforeEntityName=""+f2.exists();
		if(!f2.exists()) {
			f2.mkdir();
		}
		
		FileOutputStream fo=new FileOutputStream(new File(f2,"san.txt"));
		fo.write(10);
		fo.flush();
		fo.close();
		String path=uploadBase+"/student/san.txt";
		FileInputStream fi=new FileInputStream(new File(path));
		
		return ""+fi.read();
	}
	
	
	
	@RequestMapping("/grouping")
	public String subjectJoiners(@RequestParam Map<String, String> requestParams)
	{
		
		Page<SubjectJoiners> page=subjectJoinersRepo.findAllSubjectWithGroupBy(myPagination.getPageable(requestParams),false);
		List<SubjectJoiners> list=page.getContent();
		String data="";
		for(SubjectJoiners subjectJoiners:list)
		{
			data=data+subjectJoiners.getSubjectId().getSubjectName()+" : "+subjectJoiners.getCounter()+"\n";
		}
		return data;
	}
	
	
	
	
}
