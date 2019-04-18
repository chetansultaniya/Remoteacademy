package com.robo.remoteacademy.controller;

import java.util.Date;
import java.util.Enumeration;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;

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
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.fasterxml.uuid.Generators;
import com.robo.remoteacademy.model.Admin;
import com.robo.remoteacademy.model.Student;
import com.robo.remoteacademy.model.Subject;
import com.robo.remoteacademy.model.SubjectJoiners;
import com.robo.remoteacademy.model.Teacher;
import com.robo.remoteacademy.repository.SubjectJoinersRepository;
import com.robo.remoteacademy.repository.SubjectRepository;
import com.robo.remoteacademy.repository.TeacherRepository;
import com.robo.remoteacademy.service.TeacherService;
import com.robo.remoteacademy.util.MyPagination;

@RestController
@RequestMapping("/teacher/")
public class TeacherController {

	private final static Logger LOGGER = LogManager.getLogger(TeacherController.class);

	@Autowired
	TeacherRepository teacherRepo;
	@Autowired
	SubjectRepository subjectRepo;

	@Autowired
	TeacherService teacherService;
	
	@Autowired	
	SubjectJoinersRepository subjectJoinerRepo;

	MyPagination myPagination = new MyPagination();

	public void createSession(HttpServletRequest request, Teacher teacher) {
		LOGGER.info("Session Create for :" + teacher.getEmail());
		request.getSession().setAttribute("teacherEmail", teacher.getEmail());
		request.getSession().setAttribute("teacherName", teacher.getName());
		request.getSession().setAttribute("teacherId", teacher.getTeacherId());
	}

	public void removeSession(HttpServletRequest request) {
		HttpSession session = request.getSession();
		session.removeAttribute("teacherEmail");
		session.removeAttribute("teacherName");
		session.removeAttribute("teacherId");
		LOGGER.info(
				"\n-×-×-×-×-×-×-×-×-×-×-×-×-×-×-×-×-×-×-Session End for This Teacher-×-×-×-×-×-×-×-×-×-×-×-×-×-×-×-×-×-×-\n\n\n\n\n");
	}

	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public ModelAndView login() {
		ModelAndView mv = new ModelAndView("teacherLogin");
		LOGGER.info("Opening Login Page for Teacher");
		return mv;
	}

	@RequestMapping(value = "/logout", method = RequestMethod.POST)
	public ModelAndView logOut(HttpServletRequest request) {
		ModelAndView mv = new ModelAndView("teacherLogin");
		HttpSession session = request.getSession();
		removeSession(request);

		return mv;
	}

	@RequestMapping(value = "/index", method = RequestMethod.GET)
	public ModelAndView adminIndex() {

		ModelAndView mv = new ModelAndView("teacherLogin");
		LOGGER.info("Opening Login Page for Teacher");
		return mv;
	}

	@RequestMapping(value = "/show/getAllTeachers", method = RequestMethod.POST)
	@ResponseBody
	public Object[] getAllTeachers() {

		return teacherRepo.findEmail().toArray();
	}

	@RequestMapping(value = "/show/teacherdashboard", method = RequestMethod.GET)
	public ModelAndView dashboard() {
		ModelAndView mv = new ModelAndView("teacherLogin");
		LOGGER.info("Opening Login Page for Teacher");
		return mv;
	}

