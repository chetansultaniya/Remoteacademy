package com.robo.remoteacademy.service;

import java.io.IOException;
import java.util.Date;
import java.util.UUID;

import javax.servlet.http.HttpSession;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.uuid.Generators;
import com.robo.remoteacademy.model.Student;
import com.robo.remoteacademy.model.Teacher;
import com.robo.remoteacademy.repository.TeacherRepository;
import com.robo.remoteacademy.util.FileUploadAndDownload;

@Service
public class TeacherServiceImpl implements TeacherService {

	private static final Logger LOGGER = LogManager.getLogger(StudentServiceImpl.class.getName());

	@Autowired
	TeacherRepository teacherRepo;
	
	@Override
	public String save(Teacher teacher, MultipartFile image,HttpSession session) {

		String teacherUUID;

		if (teacher.getTeacherId() == null || teacher.getTeacherId().equals("")) {
			UUID uuid = Generators.timeBasedGenerator().generate();
			teacherUUID = uuid.toString();
			teacher.setTeacherId(teacherUUID);
			teacher.setCreatedBy(session.getAttribute("email").toString());
			teacher.setModifiedBy(session.getAttribute("email").toString());
			teacher.setCreatedOn(new Date());
			teacher.setModifiedOn(new Date());
			LOGGER.info("Saving New Teacher \n" + teacher);
		} else {
			teacherUUID = teacher.getTeacherId();
			Teacher existingTeacher = teacherRepo.findById(teacherUUID).orElse(null);
			teacher.setImage(existingTeacher.getImage());
			teacher.setCreatedBy(existingTeacher.getCreatedBy());
			teacher.setModifiedBy(session.getAttribute("email").toString());
			teacher.setCreatedOn(existingTeacher.getCreatedOn());
			teacher.setModifiedOn(new Date());
			LOGGER.info("Updating Teacher \n" + existingTeacher);
		}

		if (!image.isEmpty() && image != null) {
			try {
				byte[] imageBytes = image.getBytes();
				String imageName = image.getOriginalFilename();
				String imageExt = imageName.substring(imageName.lastIndexOf(".") + 1);
				FileUploadAndDownload fileUploadAndDownload = new FileUploadAndDownload();
				String uploadImageName = fileUploadAndDownload.fileUpload(imageBytes, imageExt, teacherUUID, "teacher",
						"image");
				teacher.setImage(uploadImageName);
				LOGGER.info("Image Uploaded : " + uploadImageName);
			} catch (IOException e) {
				LOGGER.info("" + e);
			}
		} else {
			LOGGER.info("Image Is Empty");
		}

		Teacher result = teacherRepo.save(teacher);
		LOGGER.info("Teacher saved \n" + result);
		return result.getTeacherId();

	}

}
