package com.audit.entities;

import java.util.Date;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import com.fasterxml.jackson.databind.node.ObjectNode;

/**
 * @author DC
 *
 */


public class SourceObjectEntity {

	private String sourceObjId;
	private Object sourceObj;
	private String sourceObjName;
	

	public Object getSourceObj() {
		return sourceObj;
	}

	public void setSourceObj(Object sourceObj) {
		this.sourceObj = sourceObj;
	}
	
	public SourceObjectEntity() {
		super();
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


}
