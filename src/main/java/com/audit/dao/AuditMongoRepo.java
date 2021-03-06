
package com.audit.dao;

import java.util.List;

import org.springframework.context.annotation.Profile;
import org.springframework.data.mongodb.repository.MongoRepository;
import com.audit.entities.AuditInfo;


@Profile("mongo")

public interface AuditMongoRepo extends MongoRepository<AuditInfo, String> {

	List<AuditInfo> findFirst10BySourceObjIdInAndSourceObjNameInOrderByTimeStampDesc(String sourceObjId, String sourceObjName);

		
	AuditInfo findById(String id);
}
