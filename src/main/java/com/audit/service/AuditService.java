package com.audit.service;

import java.util.List;
import java.util.Map;

import com.audit.entities.AuditInfo;
import com.audit.entities.SourceObjectEntity;

/**
 * @author DC
 *
 */
public interface AuditService<T> {

	String storeAuditInfo(SourceObjectEntity auditData, Map<String, String> auditHeaders);

	List<AuditInfo> getAuditInfo(String id, String objectName);

	AuditInfo findById(String id);

	long getVersionCount(String id, String objectName);

}
