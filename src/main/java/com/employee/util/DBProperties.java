package com.employee.util;

import lombok.Data;

import java.io.Serializable;

@Data
public class DBProperties implements Serializable {
	private String cloudSqlInstance;
	private String cloudSqlUser;
	private String cloudSqlPassword;
	private String cloudSqlDatabase;
	private String cloudSqlPath;
	private String cloudSqlDriver;
}
