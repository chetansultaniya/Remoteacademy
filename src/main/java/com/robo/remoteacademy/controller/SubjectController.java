package com.robo.remoteacademy.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.jasper.tagplugins.jstl.core.Set;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.fasterxml.uuid.Generators;
import com.robo.remoteacademy.model.Student;
import com.robo.remoteacademy.model.Subject;
import com.robo.remoteacademy.model.SubjectJoiners;
import com.robo.remoteacademy.model.Teacher;
import com.robo.remoteacademy.repository.SubjectJoinersRepository;
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
	@Autowired
	SubjectJoinersRepository subjectJoinersRepo;
	MyPagination myPagination = new MyPagination();

	@RequestMapping(value = "/show/getAllSubjects/{email}", method = RequestMethod.POST)
	@ResponseBody
	public String getAllSubjects(@PathVariable("email") String teacherEmail) throws JSONException {
		Teacher teacher = teacherRepo.findByEmail(teacherEmail, false).orElse(null);
		List<Subject> subjects = subjectRepo.findByTeacherId(teacher, false);

		JSONObject subjectData = new JSONObject();
		JSONObject jsObj2 = new JSONObject();
		JSONArray jsArr1 = new JSONArray();

		for (Subject subject : subjects) {
			jsObj2 = new JSONObject();
			jsObj2.put("id", subject.getSubjectId());
			jsObj2.put("name", subject.getSubjectName());
			jsArr1.put(jsObj2);
		}

		subjectData.put("subject", jsArr1);
		return subjectData.toString();
	}

	@RequestMapping(value = "/show/deletesubject/{id}", method = RequestMethod.POST)
	public ModelAndView deleteSubjects(@PathVariable("id") Object id, HttpServletRequest request) {
		ModelAndView mv = new ModelAndView("redirect:/subject/show/subjects");
		Subject subject;

		String subjectId[] = id.toString().split(",");
		for (String ids : subjectId) {
			subject = subjectRepo.findById(ids, false).orElse(null);
			LOGGER.info("Deleting Subject : " + subject.getSubjectName());
			deleteJoiners(subject);
			subject.setDeleted(true);
			modifyData(subject, request);
			subjectRepo.save(subject);
			LOGGER.info("Subject Deleted : " + subject.getSubjectName() + "\n\n\n");
		}

		return mv;
	}

	@RequestMapping(value = "/recycle/restoresubjects/{id}", method = RequestMethod.POST)
	public ModelAndView restoreSubjects(@PathVariable("id") Object id, HttpServletRequest request) {
		ModelAndView mv = new ModelAndView("redirect:/subject/recycle/subject");
		String subjectId[] = id.toString().split(",");
		Subject subject;
		for (String ids : subjectId) {
			subject = subjectRepo.findById(ids, true).orElse(null);
			Teacher teacherId=subject.getTeacherId();
			if(!teacherId.isDeleted()){
			LOGGER.info("Restoring Subject : " + subject.getSubjectName());
			restoreJoiners(subject);
			subject.setDeleted(false);
			modifyData(subject, request);
			subjectRepo.save(subject);
			LOGGER.info("Subject Restored : " + subject.getSubjectName() + "\n\n\n");
			}
			
		}

		return mv;
	}

	@RequestMapping(value = "/recycle/harddeletesubjects/{id}", method = RequestMethod.POST)
	public ModelAndView hardDeleteSubjects(@PathVariable("id") Object id) {
		ModelAndView mv = new ModelAndView("redirect:/subject/recycle/subject");
		String subjectIdCollection = id.toString();

			String subjectId[] = subjectIdCollection.split(",");
			for (String ids : subjectId) {
				LOGGER.info("Subject Deleting Permanently : " + id.toString());
				subjectRepo.deleteById(ids);
				LOGGER.info("Subject Deleted Permanently : " + id.toString()+"\n\n\n");
			}
		

		return mv;
	}

	@RequestMapping(value = "/recycle/subject", method = RequestMethod.GET)
	public ModelAndView subjectRecycle(@RequestParam Map<String, String> requestParams, HttpServletRequest request) {
		ModelAndView mv = new ModelAndView("recycleBin");
		Page page = subjectRepo.findAllDeleted(myPagination.getPageable(requestParams));
		HttpSession session = request.getSession();

		mv.addObject("records", page.getContent());
		mv.addObject("contextDetail", "Subject");
		mv.addObject("totalPage", page.getTotalPages());
		mv.addObject("adminDetail", session.getAttribute("name"));

		return mv;
	}

	@RequestMapping(value = "/show/subjects", method = RequestMethod.GET)
	public ModelAndView showSubjects(@RequestParam Map<String, String> requestParams, HttpServletRequest request) {
		ModelAndView mv = new ModelAndView("subjects");
		Page page = subjectRepo.findAll(myPagination.getPageable(requestParams));
		
		HttpSession session = request.getSession();
		mv.addObject("context", "subject");
		mv.addObject("subjectList", page.getContent());
		mv.addObject("totalPage", page.getTotalPages());
		mv.addObject("adminDetail", session.getAttribute("name"));
		return mv;
	}

	@RequestMapping(value = "/show/subjectProfile/{id}", method = RequestMethod.GET)
	public ModelAndView subjectProfile(@PathVariable("id") String id) {
		ModelAndView mv = new ModelAndView("subjectProfile");
		Optional<Subject> option = subjectRepo.findById(id);
		Subject subject = option.get();
		mv.addObject("subject", subject);
		return mv;
	}

	@RequestMapping(value = "/show/save", method = RequestMethod.POST)
	public ModelAndView saveSubject(@RequestParam Map<String, String> requestParams, HttpServletRequest request) {
		ModelAndView mv = new ModelAndView("redirect:/subject/show/subjects");
		ModelAndView mv2 = new ModelAndView("subjectRegistration");

		Subject subject;
		Teacher teacher;
		String subjectId = requestParams.get("subjectId");
		String teacherEmail = requestParams.get("teacherEmail");
		String subjectName = requestParams.get("subjectName");
		String subjectDuration = requestParams.get("subjectDuration");
		String subjectDescription = requestParams.get("subjectDescription");
		String tutionFee = requestParams.get("tutionFee");

		subject = subjectRepo.findById(subjectId).orElse(null);
		teacher = teacherRepo.findByEmail(teacherEmail, false).orElse(null);
		if (subject == null) {
			if (teacher != null) {
				String subjectTimeFrom = requestParams.get("subjectTimeFrom");
				String subjectTimeTo = requestParams.get("subjectTimeTo");
				UUID uuid = Generators.timeBasedGenerator().generate();
				subject = new Subject();
				subject.setSubjectId(uuid.toString());
				subject.setTeacherId(teacher);
				subject.setSubjectName(subjectName);
				subject.setDuration(subjectDuration);
				subject.setTiming(subjectTimeFrom + " to " + subjectTimeTo);
				subject.setDescription(subjectDescription);
				subject.setTutionFee(tutionFee);
				createData(subject, request);
				subjectRepo.save(subject);
				mv.addObject("success", "Subject Successfully Registered");
				LOGGER.info("\n\n\n\n******Adding New Subject******");
				LOGGER.info("Subject Id : " + subject.getSubjectId());
				LOGGER.info("Subject Name : " + subject.getSubjectName() + "\nTeacher Name : " + teacher.getName()
						+ "\nTeacher Email : " + teacher.getEmail() + "\nDuration : " + subject.getDuration()
						+ "\nSubject Timing : " + subject.getTiming() + "\nSubject Fee : " + subject.getTutionFee()
						+ "\nSubject Description : " + subject.getDescription() + "\n");
				return mv;
			} else {
				mv2.addObject("error", "Teacher not found");
				LOGGER.info("Teacher Not Found : " + teacherEmail);
				return mv2;
			}

		} else if (requestParams.get("subjectTimeFrom") == null && requestParams.get("subjectTimeTo") == null) {
			subject = subjectRepo.findById(subjectId).orElse(null);
			subject.setSubjectName(subjectName);
			subject.setDuration(subjectDuration);
			subject.setTiming(requestParams.get("timing"));
			subject.setDescription(subjectDescription);
			subject.setTutionFee(tutionFee);
			modifyData(subject, request);
			subjectRepo.save(subject);
			mv.addObject("success", "Subject Update Successfully");
			LOGGER.info("\n\n\n\n******Updating Subject******\nSubject Id : \"" + subject.getSubjectId() + "\"");
			LOGGER.info("Subject Name : " + subject.getSubjectName() + "\nDuration : " + subject.getDuration()
					+ "\nSubject Timing : " + subject.getTiming() + "\nSubject Fee : " + subject.getTutionFee()
					+ "\nSubject Description : " + subject.getDescription() + "\n");
			return mv;
		} else {
			mv2.addObject("error", "SubjectId Already Exist");
			LOGGER.info("Subject Id Already Exist :" + subject.getSubjectId());
			return mv2;
		}
	}

	private void restoreJoiners(Subject subject) {
		List<SubjectJoiners> subjectJoinersList = subjectJoinersRepo.findAllBySubjectId(subject, true);
		if (!subjectJoinersList.isEmpty()) {
			for (SubjectJoiners subjectJoiners : subjectJoinersList) {
				subjectJoiners.setDeleted(false);
				subjectJoinersRepo.save(subjectJoiners);
				LOGGER.info("Restored Subject Joiner : " + subjectJoiners.getStudentId().getEmail());
			}
		}
	}

	private void deleteJoiners(Subject subject) {
		List<SubjectJoiners> subjectJoinersList = subjectJoinersRepo.findAllBySubjectId(subject, false);
		if (!subjectJoinersList.isEmpty()) {
			for (SubjectJoiners subjectJoiners : subjectJoinersList) {
				subjectJoiners.setDeleted(true);
				subjectJoinersRepo.save(subjectJoiners);
				LOGGER.info("Deleted Subject Joiner : " + subjectJoiners.getStudentId().getEmail());
			}
		}
	}

	private void createData(Subject subject, HttpServletRequest request) {
		HttpSession session = request.getSession();
		String accessedBy = session.getAttribute("email").toString();
		subject.setCreatedBy(accessedBy);
		subject.setModifiedBy(accessedBy);
		subject.setCreatedOn(new Date());
		subject.setModifiedOn(new Date());

	}

	private void modifyData(Subject subject, HttpServletRequest request) {
		HttpSession session = request.getSession();
		String accessedBy = session.getAttribute("email").toString();
		subject.setModifiedBy(accessedBy);
		subject.setModifiedOn(new Date());
	}

}
