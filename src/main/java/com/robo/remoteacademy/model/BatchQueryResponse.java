package com.robo.remoteacademy.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "batch_query_response")
public class BatchQueryResponse extends BaseModel{

	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "batch_query_response_id")
	private String batchQueryResponseId;
	
	
	@ManyToOne
	@JoinColumn(name = "batch_query_id")
	private BatchQuery batchQueryId;
	
	@Column(name ="message")
	private String message;

	public BatchQueryResponse() {
		
	}

	public BatchQueryResponse(String batchQueryResponseId, BatchQuery batchQueryId, String message) {
		this.batchQueryResponseId = batchQueryResponseId;
		this.batchQueryId = batchQueryId;
		this.message = message;
	}

	public String getBatchQueryResponseId() {
		return batchQueryResponseId;
	}

	public void setBatchQueryResponseId(String batchQueryResponseId) {
		this.batchQueryResponseId = batchQueryResponseId;
	}

	public BatchQuery getBatchQueryId() {
		return batchQueryId;
	}

	public void setBatchQueryId(BatchQuery batchQueryId) {
		this.batchQueryId = batchQueryId;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
	
		
}
