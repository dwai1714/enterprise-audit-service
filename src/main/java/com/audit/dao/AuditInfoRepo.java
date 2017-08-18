package com.audit.dao;

import java.util.List;

import com.audit.entities.AuditInfo;

public interface AuditInfoRepo {

	List<AuditInfo> findtop10AuditInfoOrderByTimeStampDesc(String sourceObjId, String sourceObjName);

	AuditInfo findById(String id);

	void save(AuditInfo auditInfo);
	
	long getVersionCount(String sourceObjId, String sourceObjName);

}
