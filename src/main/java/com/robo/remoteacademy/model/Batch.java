package com.robo.remoteacademy.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;


@Entity
@Table(name = "batch")
public class Batch extends BaseModel{

	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "batch_id")
	private String batchId;
	
	@ManyToOne
	@JoinColumn(name = "teacher_id")
	private Teacher teacherId;
	
	
	@Column(name  = "name")
	private String name;
	
	@Column(name = "registration_start_date")
	@Temporal(TemporalType.DATE)
	private Date registrationStartDate;
	
	@Column(name = "batch_start_date")
	@Temporal(TemporalType.DATE)
	private Date batchStartDate;
	
	@Column(name = "duration")
	private String duration;
	
	@Column(name = "timing")
	private String timing;
	
	@Column(name = "description")
	private String description;
	
	@Column(name = "batch_fee")
	private String batchFee;

	public Batch() {
		
	}

	public Batch(String batchId, Teacher teacherId, String name, Date registrationStartDate, Date batchStartDate,
			String duration, String timing, String description, String batchFee) {
		this.batchId = batchId;
		this.teacherId = teacherId;
		this.name = name;
		this.registrationStartDate = registrationStartDate;
		this.batchStartDate = batchStartDate;
		this.duration = duration;
		this.timing = timing;
		this.description = description;
		this.batchFee = batchFee;
	}

	public String getBatchId() {
		return batchId;
	}

	public void setBatchId(String batchId) {
		this.batchId = batchId;
	}

	public Teacher getTeacherId() {
		return teacherId;
	}

	public void setTeacherId(Teacher teacherId) {
		this.teacherId = teacherId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Date getRegistrationStartDate() {
		return registrationStartDate;
	}

	public void setRegistrationStartDate(Date registrationStartDate) {
		this.registrationStartDate = registrationStartDate;
	}

	public Date getBatchStartDate() {
		return batchStartDate;
	}

	public void setBatchStartDate(Date batchStartDate) {
		this.batchStartDate = batchStartDate;
	}

	public String getDuration() {
		return duration;
	}

	public void setDuration(String duration) {
		this.duration = duration;
	}

	public String getTiming() {
		return timing;
	}

	public void setTiming(String timing) {
		this.timing = timing;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getBatchFee() {
		return batchFee;
	}

	public void setBatchFee(String batchFee) {
		this.batchFee = batchFee;
	}
	
	
	
}
