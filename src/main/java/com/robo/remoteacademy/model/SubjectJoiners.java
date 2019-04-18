package com.robo.remoteacademy.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "subject_joiners")
public class SubjectJoiners extends BaseModel {

	private static final long serialVersionUID = 1L;
	
	@Id
	@Column(name = "subject_joiner_id")
	private String subjectJoinerId;
	
	
	@ManyToOne
	@JoinColumn(name = "subject_id")
	private Subject subjectId;
	
	private Long counter;
	
	@ManyToOne
	@JoinColumn(name = "student_id")
	private Student studentId;

	public SubjectJoiners() {
	
	}
	public SubjectJoiners(Subject subjectId,Long counter) {
		this.subjectId = subjectId;
		this.counter=counter;	
	}

	public SubjectJoiners(String subjectJoinerId, Subject subjectId, Student studentId) {
		this.subjectJoinerId = subjectJoinerId;
		this.subjectId = subjectId;
		this.studentId = studentId;
	}

	public String getSubjectJoinerId() {
		return subjectJoinerId;
	}

	public void setSubjectJoinerId(String subjectJoinerId) {
		this.subjectJoinerId = subjectJoinerId;
	}

	public Subject getSubjectId() {
		return subjectId;
	}

	public void setSubjectId(Subject subjectId) {
		this.subjectId = subjectId;
	}

	public Student getStudentId() {
		return studentId;
	}

	public void setStudentId(Student studentId) {
		this.studentId = studentId;
	}
	public Long getCounter() {
		return counter;
	}
	public void setCounter(Long counter) {
		this.counter = counter;
	}
	
}
