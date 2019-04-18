package com.robo.remoteacademy.service;

import java.io.IOException;
import java.util.List;
import java.util.UUID;
import java.util.Date;

import javax.servlet.http.HttpSession;
import javax.transaction.Transactional;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import com.fasterxml.uuid.Generators;
import com.robo.remoteacademy.model.Student;
import com.robo.remoteacademy.repository.StudentRepository;
import com.robo.remoteacademy.util.FileUploadAndDownload;

@Service
public class StudentServiceImpl implements StudentService {

	private static final Logger LOGGER = LogManager.getLogger(StudentServiceImpl.class.getName());

	@Autowired
	private StudentRepository studentRepo;

	public List<Student> getAllStudent() {
		return studentRepo.findAll();
	}

	@Transactional
	public String save(Student student, MultipartFile image,HttpSession session) {
		String studentUUID;
		
		if (student.getStudentId() == null || student.getStudentId().equals("")) {
			UUID uuid = Generators.timeBasedGenerator().generate();
			studentUUID = uuid.toString();
			student.setStudentId(studentUUID);
			student.setCreatedBy(session.getAttribute("email").toString());
			student.setModifiedBy(session.getAttribute("email").toString());
			student.setCreatedOn(new Date());
			student.setModifiedOn(new Date());
			LOGGER.info("Saving New Student \n"+student);
		} else {
			studentUUID = student.getStudentId();
			Student existingStudent = studentRepo.findById(studentUUID).orElse(null);
			student.setImage(existingStudent.getImage());
			student.setCreatedBy(existingStudent.getCreatedBy());
			student.setModifiedBy(session.getAttribute("email").toString());
			student.setCreatedOn(existingStudent.getCreatedOn());
			student.setModifiedOn(new Date());
			LOGGER.info("Updating Student \n"+existingStudent);
		}

		if (!image.isEmpty() && image != null) {
			try {
				byte[] imageBytes = image.getBytes();
				String imageName = image.getOriginalFilename();
				String imageExt = imageName.substring(imageName.lastIndexOf(".") + 1);
				FileUploadAndDownload fileUploadAndDownload = new FileUploadAndDownload();
				String uploadImageName = fileUploadAndDownload.fileUpload(imageBytes, imageExt, studentUUID, "student",
						"image");
				student.setImage(uploadImageName);
				LOGGER.info("Image Uploaded : "+uploadImageName);
			} catch (IOException e) {
				LOGGER.info(""+e);
			}
		} else {
			LOGGER.info("Image Is Empty");
		}

		Student result = studentRepo.save(student);
		LOGGER.info("Student saved \n"+result);
		return result.getStudentId();
	}

}
