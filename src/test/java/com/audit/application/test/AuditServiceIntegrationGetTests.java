package com.audit.application.test;
import static org.assertj.core.api.Assertions.assertThat;

import java.net.URL;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.embedded.LocalServerPort;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import com.audit.dao.AuditInfoRepoMongoImpl;
import com.audit.dao.AuditInfoRepoPostgresImpl;
import com.audit.entities.AuditInfo;
import com.audit.service.AuditService;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)

public class AuditServiceIntegrationGetTests {
	

		private final Logger logger = LoggerFactory.getLogger(this.getClass());

		@Autowired
		private TestRestTemplate testRestTemplate;

		@LocalServerPort
		private int port;

		private URL base;
		@Autowired
		private AuditService auditService;

	
		@Before
		public void setUp() throws Exception {
			this.base = new URL("http://localhost:" + port + "/");
		}

		@Test
		public void getTest() throws Exception {
			String url = this.base.toString() + "audit/history/12/Test";
			logger.debug("Testing attribute Controller method for getting audit added in post");
			ResponseEntity<Object> response = testRestTemplate.getForEntity(url, Object.class);
			HttpStatus statusCode = response.getStatusCode();
			assertThat(response.getStatusCode().is2xxSuccessful());
			logger.info("Get Status Code " + statusCode.toString());

		}
		
		@Test
		public void getOneAuditInfoTest() throws Exception {
			String url = this.base.toString() + "audit/abcd";
			logger.debug("Testing attribute Controller method for getting audit info with objectId");
			ResponseEntity<Object> response = testRestTemplate.getForEntity(url, Object.class);
			HttpStatus statusCode = response.getStatusCode();
			assertThat(response.getStatusCode().is2xxSuccessful());
			logger.info("Get Status Code " + statusCode.toString());

		}



	}


