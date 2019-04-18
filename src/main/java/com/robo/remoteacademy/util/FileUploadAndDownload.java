package com.robo.remoteacademy.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.commons.io.IOUtils;

import com.robo.remoteacademy.model.Student;
import com.robo.remoteacademy.model.Teacher;
import com.robo.remoteacademy.repository.StudentRepository;
import com.robo.remoteacademy.repository.TeacherRepository;

public class FileUploadAndDownload {

	StudentRepository studentRepo;
	TeacherRepository teacherRepo;
	
	public String fileUpload(byte[] bytes,String extension,String uuid,String entityName,String columnName)
	{
		
String filename = columnName + "_" + uuid + "." + extension;
        
        try {
        	String uploadBase = "data/images/web_uploads";
            // Creating uploadBase Folder if Not Exist
            File uploadBaseDir = new File(uploadBase);            
            if(!uploadBaseDir.exists()){
               uploadBaseDir.mkdirs();
            }
            // Creating Entity Folder inside data if Not Exist
            File entityDir = new File(uploadBase,entityName);            
            if(!entityDir.exists()){
               entityDir.mkdir();
            }
            FileOutputStream fos = new FileOutputStream(new File(entityDir, filename));
            fos.write(bytes);
            fos.flush();
            fos.close();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return filename;
	}
	
	
	public byte[] getUploadFile(String entityName,String fileName) throws IOException
	{
        String uploadBase ="data/images/web_uploads/" + entityName + "/" + fileName;
        FileInputStream in = new FileInputStream(new File(uploadBase));
        return IOUtils.toByteArray(in);            
	}
	
	

}
