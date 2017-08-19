package com.audit.application.test;

import static org.assertj.core.api.Assertions.assertThat;


import java.net.URL;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.embedded.LocalServerPort;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import com.audit.entities.SourceObjectEntity;
import com.audit.service.AuditService;
import com.audit.application.test.AuditTestItem;
import com.fasterxml.jackson.core.JsonProcessingException;

/**
 * @author DC
 *
 */
// @TODO: need more tests

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class AuditServiceIntegrationPostTest {
	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	RestTemplate restTemplate = new RestTemplate();

	@LocalServerPort
	private int port;

	private URL base;

	@Autowired
	private AuditService auditService;
	
	@Autowired
	private AuditTestItem testItem;

	

	private SourceObjectEntity sourceObjectEntity = new SourceObjectEntity();

	@Before
	public void setUp() throws Exception {
		this.base = new URL("http://localhost:" + port + "/api/");
	}


	private ResponseEntity<Map> getResponse(Object sourceObjectEntity, MultiValueMap<String, String> headers)
			throws Exception {

		// MultiValueMap<String, String> headers = getHeaders();
		String url = this.base.toString() + "audit";
		HttpEntity<Object> entity = new HttpEntity<Object>(sourceObjectEntity, headers);
		logger.debug("Entity is  " + entity.toString());
		ResponseEntity<Map> response = restTemplate.exchange(url, HttpMethod.POST, entity, Map.class);
		return response;

	}

	/**
	 * @throws JsonProcessingException
	 * @throws Exception
	 */

	@Test
	public void postTestWithProperHeadersAndObject() throws Exception {
		logger.debug("Testing of calling postTestWithProperHeaders function starts");
		Object sourceObject = testItem.getProperSourceObject();
		MultiValueMap<String, String> headers = testItem.getProperHeaders();

		ResponseEntity<Map> response = getResponse(sourceObject, headers);
		assertThat(response.getStatusCode().is2xxSuccessful());
		logger.debug("Testing of calling postTestWithProperHeaders function ends");

	}

	@Test
	public void postTestWithMalFormedHeaders() throws Exception {
		logger.debug("Testing of calling postTestWithMalFormedHeaders function starts");
		Object sourceObject = testItem.getProperSourceObject();
		MultiValueMap<String, String> headers = testItem.getMalformedHeaders();

		ResponseEntity<Map> response = getResponse(sourceObject, headers);
		assertThat(response.getStatusCode().is2xxSuccessful());
		logger.debug("Testing of calling postTestWithMalFormedHeaders function ends");

	}

	@Test
	public void postTestWithVeryMalFormedHeaders() throws Exception {
		logger.debug("Testing of calling postTestWithVeryMalFormedHeaders function starts");
		Object sourceObject = testItem.getProperSourceObject();
		MultiValueMap<String, String> headers = testItem.getVeryMalformedHeaders();

		ResponseEntity<Map> response = getResponse(sourceObject, headers);
		assertThat(response.getStatusCode().is2xxSuccessful());
		logger.debug("Testing of calling postTestWithVeryMalFormedHeaders function ends");

	}

}
