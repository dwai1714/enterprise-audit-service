package com.audit.service;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.javers.core.Javers;
import org.javers.core.JaversBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.audit.dao.AuditInfoRepo;
import com.audit.entities.AuditInfo;
import com.audit.entities.AuditInfoFactory;
import com.audit.entities.SourceObjectEntity;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.flipkart.zjsonpatch.JsonDiff;

@Service("auditService")

public class AuditServiceImpl implements AuditService {

	@Autowired
	private AuditInfoFactory auditInfoFactory;
	@Autowired
	@Qualifier("postgres")
	private AuditInfoRepo classRepository;

	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.audit.service.AuditServiceMongo#storeAuditInfo(java.lang.Object,
	 * int, int)
	 */

	@Override
	public String storeAuditInfo(SourceObjectEntity sourceObjectEntity, Map auditHeaders) {
		// what happens if source object is null ??
		AuditInfo auditInfo = auditInfoFactory.getAuditInfo();

		auditInfo.setSourceObjId(sourceObjectEntity.getSourceObjId());
		auditInfo.setSourceObjName(sourceObjectEntity.getSourceObjName());
		auditInfo.setSourceObj(sourceObjectEntity.getSourceObj());
		setOtherParams(auditInfo, auditHeaders);
		classRepository.save(auditInfo);
		return auditInfo.getId();

	}

	@Override
	public List<AuditInfo> getAuditInfo(String sourceObjId, String sourceObjName) {

		List<AuditInfo> listAuditInfo = getAuditInfoList(sourceObjId, sourceObjName);
		logger.debug("size is  is " + listAuditInfo.size());
		for (int i = 0; i < listAuditInfo.size() - 1; i++) {
			String diff = getDiff(listAuditInfo.get(i + 1), listAuditInfo.get(i));
			listAuditInfo.get(i).setDiffLog(diff);

		}

		return listAuditInfo;
	}
	


	@Override
	public long getVersionCount(String sourceObjId, String sourceObjName) {

		return classRepository.getVersionCount(sourceObjId, sourceObjName);
	}


	private String extractParamsFromSourceObject(AuditInfo auditInfo, Map<String, String> auditHeaders,
			String paramFromAuditHeader) {

		Map<String, String> defaultParams = new HashMap<String, String>();
		defaultParams.put("defaultcreateddate", new Date(0).toString());
		defaultParams.put("defaultlastmodifieddate", new Date().toString());
		defaultParams.put("defaultcreatedby", "Default_Program_Created_By");
		defaultParams.put("defaultlastmodifiedby", "Default_Program_Last_Modified_By");

		ObjectMapper oMapper = new ObjectMapper();
		Map<String, Object> sourceObjMap = oMapper.convertValue(auditInfo.getSourceObj(), Map.class);

		String returnParam;
		if (sourceObjMap.get(auditHeaders.get(paramFromAuditHeader)) != null) {
			returnParam = sourceObjMap.get(auditHeaders.get(paramFromAuditHeader)).toString();

		} else {
			String defaultString = "default" + paramFromAuditHeader;
			returnParam = defaultParams.get(defaultString).toString();
		}
		return returnParam;
	}

	private void setOtherParams(AuditInfo auditInfo, Map<String, String> auditHeaders) {
		auditInfo.setTimeStamp(new Date());
		auditInfo.setCreatedBy(extractParamsFromSourceObject(auditInfo, auditHeaders, "createdby"));
		auditInfo.setLastModifiedBy(extractParamsFromSourceObject(auditInfo, auditHeaders, "lastmodifiedby"));
		auditInfo.setLastModifiedDate(extractParamsFromSourceObject(auditInfo, auditHeaders, "lastmodifieddate"));
		auditInfo.setCreatedDate(extractParamsFromSourceObject(auditInfo, auditHeaders, "createddate"));
	}

	@Override
	public AuditInfo findById(String id) {
		return classRepository.findById(id);
	}

	private List<AuditInfo> getAuditInfoList(String sourceObjId, String sourceObjName) {

		return classRepository.findtop10AuditInfoOrderByTimeStampDesc(sourceObjId, sourceObjName);
	}

	private String getDiff(AuditInfo oldAinfo, AuditInfo newAinfo) {
		Javers javers = JaversBuilder.javers().build();
		return javers.compare(oldAinfo.getSourceObj(), newAinfo.getSourceObj()).prettyPrint();

	}

	private String getJsonDiff(AuditInfo oldAinfo, AuditInfo newAinfo) {
		try {
			ObjectMapper oMapper = new ObjectMapper();
			String oldAinfoString = oMapper.writeValueAsString(oldAinfo.getSourceObj());
			String newAinfoString = oMapper.writeValueAsString(newAinfo.getSourceObj());
			JsonNode beforeNode = oMapper.readTree(oldAinfoString);
			JsonNode afterNode = oMapper.readTree(newAinfoString);
			JsonNode patchNode = JsonDiff.asJson(beforeNode, afterNode);
			String prettyDiff = patchNode.toString();
			return prettyDiff;

		} catch (Exception e) {
			e.printStackTrace();
			return "No Diff";
		}

	}


}
