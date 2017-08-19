package com.audit.entities;

import java.util.Date;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.stereotype.Component;


/**
 * @author DC
 *
 */
@Component(value = "AuditInfoMongo")
@Document(collection = "Audit_Info")

public class AuditInfoMongo implements AuditInfo {
	private String sourceObjId;
	private Object sourceObj;
	private String sourceObjName;
	private String createdBy;
	private String lastModifiedBy;
	private String createdDate;
	private String lastModifiedDate;
	private Date timeStamp;
	private String diffLog;

	@Id
	private String id;

	public AuditInfoMongo() {
		super();
	}

	@Override
	public String getId() {
		return id;
	}

	public String getSourceObjId() {
		return sourceObjId;
	}

	public void setSourceObjId(String sourceObjId) {
		this.sourceObjId = sourceObjId;
	}

	public Object getSourceObj() {
		return sourceObj;
	}

	public void setSourceObj(Object sourceObj) {
		this.sourceObj = sourceObj;
	}

	public String getSourceObjName() {
		return sourceObjName;
	}

	public void setSourceObjName(String sourceObjName) {
		this.sourceObjName = sourceObjName;
	}

	public String getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

	public String getLastModifiedBy() {
		return lastModifiedBy;
	}

	public void setLastModifiedBy(String lastModifiedBy) {
		this.lastModifiedBy = lastModifiedBy;
	}

	public String getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(String createdDate) {
		this.createdDate = createdDate;
	}

	public String getLastModifiedDate() {
		return lastModifiedDate;
	}

	public void setLastModifiedDate(String lastModifiedDate) {
		this.lastModifiedDate = lastModifiedDate;
	}

	public Date getTimeStamp() {
		return timeStamp;
	}

	public void setTimeStamp(Date timeStamp) {
		this.timeStamp = timeStamp;
	}

	public String getDiffLog() {
		return diffLog;
	}

	public void setDiffLog(String diffLog) {
		this.diffLog = diffLog;
	}

	public void setId(String id) {
		this.id = id;
	}

}