package com.robo.remoteacademy.controller;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.fasterxml.uuid.Generators;
import com.robo.remoteacademy.model.Student;
import com.robo.remoteacademy.model.Subject;
import com.robo.remoteacademy.model.SubjectJoiners;
import com.robo.remoteacademy.model.Teacher;
import com.robo.remoteacademy.repository.StudentRepository;
import com.robo.remoteacademy.repository.SubjectJoinersRepository;
import com.robo.remoteacademy.repository.SubjectRepository;
import com.robo.remoteacademy.repository.TeacherRepository;
import com.robo.remoteacademy.util.MyPagination;

@RestController
@RequestMapping("/subject_joiners/")
public class SubjectJoinersController {

	private static final Logger LOGGER = LogManager.getLogger(SubjectJoinersController.class);
	@Autowired
	SubjectJoinersRepository subjectJoinersRepo;

	@Autowired
	StudentRepository studentRepo;
	@Autowired
	SubjectRepository subjectRepo;

	MyPagination myPagination = new MyPagination();

	@RequestMapping(value = "/show/subject_joiners")
	public ModelAndView showSubjectJoin(@RequestParam Map<String, String> requestParams, HttpServletRequest request) {
		ModelAndView mv = new ModelAndView("subjectJoiners");
		HttpSession session = request.getSession();

		Page page = subjectJoinersRepo.findAllSubjectWithGroupBy(myPagination.getPageable(requestParams), false);
		mv.addObject("subjectJoinersList", page.getContent());
		mv.addObject("context", "subject_joiners");
		mv.addObject("totalPage", page.getTotalPages());
		mv.addObject("adminDetail", session.getAttribute("name"));

		return mv;
	}

	@RequestMapping(value = "/show/deletesubjectjoiners/{id}")
	public ModelAndView deleteSubjectJoiners(@PathVariable("id") Object id, HttpServletRequest request) {
		ModelAndView mv = new ModelAndView("redirect:/subject_joiners/show/subject_joiners");

		String subjectId[] = id.toString().split(",");
		for (String ids : subjectId) {
			Subject subject = subjectRepo.findById(ids, false).orElse(null);
			List<SubjectJoiners> subjectJoiners = subjectJoinersRepo.findAllBySubjectId(subject, false);
			LOGGER.info("Removing Student from  : " + subject.getSubjectName());
			if (subjectJoiners != null && !subjectJoiners.isEmpty()) {
				for (SubjectJoiners subjectJoiner : subjectJoiners) {
					subjectJoiner.setDeleted(true);
					modifyData(subjectJoiner, request);
					subjectJoinersRepo.save(subjectJoiner);
					LOGGER.info("Student Removed  : " + subjectJoiner.getStudentId().getEmail() + "\n");
				}
			}
		}

		return mv;
	}

	@RequestMapping(value = "/recycle/harddeletesubjectjoiners/{id}")
	public ModelAndView hardDeletedSubjectJoiners(@PathVariable("id") Object id) {
		ModelAndView mv = new ModelAndView("redirect:/subject_joiners/recycle/subject_joiners");
		String subjectId[] = id.toString().split(",");
		for (String ids : subjectId) {
			Subject subject = subjectRepo.findById(ids).orElse(null);
			List<SubjectJoiners> subjectJoiners = subjectJoinersRepo.findAllBySubjectId(subject, true);
			LOGGER.info("Subject Joiners Deleting Permanently");
			for (SubjectJoiners subjectJoiner : subjectJoiners) {
				subjectJoinersRepo.delete(subjectJoiner);
				LOGGER.info(subjectJoiner.getSubjectJoinerId() + " : Deleted");
			}
			LOGGER.info("\n\n\n");
		}

		return mv;
	}

	@RequestMapping(value = "/recycle/restoresubjectjoiners/{id}")
	public ModelAndView restoreSubjectJoiners(@PathVariable("id") Object id, HttpServletRequest request) {
		ModelAndView mv = new ModelAndView("redirect:/subject_joiners/recycle/subject_joiners");
		String subjectId[] = id.toString().split(",");
		for (String ids : subjectId) {
			Subject subject = subjectRepo.findById(ids, false).orElse(null);
			List<SubjectJoiners> subjectJoiners = subjectJoinersRepo.findAllBySubjectId(subject, true);
			if (subjectJoiners != null && !subjectJoiners.isEmpty()) {
				LOGGER.info("Restoring Student in  : " + subject.getSubjectName());
				for (SubjectJoiners subjectJoiner : subjectJoiners) {
					if (!subjectJoiner.getStudentId().isDeleted()) {
						subjectJoiner.setDeleted(false);
						modifyData(subjectJoiner, request);
						subjectJoinersRepo.save(subjectJoiner);
						LOGGER.info("Student Restored  : " + subjectJoiner.getStudentId().getEmail() + "\n");
					}
				}
			}
		}

		return mv;
	}

