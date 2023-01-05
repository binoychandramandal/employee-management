package com.employee.util;

import com.google.gson.Gson;
import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.sql.DataSource;
import java.io.InputStream;
import java.io.InputStreamReader;

public class CloudSqlUtil {

    private static final Logger LOGGER = LoggerFactory.getLogger(CloudSqlUtil.class);

    /**
     * Method to create connection pool of 5
     *
     * @return
     */
    private  DataSource createConnectionPool() {
        String fileName = "/dbProperties-dev.json";
        Gson gson = new Gson();
        InputStream inputStream = CloudSqlUtil.class.getResourceAsStream(fileName);
        DBProperties dbProperties = gson.fromJson(new InputStreamReader(inputStream), DBProperties.class);
        // [START cloud_sql_mysql_servlet_create]
        // The configuration object specifies behaviors for the connection pool.
        HikariConfig config = new HikariConfig();
        // Configure which instance and what database user to connect with.
        config.setJdbcUrl(String.format("jdbc:mysql:///%s", dbProperties.getCloudSqlDatabase()));
        config.setUsername(dbProperties.getCloudSqlUser()); // e.g. "root", "postgres"
        config.setPassword(dbProperties.getCloudSqlPassword()); // e.g. "my-password"
        config.addDataSourceProperty("socketFactory", "com.google.cloud.sql.mysql.SocketFactory");
        config.addDataSourceProperty("cloudSqlInstance", dbProperties.getCloudSqlInstance());
        config.addDataSourceProperty("useSSL", "false");
        config.setMaximumPoolSize(15);
        config.setMinimumIdle(5);
        config.setConnectionTimeout(10000); // 10 seconds
        config.setIdleTimeout(600000); // 10 minutes
        config.setMaxLifetime(1800000); // 30 minutes
        LOGGER.info("DataSource object has been initialized from file: {}", fileName);
        return new HikariDataSource(config);
    }

}
