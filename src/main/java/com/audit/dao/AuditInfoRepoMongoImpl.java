package com.audit.dao;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Profile;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Component;

import com.audit.entities.AuditInfo;
import com.audit.entities.AuditInfoMongo;

@Component
@Qualifier("mongo")
public class AuditInfoRepoMongoImpl implements AuditInfoRepo {
	@Autowired
	private AuditMongoRepo auditMongoRepo;
	private final Logger LOGGER = LoggerFactory.getLogger(this.getClass());

	@Autowired
	MongoTemplate mongoTemplate;

	private static final String COLLECTION = "auditInfo";

	@Override
	public List<AuditInfo> findtop10AuditInfoOrderByTimeStampDesc(String sourceObjId, String sourceObjName) {

		return auditMongoRepo.findFirst10BySourceObjIdInAndSourceObjNameInOrderByTimeStampDesc(sourceObjId,
				sourceObjName);
	}

	@Override
	public AuditInfo findById(String id) {
		return auditMongoRepo.findOne(id);
	}

	@Override
	public void save(AuditInfo auditInfo) {
		AuditInfoMongo auditInfoMongo = (AuditInfoMongo) auditInfo;

		auditMongoRepo.save(auditInfoMongo);

	}

	@Override
	public long getVersionCount(String sourceObjId, String sourceObjName) {
		Query query = new Query();
		query.addCriteria(Criteria.where("sourceObjId").is(sourceObjId));
		query.addCriteria(Criteria.where("sourceObjName").is(sourceObjName));
		long versions = mongoTemplate.count(query, COLLECTION);
		LOGGER.debug("Count is " + versions);
		return mongoTemplate.count(query, COLLECTION);
	}

}