	@RequestMapping(value = "/recycle/subject_joiners")
	public ModelAndView showRecycleSubjectJoiners(@RequestParam Map<String, String> requestParams,
			HttpServletRequest request) {
		ModelAndView mv = new ModelAndView("recycleBin");
		HttpSession session = request.getSession();

		Page page = subjectJoinersRepo.findAllSubjectWithGroupBy(myPagination.getPageable(requestParams), true);
		mv.addObject("records", page.getContent());
		mv.addObject("contextDetail", "Subject Joiners");
		mv.addObject("totalPage", page.getTotalPages());
		mv.addObject("adminDetail", session.getAttribute("name"));

		return mv;
	}

	@RequestMapping(value = "/recycle/joined_students", method = RequestMethod.GET)
	public ModelAndView showRecycleJoinedStudents(@RequestParam Map<String, String> requestParams,
			HttpServletRequest request) {
		ModelAndView mv = new ModelAndView("recycleBin");
		HttpSession session = request.getSession();

		mv.addObject("contextDetail", "Joined Students");
		mv.addObject("adminDetail", session.getAttribute("name"));
		System.out.println("requestParams : " + requestParams);
		return mv;
	}

	@RequestMapping(value = "/show/save", method = RequestMethod.POST)
	public ModelAndView joinToSubject(@RequestParam Map<String, String> requestParams, HttpServletRequest request) {
		ModelAndView mv = new ModelAndView("joinSubject");

		Subject subject = subjectRepo.findById(requestParams.get("subjectId"), false).orElse(null);
		Student student = studentRepo.findByEmail(requestParams.get("studentEmail"), false).orElse(null);
		if (subject != null && student != null) {
			try {
				UUID uuid = Generators.timeBasedGenerator().generate();
				SubjectJoiners subjectJoiners = new SubjectJoiners();
				subjectJoiners.setStudentId(student);
				subjectJoiners.setSubjectId(subject);
				subjectJoiners.setSubjectJoinerId(uuid.toString());
				createData(subjectJoiners, request);
				subjectJoinersRepo.save(subjectJoiners);
				LOGGER.info("\"" + subject.getSubjectName() + "\" Joined By " + "\"" + requestParams.get("studentEmail")
						+ "\"");
				mv.addObject("success", "Subject Joining Successfull");
			} catch (Exception e) {
				LOGGER.info("Subject Joining Failed : " + e);
				mv.addObject("error", "Subject Joining Failed!Try Again");
			}
		} else {
			LOGGER.info("Subject Joining Failed");
			mv.addObject("error", "Subject Joining Failed!Try Again");
		}

		return mv;

	}

	@RequestMapping(value = "/show/subjectJoinersProfile/{id}", method = RequestMethod.GET)
	public ModelAndView subjectJoinersProfile(@RequestParam Map<String, String> requestParams,
			@PathVariable("id") String id, HttpServletRequest request) {
		ModelAndView mv = new ModelAndView("joinedStudents");
		HttpSession session = request.getSession();
		Subject subject = subjectRepo.findById(id).orElse(null);

		Page page = subjectJoinersRepo.findAllBySubjectId(myPagination.getPageable(requestParams), subject, false);
		mv.addObject("contextDetail", "Joined Students");
		mv.addObject("totalPage", page.getTotalPages());
		mv.addObject("adminDetail", session.getAttribute("name"));
		mv.addObject("context", "joined_students");
		if (page.getTotalElements() == 0) {
			mv.addObject("data", page.getTotalElements());
		} else {
			mv.addObject("subjectJoinersData", page.getContent());
		}
		return mv;
	}

	private void createData(SubjectJoiners subjectJoiners, HttpServletRequest request) {
		HttpSession session = request.getSession();
		String accessedBy = session.getAttribute("email").toString();
		subjectJoiners.setCreatedBy(accessedBy);
		subjectJoiners.setModifiedBy(accessedBy);
		subjectJoiners.setCreatedOn(new Date());
		subjectJoiners.setModifiedOn(new Date());

	}

	private void modifyData(SubjectJoiners subjectJoiners, HttpServletRequest request) {
		HttpSession session = request.getSession();
		String accessedBy = session.getAttribute("email").toString();
		subjectJoiners.setModifiedBy(accessedBy);
		subjectJoiners.setModifiedOn(new Date());
	}

}
