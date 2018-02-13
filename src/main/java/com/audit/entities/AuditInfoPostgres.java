package com.audit.entities;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.TypeDef;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import com.audit.utils.JsonbUserType;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;

/**
 * @author DC
 *
 */
// @Document(collection = "AuditInfo")
@Profile("postgres")
@Component(value = "AuditInfoPostgres")
@Entity
@Table(name = "Audit_info")
@TypeDef(name = JsonbUserType.JSONB_TYPE, typeClass = JsonbUserType.class)
public class AuditInfoPostgres implements AuditInfo {
	private String sourceObjId;
	private Object sourceObj;
	private String sourceObjName;
	private String createdBy;
	private String lastModifiedBy;
	private String createdDate;
	private String lastModifiedDate;
	private Date timeStamp;
	private String diffLog;

	private String id;

	@Id
	@GeneratedValue(generator = "system-uuid")
	@GenericGenerator(name = "system-uuid", strategy = "uuid")
	@Column(name = "id", unique = true)
	@Override
	public String getId() {
		return id;
	}

	@Override
	public void setId(String id) {
		this.id = id;
	}

	@Type(type = JsonbUserType.JSONB_TYPE)
	@Column(name = "source_Obj", length = 50)

	public Object getSourceObj() {
		ObjectMapper mapper = new ObjectMapper();
		return  mapper.valueToTree(sourceObj);
	}

	public void setSourceObj(Object sourceObj) {
		this.sourceObj = sourceObj;
	}

	public String getSourceObjId() {
		return sourceObjId;
	}

	public void setSourceObjId(String sourceObjId) {
		this.sourceObjId = sourceObjId;
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
}