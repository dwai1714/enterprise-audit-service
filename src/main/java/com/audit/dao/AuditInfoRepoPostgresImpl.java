package com.audit.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import com.audit.entities.AuditInfo;
import com.audit.entities.AuditInfoPostgres;

@Component
@Profile("postgres")
public class AuditInfoRepoPostgresImpl implements AuditInfoRepo {

	@Autowired
	private AuditPostgresRepo auditPostgresRepo;

	@Override
	public List<AuditInfo> findtop10AuditInfoOrderByTimeStampDesc(String sourceObjId, String sourceObjName) {
		return auditPostgresRepo.findBySourceObjIdInAndSourceObjNameInOrderByTimeStampDesc(sourceObjId, sourceObjName);
	}

	@Override
	public AuditInfo findById(String id) {
		return auditPostgresRepo.findById(id);
	}

	@Override
	public void save(AuditInfo auditInfo) {
		AuditInfoPostgres auditInfoPostgres = (AuditInfoPostgres) auditInfo;
		auditPostgresRepo.save(auditInfoPostgres);

	}
	
	@Override
	public  long getVersionCount(String sourceObjId, String sourceObjName) {
		long versions = auditPostgresRepo.countBySourceObjIdInAndSourceObjNameIn(sourceObjId,sourceObjName);
		return versions;
	}


}
