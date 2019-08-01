package com.nazar.model.dao.implementations;

import javax.sql.DataSource;
import org.apache.commons.dbcp.BasicDataSource;
import org.apache.log4j.Logger;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class ConnectionPoolHolder {
    private static final Logger logger = Logger.getLogger(ConnectionPoolHolder.class);
    private static final String DB_URL = "db.connection.url";
    private static final String DB_USERNAME = "db.connection.username";
    private static final String DB_PASSWORD = "db.connection.password";
    private static final String DB_DRIVER = "db.connection.driver";
    private static volatile DataSource dataSource;
    public static DataSource getDataSource(){

        if (dataSource == null){
            synchronized (ConnectionPoolHolder.class) {
                if (dataSource == null) {
                    BasicDataSource ds = new BasicDataSource();
                    Properties properties = new Properties();
                    String propFile = "db.properties";
                    try (InputStream inputStream = Thread.currentThread()
                            .getContextClassLoader()
                            .getResourceAsStream(propFile)) {
                        if (inputStream != null) {
                            properties.load(inputStream);
                        } else {
                            logger.debug("Property file '" + propFile + "' not found");
                            throw new RuntimeException();
                        }
                        Class.forName(properties.getProperty(DB_DRIVER));
                        ds.setUrl(properties.getProperty(DB_URL));
                        ds.setUsername(properties.getProperty(DB_USERNAME));
                        ds.setPassword(properties.getProperty(DB_PASSWORD));
                        ds.setMinIdle(5);
                        ds.setMaxIdle(10);
                        ds.setMaxOpenPreparedStatements(100);
                        dataSource = ds;
                    } catch (IOException | ClassNotFoundException e) {
                       logger.error("File not found " + propFile);
                    }
                }
            }
        }
        return dataSource;
    }
}
