package com.audit.utils;
import org.hibernate.dialect.PostgreSQL94Dialect;

import java.sql.Types;

public class JsonbExtensionToPostgreSQLDialect extends PostgreSQL94Dialect {

	public JsonbExtensionToPostgreSQLDialect() {
		super();
		registerColumnType(Types.JAVA_OBJECT, JsonbUserType.JSONB_TYPE);
	}
}