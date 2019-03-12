package com.robo.remoteacademy.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "batch_rating")
public class BatchRating extends BaseModel{

	private static final long serialVersionUID = 1L;
	
	@Id
	@Column(name = "batch_rating_id")
	private String batchRatingId;
	
	
	@ManyToOne
	@JoinColumn(name = "batch_id")
	private Batch batchId;
	
	
	@ManyToOne
	@JoinColumn(name = "student_id")
	private Student studentId;
	
	
	@NotNull
	@Column(name = "points")
	private String points;
	
	@Column(name = "message")
	private String message;

	public BatchRating() {
		
	}

	public BatchRating(String batchRatingId, Batch batchId, Student studentId, String points, String message) {
		this.batchRatingId = batchRatingId;
		this.batchId = batchId;
		this.studentId = studentId;
		this.points = points;
		this.message = message;
	}

	public String getBatchRatingId() {
		return batchRatingId;
	}

	public void setBatchRatingId(String batchRatingId) {
		this.batchRatingId = batchRatingId;
	}

	public Batch getBatchId() {
		return batchId;
	}

	public void setBatchId(Batch batchId) {
		this.batchId = batchId;
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
