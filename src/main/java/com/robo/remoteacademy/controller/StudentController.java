package com.robo.remoteacademy.controller;

import java.lang.management.MemoryType;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Date;
import java.util.Enumeration;
import java.util.Iterator;
import java.util.List;
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
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.fasterxml.uuid.Generators;
import com.robo.remoteacademy.model.Student;
import com.robo.remoteacademy.model.Subject;
import com.robo.remoteacademy.model.SubjectJoiners;
import com.robo.remoteacademy.model.Teacher;
import com.robo.remoteacademy.repository.StudentRepository;
import com.robo.remoteacademy.repository.SubjectJoinersRepository;
import com.robo.remoteacademy.service.StudentService;
import com.robo.remoteacademy.service.StudentServiceImpl;
import com.robo.remoteacademy.util.MyPagination;

@RestController
@RequestMapping("/student/")
public class StudentController {

	private static final Logger LOGGER = LogManager.getLogger(StudentController.class);

	@Autowired
	StudentRepository studentRepo;
	@Autowired
	StudentService studentService;
	@Autowired
	SubjectJoinersRepository subjectJoinersRepo;

	MyPagination myPagination = new MyPagination();

	public void createSession(HttpServletRequest request, Student student) {
		HttpSession session = request.getSession();
		LOGGER.info("Session Create for :" + student.getEmail());
		request.getSession().setAttribute("studentEmail", student.getEmail());
		request.getSession().setAttribute("studentName", student.getName());
		request.getSession().setAttribute("studentId", student.getStudentId());

	}

	public void removeSession(HttpServletRequest request) {
		HttpSession session = request.getSession();
		session.removeAttribute("studentEmail");
		session.removeAttribute("studentName");
		session.removeAttribute("studentId");
		LOGGER.info(
				"\n-×-×-×-×-×-×-×-×-×-×-×-×-×-×-×-×-×-×-Session End for This Student-×-×-×-×-×-×-×-×-×-×-×-×-×-×-×-×-×-×-\n\n\n\n\n");
	}

	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public ModelAndView login() {
		ModelAndView mv = new ModelAndView("studentLogin");
		LOGGER.info("Opening Login Page for Student");
		return mv;
	}

	@RequestMapping(value = "/logout", method = RequestMethod.POST)
	public ModelAndView logout(HttpServletRequest request) {
		ModelAndView mv = new ModelAndView("studentLogin");
		HttpSession session = request.getSession();
		removeSession(request);

		return mv;
	}

	@RequestMapping(value = "/index", method = RequestMethod.GET)
	public ModelAndView studentIndex() {
		ModelAndView mv = new ModelAndView("studentLogin");
		LOGGER.info("Opening Login Page for Student");

		return mv;
	}

	@RequestMapping(value = "/show/studentdashboard", method = RequestMethod.GET)
	public ModelAndView dashboard() {
		ModelAndView mv = new ModelAndView("index");
		LOGGER.info("Opening Login Page for Student");

		return mv;
	}

