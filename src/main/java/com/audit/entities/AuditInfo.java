package com.audit.entities;

import java.util.Date;



/**
 * @author DC
 *
 */

public interface AuditInfo {

	String getId();

	String getSourceObjId();

	void setSourceObjId(String sourceObjId);

	Object getSourceObj();

	void setSourceObj(Object sourceObj);

	String getSourceObjName();

	void setSourceObjName(String sourceObjName);

	String getCreatedBy();

	void setCreatedBy(String createdBy);

	String getLastModifiedBy();

	void setLastModifiedBy(String lastModifiedBy);

	String getCreatedDate();

	void setCreatedDate(String createdDate);

	String getLastModifiedDate();

	void setLastModifiedDate(String lastModifiedDate);

	Date getTimeStamp();

	void setTimeStamp(Date timeStamp);

	String getDiffLog();

	void setDiffLog(String diffLog);

	void setId(String id);

}