package com.robo.remoteacademy.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "subject_rating")
public class SubjectRating extends BaseModel {

	private static final long serialVersionUID = 1L;
	
	@Id
	@Column(name = "subject_rating_id")
	private String subjectRatingId;
	
	
	@ManyToOne
	@JoinColumn(name = "subject_id")
	private Subject subjectId;
	
	
	@ManyToOne
	@JoinColumn(name = "student_id")
	private Student studentId;
	
	
	@NotNull
	@Column(name = "points")
	private String points;
	
	@Column(name = "message")
	private String message;

	public SubjectRating() {
		
	}

	public SubjectRating(String subjectRatingId, Subject subjectId, Student studentId, String points, String message) {
		this.subjectRatingId = subjectRatingId;
		this.subjectId = subjectId;
		this.studentId = studentId;
		this.points = points;
		this.message = message;
	}

	public String getSubjectRatingId() {
		return subjectRatingId;
	}

	public void setSubjectRatingId(String subjectRatingId) {
		this.subjectRatingId = subjectRatingId;
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

	public String getPoints() {
		return points;
	}

	public void setPoints(String points) {
		this.points = points;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	
	
}
