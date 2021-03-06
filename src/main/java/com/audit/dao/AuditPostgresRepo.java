
package com.audit.dao;

import java.util.List;

import org.springframework.context.annotation.Profile;
import org.springframework.data.jpa.repository.JpaRepository;
import com.audit.entities.AuditInfo;
import com.audit.entities.AuditInfoPostgres;

/**
 * @author DC
 *
 */
@Profile("postgres")

public interface AuditPostgresRepo extends JpaRepository<AuditInfoPostgres, String> {
	/**
	 * @param sourceObjId
	 * @return
	 */
	List<AuditInfo> findBySourceObjIdInAndSourceObjNameInOrderByTimeStampDesc(String sourceObjId, String sourceObjName);
	AuditInfo findById(String id);
	
	long countBySourceObjIdInAndSourceObjNameIn(String sourceObjId, String sourceObjName) ;

}