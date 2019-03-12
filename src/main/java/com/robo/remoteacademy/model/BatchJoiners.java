package com.robo.remoteacademy.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "batch_joiners")
public class BatchJoiners extends BaseModel{

	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "batch_joiner_id")
	private String batchJoinerId;
	
	
	@ManyToOne
	@JoinColumn(name = "batch_id")
	private Batch batchId;
	

	@ManyToOne
	@JoinColumn(name = "student_id")
	private Student studentId;

	public BatchJoiners() {
		
	}

	public BatchJoiners(String batchJoinerId, Batch batchId, Student studentId) {
		this.batchJoinerId = batchJoinerId;
		this.batchId = batchId;
		this.studentId = studentId;
	}

	public String getBatchJoinerId() {
		return batchJoinerId;
	}

	public void setBatchJoinerId(String batchJoinerId) {
		this.batchJoinerId = batchJoinerId;
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
	
		
}
