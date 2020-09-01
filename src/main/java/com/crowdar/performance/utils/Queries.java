package com.crowdar.performance.utils;

import java.io.IOException;
import java.io.InputStream;
import java.sql.SQLException;
import java.util.Properties;

import com.crowdar.core.PropertyManager;

/**
 * Execute a db query defined in a properties file
 * (\src\test\resources\queries.properties) by parameter
 */
public class Queries {

    private static final String propFileName = "queries.properties";
    private static Properties props;

    public static Properties getQueries() throws SQLException {
        InputStream is = Queries.class.getResourceAsStream("/" + propFileName);
        if (is == null){
            throw new SQLException("Unable to load property file: " + propFileName);
        }
        //singleton
        if(props == null){
            props = new Properties();
            try {
                props.load(is);
            } catch (IOException e) {
                throw new SQLException("Unable to load property file: " + propFileName + "\n" + e.getMessage());
            }
            
            if(PropertyManager.getProperty("data.generate.query") != null) {
            	props.put("data.generate.query", PropertyManager.getProperty("data.generate.query"));
            }
        }
        return props;
    }

    public static String getQuery(String query) throws SQLException{
        return getQueries().getProperty(query);
    }

}