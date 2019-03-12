package com.robo.remoteacademy.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "subject_query")
public class SubjectQuery extends BaseModel {

	private static final long serialVersionUID = 1L;
	
	@Id
	@Column(name = "subject_query_id")
	private String subjectQueryId;
	
	
	
	@ManyToOne
	@JoinColumn(name = "subject_id")
	private Subject subjectId;
	
	
	
	@ManyToOne
	@JoinColumn(name = "student_id")
	private Student studentId;
	
	@Column(name = "message")
	private String message;

	public SubjectQuery() {

	}

	public SubjectQuery(String subjectQueryId, Subject subjectId, Student studentId, String message) {
		
		this.subjectQueryId = subjectQueryId;
		this.subjectId = subjectId;
		this.studentId = studentId;
		this.message = message;
	}

	public String getSubjectQueryId() {
		return subjectQueryId;
	}

	public void setSubjectQueryId(String subjectQueryId) {
		this.subjectQueryId = subjectQueryId;
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

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
	
	
	

}
