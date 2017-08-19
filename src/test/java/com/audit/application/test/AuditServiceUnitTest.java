package com.audit.application.test;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.test.context.junit4.SpringRunner;

import com.audit.entities.AuditInfo;
import com.audit.service.AuditService;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)

public class AuditServiceUnitTest {
	private final Logger logger = LoggerFactory.getLogger(this.getClass());


	@Autowired
	private AuditService auditService;



	@Test
	public void getAuditInfoTest() throws Exception {
		List<AuditInfo> auditInfo = auditService.getAuditInfo("12", "Test");
		for (int i = 0; i < auditInfo.size(); i++) {
			logger.info("Size is " + auditInfo.size());
			logger.info("TimeStamp is " + auditInfo.get(i).getTimeStamp());
		}
	}

	@Test
	public void countVersions() {
		logger.info("Version count  is " + auditService.getVersionCount("12", "Test"));
	}

}
