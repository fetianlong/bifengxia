package com.chatm.search.util;

import java.util.HashMap;
import org.apache.commons.dbcp.BasicDataSource;

import org.springframework.jdbc.core.JdbcTemplate;

public class JDBCHelper {
	public static HashMap<String, JdbcTemplate> templateMap = new HashMap<String, JdbcTemplate>();

	public static JdbcTemplate createMysqlTemplate(String templateName,String oracleDriver,
			String url, String username, String password, int initialSize,
			int maxActive) {

		BasicDataSource dataSource = new BasicDataSource();
		dataSource.setDriverClassName(oracleDriver);
		dataSource.setUrl(url);
		dataSource.setUsername(username);
		dataSource.setPassword(password);
		dataSource.setInitialSize(initialSize);
		dataSource.setMaxActive(maxActive);
		
		dataSource.setMaxWait(0);
//		dataSource.setm
		
		JdbcTemplate template = new JdbcTemplate(dataSource);
		templateMap.put(templateName, template);
		return template;
	}

	public static JdbcTemplate getJdbcTemplate(String templateName) {
		return templateMap.get(templateName);
	}
}
