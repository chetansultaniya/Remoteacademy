package com.robo.remoteacademy.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "subject_query_response")
public class SubjectQueryResponse extends BaseModel {

	private static final long serialVersionUID = 1L;
	
	@Id
	@Column(name = "subject_query_response_id")
	private String subjectQueryResponseId;

	
	@ManyToOne
	@JoinColumn(name = "subject_query_id")
	private SubjectQuery subjectQueryId;

	@Column(name = "message")
	private String message;

	public SubjectQueryResponse() {

	}

	public SubjectQueryResponse(String subjectQueryResponseId, SubjectQuery subjectQueryId, String message) {
		this.subjectQueryResponseId = subjectQueryResponseId;
		this.subjectQueryId = subjectQueryId;
		this.message = message;
	}

	public String getSubjectQueryResponseId() {
		return subjectQueryResponseId;
	}

	public void setSubjectQueryResponseId(String subjectQueryResponseId) {
		this.subjectQueryResponseId = subjectQueryResponseId;
	}

	public SubjectQuery getSubjectQueryId() {
		return subjectQueryId;
	}

	public void setSubjectQueryId(SubjectQuery subjectQueryId) {
		this.subjectQueryId = subjectQueryId;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

}