	@RequestMapping(value = "/show/studentdashboard", method = RequestMethod.POST)
	public ModelAndView studentDashboard(HttpServletRequest request, @RequestParam Map<String, String> requestParams) {
		ModelAndView mv2 = new ModelAndView("studentLogin");
		ModelAndView mv = new ModelAndView("studentDashboard");
		HttpSession session = request.getSession();

		Student student;
		if (session.getAttribute("studentEmail") == null) {
			student = studentRepo.findByEmail(requestParams.get("email"), false).orElse(null);
			if (student != null) {
				if (student.getPassword().equals(requestParams.get("password"))) {
					LOGGER.info(
							"\n\n\n\n\n*************************************Session Start*****************************************\nAccessing By\nStudent Id : "
									+ student.getStudentId() + "\nStudent Name : " + student.getName() + "\n");
					createSession(request, student);
					mv.addObject("studentDetail", student.getName());
					LOGGER.info("Opening Student DashBoard");
					return mv;
				} else {
					mv2.addObject("error", "You entered wrong Password!");
					LOGGER.info(student.getEmail() + " : entered wrong Password");
					return mv2;
				}
			} else {
				mv2.addObject("error", "Email doesn't exist!");
				LOGGER.info("Email Not Exist : " + requestParams.get("email"));
				return mv2;
			}
		} else {

			if (requestParams.get("email").equals((String) session.getAttribute("studentEmail"))) {
				String sessionEmail = (String) session.getAttribute("studentEmail");
				student = studentRepo.findByEmail(sessionEmail, false).orElse(null);
				mv.addObject("studentDetail", student.getName());
				LOGGER.info(
						"\n\n\n\n\n*************************************Session Start*****************************************\nAccessing By\nStudent Id : "
								+ student.getStudentId() + "\nStudent Name : " + student.getName() + "\n");
				LOGGER.info("Opening Student DashBoard");
				return mv;
			} else {
				removeSession(request);
				student = studentRepo.findByEmail(requestParams.get("email"), false).orElse(null);
				LOGGER.info(
						"\n\n\n\n\n*************************************Session Start*****************************************\nAccessing By\nStudent Id : "
								+ student.getStudentId() + "\nStudent Name : " + student.getName() + "\n");
				createSession(request, student);
				mv.addObject("studentDetail", student.getName());
				LOGGER.info("Opening Student DashBoard");
				return mv;
			}
		}
	}

	@RequestMapping(value = "/show/showdashboard", method = RequestMethod.GET)
	public ModelAndView showDashboard() {
		ModelAndView mv = new ModelAndView("index");
		LOGGER.info("Opening Login Page for Student");
		return mv;
	}

	@RequestMapping(value = "/show/deletestudent/{id}", method = RequestMethod.POST)
	public ModelAndView deleteStudents(@PathVariable("id") Object id, HttpServletRequest request) {
		ModelAndView mv = new ModelAndView("redirect:/student/show/students");
		String studentIdCollection[] = id.toString().split(",");

		for (String ids : studentIdCollection) {
			Student student = studentRepo.findById(ids, false).orElse(null);
			LOGGER.info("Deleting Student : " + student.getEmail());
			deleteStudent(ids,request);
		}

		return mv;
	}

	@RequestMapping(value = "/recycle/restorestudents/{id}", method = RequestMethod.POST)
	public ModelAndView restoreStudents(@PathVariable("id") Object id, HttpServletRequest request) {
		ModelAndView mv = new ModelAndView("redirect:/student/recycle/student");
		String studentIdCollection[] = id.toString().split(",");

		for (String ids : studentIdCollection) {
			Student student = studentRepo.findById(ids, true).orElse(null);
			LOGGER.info("Restoring Student : " + student.getEmail());
			restoreStudent(ids,request);
		}
		return mv;
	}

	@RequestMapping(value = "/recycle/harddeletestudents/{id}", method = RequestMethod.POST)
	public ModelAndView hardDeleteStudents(@PathVariable("id") Object id) {
		ModelAndView mv = new ModelAndView("redirect:/student/recycle/student");
		String studentIdCollection = id.toString();

		Student student;
		if (studentIdCollection.length() < 2) {
			studentRepo.deleteById(studentIdCollection);
			LOGGER.info("Student Deleted Permanently with ID : " + id.toString());
		} else {
			String studentId[] = studentIdCollection.split(",");
			for (String ids : studentId) {
				studentRepo.deleteById(ids);
				LOGGER.info("Student Deleted Permanently with ID : " + ids);
			}
		}

		return mv;
	}

	@RequestMapping(value = "/recycle/student", method = RequestMethod.GET)
	public ModelAndView studentRecycle(@RequestParam Map<String, String> requestParams, HttpServletRequest request) {
		ModelAndView mv = new ModelAndView("recycleBin");
		Page page = studentRepo.findAllDeleted(myPagination.getPageable(requestParams));
		HttpSession session = request.getSession();

		mv.addObject("records", page.getContent());
		mv.addObject("contextDetail", "Student");
		mv.addObject("totalPage", page.getTotalPages());
		mv.addObject("adminDetail", session.getAttribute("name"));

		return mv;
	}

