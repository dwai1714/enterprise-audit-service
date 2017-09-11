package com.audit.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.audit.entities.AuditInfo;
import com.audit.entities.SourceObjectEntity;
import com.audit.service.AuditService;

import io.swagger.annotations.ApiOperation;

/**
 * @author DC
 * 
 *         Rest Controller for handling Audit operations
 *
 */
@RestController
@RequestMapping(value = "/audit")
public class AuditController {

	private Logger log = LoggerFactory.getLogger(getClass());

	@Autowired
	private AuditService<AuditInfo> auditService;

	/**
	 * @param id
	 * @return
	 */
	@ApiOperation(httpMethod = "GET", value = "The API for getting the audit history of a object with id and Object name")
	@RequestMapping(value = "/history/{id}/{objName}", method = RequestMethod.GET)
	public List<AuditInfo> getAuditInfo(@PathVariable("id") String id, 
			@PathVariable("objName") String objName) {
		List<AuditInfo> auditInfos = auditService.getAuditInfo(id, objName);
		this.log.info("Found " + auditInfos.size() + " recored for the ID " + id);
		return auditInfos;
	}

	/**
	 * @param id
	 */

	@ApiOperation(httpMethod = "GET", value = "The API for getting the details of a Audit object with Objectid")

	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public Object getOneAuditInfo(@PathVariable("id") String id) {
		AuditInfo auditInfo = auditService.findById(id);
		return auditInfo;
	}

	/**
	 * @param auditData
	 * @param id
	 */
	@ApiOperation(httpMethod = "POST", value = "The API for posting the details of a Audit Object")
	@RequestMapping(method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
		
	public Map<String, String> addAuditInfo(@RequestBody SourceObjectEntity auditData,
			@RequestHeader Map <String, String> auditHeaders) {
		this.log.info("Processing Audit Info of Class with source object id " +  auditData.getSourceObjId());
		
		String Id = auditService.storeAuditInfo(auditData, auditHeaders);
		Map<String, String> response = new HashMap<>();
		response.put("id", Id);
		return response;

	}

}
