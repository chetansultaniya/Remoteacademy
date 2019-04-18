package com.robo.remoteacademy.service;

import javax.servlet.http.HttpSession;

import org.springframework.web.multipart.MultipartFile;
import com.robo.remoteacademy.model.Teacher;

public interface TeacherService {

	String save(Teacher teacher,MultipartFile image,HttpSession session);
}
