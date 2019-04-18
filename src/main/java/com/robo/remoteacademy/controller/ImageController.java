package com.robo.remoteacademy.controller;

import java.io.IOException;
import java.util.Base64;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.robo.remoteacademy.model.Student;
import com.robo.remoteacademy.model.Teacher;
import com.robo.remoteacademy.repository.StudentRepository;
import com.robo.remoteacademy.repository.TeacherRepository;
import com.robo.remoteacademy.util.FileUploadAndDownload;


@RestController
@RequestMapping("/image/")
public class ImageController {

	@Autowired
	StudentRepository studentRepo;
	@Autowired
	TeacherRepository teacherRepo;
	
	@RequestMapping(value="/get",method=RequestMethod.POST,produces = MediaType.IMAGE_JPEG_VALUE)
	public @ResponseBody String getImage(@RequestParam String entity,@RequestParam String id,HttpServletRequest request) throws IOException
	{
		
		try
		{
		byte [] imageBytes=new FileUploadAndDownload().getUploadFile(entity, imageName(entity,id));
		String base64Encoded=Base64.getEncoder().encodeToString(imageBytes);
		
		 return base64Encoded;
    } catch (IOException e) {
System.out.println(e);
    }
    return null;
	}
	
	
	private String imageName(String entityName,String entityId)
    {
        String fileName = "";
        
        switch(entityName)
        {
        case "student":
			Student student = studentRepo.findById(entityId).orElse(null); 
            fileName = student.getImage();
            break;
        case "teacher":
            Teacher teacher = teacherRepo.findById(entityId).orElse(null); 
            fileName = teacher.getImage();
            break;
 
        }
        
        return fileName;
    }
	
}