	@RequestMapping(value = "/show/teacherdashboard", method = RequestMethod.POST)
	public ModelAndView teacherDashboard(HttpServletRequest request, @RequestParam Map<String, String> requestParams) {
		ModelAndView mv = new ModelAndView("teacherDashboard");
		ModelAndView mv2 = new ModelAndView("teacherLogin");
		HttpSession session = request.getSession();

		Teacher teacher;
		if (session.getAttribute("teacherEmail") == null) {
			teacher = teacherRepo.findByEmail(requestParams.get("email"),false).orElse(null);
			if (teacher != null) {
				if (teacher.getPassword().equals(requestParams.get("password"))) {
					LOGGER.info(
							"\n\n\n\n\n*************************************Session Start*****************************************\nAccessing By\nTeacher Id : "
									+ teacher.getTeacherId() + "\nTeacher Name : " + teacher.getName() + "\n");
					createSession(request, teacher);
					mv.addObject("teacherDetail", teacher.getName());
					LOGGER.info("Opening Teacher DashBoard");
					return mv;
				} else {
					mv2.addObject("error", "You entered wrong Password!");
					LOGGER.info(teacher.getEmail() + " : entered wrong Password");
					return mv2;
				}
			} else {
				mv2.addObject("error", "Email doesn't exist");
				LOGGER.info("Email Not Exist : " + requestParams.get("email"));
				return mv2;
			}
		} else {
			if (requestParams.get("email").equals((String) session.getAttribute("teacherEmail"))) {
				String sessionEmail = (String) session.getAttribute("teacherEmail");
				teacher = teacherRepo.findByEmail(sessionEmail,false).orElse(null);
				mv.addObject("teacherDetail", teacher.getName());
				LOGGER.info(
						"\n\n\n\n\n*************************************Session Start*****************************************\nAccessing By\nTeacher Id : "
								+ teacher.getTeacherId() + "\nTeacher Name : " + teacher.getName() + "\n");
				LOGGER.info("Opening Teacher DashBoard");
				return mv;
			} else {
				removeSession(request);
				teacher = teacherRepo.findByEmail(requestParams.get("email"),false).orElse(null);
				LOGGER.info(
						"\n\n\n\n\n*************************************Session Start*****************************************\nAccessing By\nTeacher Id : "
								+ teacher.getTeacherId() + "\nTeacher Name : " + teacher.getName() + "\n");
				createSession(request, teacher);
				mv.addObject("teacherDetail", teacher.getName());
				LOGGER.info("Opening Teacher DashBoard");
				return mv;
			}
		}
	}

	@RequestMapping(value = "/show/showdashboard", method = RequestMethod.GET)
	public ModelAndView showDashboard() {
		ModelAndView mv = new ModelAndView("index");
		LOGGER.info("Opening Login Page for Teacher");
		return mv;
	}

	@RequestMapping(value = "/show/deleteteacher/{id}", method = RequestMethod.POST)
	public ModelAndView deleteTeacher(@PathVariable("id") Object id,HttpServletRequest request) {
		ModelAndView mv = new ModelAndView("redirect:/teacher/show/teachers");
		String teacherIdCollection[] = id.toString().split(",");

		
		for (String ids : teacherIdCollection) {
			Teacher teacher = teacherRepo.findById(ids,false).orElse(null);
			LOGGER.info("Deleting Teacher : "+teacher.getEmail());
			deleteSubject(teacher);
			modifyData(teacher,request);
			teacher.setDeleted(true);
			teacherRepo.save(teacher);
			LOGGER.info("Teacher Deleted : "+teacher.getEmail()+"\n\n\n");
		}

		return mv;
	}

	@RequestMapping(value = "/recycle/restoreteachers/{id}", method = RequestMethod.POST)
	public ModelAndView restoreTeachers(@PathVariable("id") Object id,HttpServletRequest request) {
		ModelAndView mv = new ModelAndView("redirect:/teacher/recycle/teacher");
String teacherIdCollection[] = id.toString().split(",");

		
		for (String ids : teacherIdCollection) {
			Teacher teacher = teacherRepo.findById(ids,true).orElse(null);
			LOGGER.info("Restoring Teacher : "+teacher.getEmail());
			restoreSubject(teacher);
			modifyData(teacher,request);
			teacher.setDeleted(false);
			teacherRepo.save(teacher);
			LOGGER.info("Teacher Restored : "+teacher.getEmail()+"\n\n\n");
		}

		return mv;
	}

	@RequestMapping(value = "/recycle/harddeleteteachers/{id}", method = RequestMethod.POST)
	public ModelAndView hardDeleteTeachers(@PathVariable("id") Object id) {
		ModelAndView mv = new ModelAndView("redirect:/teacher/recycle/teacher");
		String teacherIdCollection = id.toString();

		if (teacherIdCollection.length() < 2) {
			teacherRepo.deleteById(teacherIdCollection);
			LOGGER.info("Teacher Deleted Permanently with ID : " + id.toString());
		} else {
			String teacherId[] = teacherIdCollection.split(",");

			for (String ids : teacherId) {
				teacherRepo.deleteById(ids);
				LOGGER.info("Teacher Deleted Permanently with ID : " + ids);
			}
		}

		return mv;
	}

