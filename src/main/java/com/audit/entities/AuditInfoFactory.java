package com.audit.entities;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class AuditInfoFactory {

	@Value("${spring.profiles.include}")  
	private String databaseIs ;

	public AuditInfo getAuditInfo() {
		if (databaseIs.equals("postgres")) {
			return new AuditInfoPostgres();
		} else {
			return new AuditInfoMongo();
		}
	}

}