	@RequestMapping(value = "/show/students", method = RequestMethod.GET)
	public ModelAndView showStudents(@RequestParam Map<String, String> requestParams, HttpServletRequest request) {
		ModelAndView mv = new ModelAndView("students");
		HttpSession session = request.getSession();
		Page page = studentRepo.findAll(myPagination.getPageable(requestParams));

		mv.addObject("studentList", page.getContent());
		mv.addObject("context", "student");
		mv.addObject("totalPage", page.getTotalPages());
		mv.addObject("adminDetail", session.getAttribute("name"));
		return mv;
	}

	@RequestMapping(value = "/show/studentProfile/{id}", method = RequestMethod.GET)
	public ModelAndView studentProfile(@PathVariable("id") String id) {
		ModelAndView mv = new ModelAndView("studentProfile");
		Optional<Student> option = studentRepo.findById(id);
		Student student = option.get();
		mv.addObject("student", student);
		return mv;
	}

	@RequestMapping(value = "/show/save", method = RequestMethod.POST)
	public ModelAndView saveStudent(@ModelAttribute("student") Student student, BindingResult result,
			@RequestParam("image") MultipartFile image, HttpServletRequest request) {
		ModelAndView mv = new ModelAndView("redirect:/student/show/students");
		HttpSession session = request.getSession();

		studentService.save(student, image, session);
		return mv;
	}

	@RequestMapping(value = "/show/studentRegistration", method = RequestMethod.POST)
	public ModelAndView studentRegistration() {
		ModelAndView mv = new ModelAndView("studentRegistration");
		LOGGER.info("Opening Student Registration");
		return mv;
	}

	private void restoreStudent(String studentId,HttpServletRequest request) {
		Student student = studentRepo.findById(studentId, true).orElse(null);
		if (student != null) {
			List<SubjectJoiners> subjectJoiners =subjectJoinersRepo.findAllByStudentId(student, true);
			for(SubjectJoiners subjectJoiner:subjectJoiners) {
				subjectJoiner.setDeleted(false);
				subjectJoinersRepo.save(subjectJoiner);
				LOGGER.info("Student Joined back with its previos joined Subject "+subjectJoiner.getSubjectId().getSubjectName());
			}
			student.setDeleted(false);
			modifyData(student, request);
			studentRepo.save(student);
			LOGGER.info("Student Restored\n\n\n");
		}

	}

	private void deleteStudent(String studentId,HttpServletRequest request) {
		Student student = studentRepo.findById(studentId, false).orElse(null);
		if (student != null) {
			List<SubjectJoiners> subjectJoiners =subjectJoinersRepo.findAllByStudentId(student, false);
			for(SubjectJoiners subjectJoiner:subjectJoiners) {
				subjectJoiner.setDeleted(true);
				subjectJoinersRepo.save(subjectJoiner);
				LOGGER.info("Student Removed with "+subjectJoiner.getSubjectId().getSubjectName());
			}
			student.setDeleted(true);
			modifyData(student, request);
			studentRepo.save(student);
			LOGGER.info("Student Deleted\n\n\n");
		}

	}

	private void createData(Student student, HttpServletRequest request) {
		HttpSession session = request.getSession();
		String accessedBy = session.getAttribute("email").toString();
		student.setCreatedBy(accessedBy);
		student.setModifiedBy(accessedBy);
		student.setCreatedOn(new Date());
		student.setModifiedOn(new Date());

	}

	private void modifyData(Student student, HttpServletRequest request) {
		HttpSession session = request.getSession();
		String accessedBy = session.getAttribute("email").toString();
		student.setModifiedBy(accessedBy);
		student.setModifiedOn(new Date());
	}

}