	@RequestMapping(value = "/recycle/teacher", method = RequestMethod.GET)
	public ModelAndView studentRecycle(@RequestParam Map<String, String> requestParams, HttpServletRequest request) {
		ModelAndView mv = new ModelAndView("recycleBin");
		Page page = teacherRepo.findAllDeleted(myPagination.getPageable(requestParams));
		HttpSession session = request.getSession();
		mv.addObject("records", page.getContent());
		mv.addObject("contextDetail", "Teacher");
		mv.addObject("totalPage", page.getTotalPages());
		mv.addObject("adminDetail", session.getAttribute("name"));

		return mv;
	}

	@RequestMapping(value = "/show/teachers", method = RequestMethod.GET)
	public ModelAndView showTeachers(@RequestParam Map<String, String> requestParams, HttpServletRequest request) {
		ModelAndView mv = new ModelAndView("teachers");
		Page page = teacherRepo.findAll(myPagination.getPageable(requestParams));
		HttpSession session = request.getSession();
		mv.addObject("teacher", page.getContent());
		mv.addObject("context", "teacher");
		mv.addObject("totalPage", page.getTotalPages());
		mv.addObject("adminDetail", session.getAttribute("name"));
		LOGGER.info("Show Teachers List");

		return mv;
	}

	@RequestMapping(value = "/show/teacherProfile/{id}", method = RequestMethod.GET)
	public ModelAndView teacherProfile(@PathVariable("id") String id) {
		ModelAndView mv = new ModelAndView("teacherProfile");
		Optional<Teacher> option = teacherRepo.findById(id);
		Teacher teacher = option.get();
		mv.addObject("teacher", teacher);
		LOGGER.info("Open Teacher : " + teacher.getTeacherId());

		return mv;
	}

	@RequestMapping(value = "/show/save", method = RequestMethod.POST)
	public ModelAndView saveTeacher(@ModelAttribute("teacher") Teacher teacher, BindingResult result,
			@RequestParam("image") MultipartFile image, HttpServletRequest request) {
		ModelAndView mv = new ModelAndView("redirect:/teacher/show/teachers");
		HttpSession session=request.getSession();
		createData(teacher, request);
		teacherService.save(teacher, image,session);
		return mv;
	}
	
	
	
	private void restoreSubject(Teacher teacherId) {
		List<Subject> subjectList = subjectRepo.findByTeacherId(teacherId,true);
		if (!subjectList.isEmpty()&&subjectList!=null) {
			for (Subject subject : subjectList) {
				subject.setDeleted(false);
				LOGGER.info("Restoring its Subject : "+subject.getSubjectName());
				List<SubjectJoiners> subjectJoiners=subjectJoinerRepo.findAllBySubjectId(subject, true);
				if(!subjectJoiners.isEmpty()&&subjectJoiners!=null) {
					int count=0;
					for(SubjectJoiners subjectJoiner:subjectJoiners) {
						subjectJoiner.setDeleted(false);
						count++;
					}
					LOGGER.info(count+" : Students are restored with this Subject");
				}
				subjectRepo.save(subject);
			}
			LOGGER.info("Subject Restored");
		}
	}

	
	private void deleteSubject(Teacher teacherId) {
		List<Subject> subjectList = subjectRepo.findByTeacherId(teacherId, false);
		if (!subjectList.isEmpty()&&subjectList!=null) {
			for (Subject subject : subjectList) {
				subject.setDeleted(true);
				LOGGER.info("Deleting its Subject : "+subject.getSubjectName());
				List<SubjectJoiners> subjectJoiners=subjectJoinerRepo.findAllBySubjectId(subject, false);
				if(!subjectJoiners.isEmpty()&&subjectJoiners!=null) {
					int count=0;
					for(SubjectJoiners subjectJoiner:subjectJoiners) {
						subjectJoiner.setDeleted(true);
						count++;
					}
					LOGGER.info(count+" : Students are removed with this Subject");
				}
				subjectRepo.save(subject);
			}
			
			LOGGER.info("Subject Deleted");
		}
	}

	private void createData(Teacher teacher, HttpServletRequest request) {
		HttpSession session = request.getSession();
		String accessedBy = session.getAttribute("email").toString();
		teacher.setCreatedBy(accessedBy);
		teacher.setModifiedBy(accessedBy);
		teacher.setCreatedOn(new Date());
		teacher.setModifiedOn(new Date());

	}

	private void modifyData(Teacher teacher, HttpServletRequest request) {
		HttpSession session = request.getSession();
		String accessedBy = session.getAttribute("email").toString();
		teacher.setModifiedBy(accessedBy);
		teacher.setModifiedOn(new Date());
	}

	

}
