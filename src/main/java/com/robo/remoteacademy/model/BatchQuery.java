package com.robo.remoteacademy.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "batch_query")
public class BatchQuery extends BaseModel{

	private static final long serialVersionUID = 1L;
	@Id
	@Column(name = "batch_query_id")
	private String batchQueryId;
	
	
	@ManyToOne
	@JoinColumn(name = "batch_id")
	private Batch batchId;
	
	
	@ManyToOne
	@JoinColumn(name = "student_id")
	private Student studentId;
	
	@Column(name = "message")
	private String message;

	public BatchQuery() {
		
	}

	public BatchQuery(String batchQueryId, Batch batchId, Student studentId, String message) {
		this.batchQueryId = batchQueryId;
		this.batchId = batchId;
		this.studentId = studentId;
		this.message = message;
	}

	public String getBatchQueryId() {
		return batchQueryId;
	}

	public void setBatchQueryId(String batchQueryId) {
		this.batchQueryId = batchQueryId;
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

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
	
	
	
}
