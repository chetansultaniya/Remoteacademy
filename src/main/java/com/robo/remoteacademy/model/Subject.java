package com.robo.remoteacademy.model;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "subject")
public class Subject extends BaseModel {

	private static final long serialVersionUID = 1L;
	
	@Id
	@Column(name = "subject_id")
	private String subjectId;

	
	@ManyToOne
	@JoinColumn(name="teacher_id")
	private Teacher teacherId;

	@NotNull
	@Column(name = "duration")
	private String duration;

	@Column(name = "timing")
	private String timing;

	@NotNull
	@Column(name = "subject_name")
	private String subjectName;

	@Column(name = "description")
	private String description;

	@Column(name = "tution_fee")
	private String tutionFee;

	@OneToMany(mappedBy = "subjectId", cascade = CascadeType.ALL)
	private Set<SubjectJoiners> subjectJoiners;
	
	
	

	public Subject() {

	}

	

	public Subject(String subjectId, Teacher teacherId, @NotNull String duration, String timing,
			@NotNull String subjectName, String description, String tutionFee, Set<SubjectJoiners> subjectJoiners) {
		
		this.subjectId = subjectId;
		this.teacherId = teacherId;
		this.duration = duration;
		this.timing = timing;
		this.subjectName = subjectName;
		this.description = description;
		this.tutionFee = tutionFee;
		this.subjectJoiners = subjectJoiners;
	}



	public String getSubjectId() {
		return subjectId;
	}

	public void setSubjectId(String subjectId) {
		this.subjectId = subjectId;
	}

	public Teacher getTeacherId() {
		return teacherId;
	}

	public void setTeacherId(Teacher teacherId) {
		this.teacherId = teacherId;
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

	public String getSubjectName() {
		return subjectName;
	}

	public void setSubjectName(String subjectName) {
		this.subjectName = subjectName;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getTutionFee() {
		return tutionFee;
	}

	public void setTutionFee(String tutionFee) {
		this.tutionFee = tutionFee;
	}
	
	public Set<SubjectJoiners> getSubjectJoiners() {
		return subjectJoiners;
	}

	public void setSubjectJoiners(Set<SubjectJoiners> subjectJoiners) {
		this.subjectJoiners = subjectJoiners;
	}

}
