package com.audit.application.test;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import com.audit.entities.SourceObjectEntity;
import com.fasterxml.jackson.databind.ObjectMapper;

@Component
public class AuditTestItem {
	String callingProgramCreatedBy = "createdUser";
	String callingProgramCreatedDate = "createdDate";
	String callingProgramLastModifiedBy = "lastModifiedUser";
	String callingProgramLastModifiedDate = "lastModifiedDate";

	@Autowired
	private ObjectMapper objectMapper;

	private SourceObjectEntity sourceObjectEntity = new SourceObjectEntity();


	private Date getDate(String stringDate, String format) {
		DateFormat df = new SimpleDateFormat(format);
		Date returnDate;
		try {
			returnDate = df.parse(stringDate);
			return returnDate;
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return new Date();

	}

	public MultiValueMap<String, String> getProperHeaders() throws Exception {
		MultiValueMap<String, String> headers = new LinkedMultiValueMap<String, String>();
		Map<String, String> map = new HashMap<String, String>();
		headers.add("Content-Type", "application/json");
		headers.add("createdby", callingProgramCreatedBy);
		headers.add("createddate", callingProgramCreatedDate);
		headers.add("lastmodifiedby", callingProgramLastModifiedBy);
		headers.add("lastmodifieddate", callingProgramLastModifiedDate);
		return headers;
	}

	public MultiValueMap<String, String> getMalformedHeaders() throws Exception {
		MultiValueMap<String, String> headers = new LinkedMultiValueMap<String, String>();
		headers.add("Content-Type", "application/json");
		// headers.add("createdby", callingProgramCreatedBy);
		headers.add("createddate", callingProgramCreatedDate);
		headers.add("lastmodifiedby", callingProgramLastModifiedBy);
		headers.add("lastmodifieddate", callingProgramLastModifiedDate);
		return headers;
	}

	public MultiValueMap<String, String> getVeryMalformedHeaders() throws Exception {
		MultiValueMap<String, String> headers = new LinkedMultiValueMap<String, String>();
		Map<String, String> map = new HashMap<String, String>();
		// headers.add("Content-Type", "application/json");
		// headers.add("createdby", callingProgramCreatedBy);
		// headers.add("createddate", callingProgramCreatedDate);
		// headers.add("lastmodifiedby", callingProgramLastModifiedBy);
		// headers.add("lastmodifieddate", callingProgramLastModifiedDate);
		return headers;
	}

	public Item getItem() {
		Item item1 = new Item("12", "swift", "2017");
		return item1;
	}

	public SourceObjectEntity getProperSourceObject() {
		Item item = getItem();
		item.setCreatedUser("DC");
		item.setCreatedDate(getDate("21/02/1971 1:15:15", "dd/MM/yyyy HH:mm:ss"));
		item.setLastModifiedUser("SomeOne Else");
		item.setLastModifiedDate(new Date());

		sourceObjectEntity.setSourceObjId(item.getId());
		sourceObjectEntity.setSourceObjName("Test");
		sourceObjectEntity.setSourceObj(item);
		return sourceObjectEntity;

	}


}
