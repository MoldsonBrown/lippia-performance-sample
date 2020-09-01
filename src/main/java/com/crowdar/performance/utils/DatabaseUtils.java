package com.crowdar.performance.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.crowdar.core.PropertyManager;

public class DatabaseUtils {

	private static Connection getConnection(String host) throws SQLException {	
		return DriverManager.getConnection(	
											String.format(PropertyManager.getProperty("db.connection.string"),host), 
											PropertyManager.getProperty("db.connection.user"),
											PropertyManager.getProperty("db.connection.pass") 
									       );
	}
	
	private static Connection getConnection() throws SQLException {	
		return DriverManager.getConnection(	
											PropertyManager.getProperty("db.connection.string"), 
											PropertyManager.getProperty("db.connection.user"),
											PropertyManager.getProperty("db.connection.pass") 
									       );
	}
	
	public static List<Map<String, Object>> executeQueryWithHost(String queryString,String host) throws SQLException {
		return executeQuery(queryString,getConnection(host));
	}
	
	public static List<Map<String, Object>> executeQuery(String queryString) throws SQLException {
		return executeQuery(queryString,getConnection());
	}
	
	private static List<Map<String, Object>> executeQuery(String queryString,Connection connection) throws SQLException {
		List<Map<String, Object>> records = null;
		ResultSet rs = null;
		try {
			try (Statement st = connection.createStatement()) {
				rs = st.executeQuery(queryString);
				if(rs!=null) {
					ResultSetMetaData md = rs.getMetaData();
					int columns = md.getColumnCount();
					// list to have all rows
					records = new ArrayList<>(columns);
					// load data for each column / record
					while (rs.next()) {
						HashMap<String, Object> row = new HashMap<>(columns);
						// add column/value for a record
				        for (int i = 1; i <= columns; ++i) {
				        	row.put(md.getColumnName(i), rs.getObject(i));
				        }
				        records.add(row);
					}
				}
			}
		} catch (SQLException e) {
			throw e;
		}finally {
			try {
				connection.close();
			} catch (SQLException e) {
				throw e;
			}
		}
		
		return records;
	}

	public static String uniqueResult(String queryString) throws SQLException {
		ResultSet rs = null;
		try {
			try (Statement st = getConnection().createStatement()) {
				rs = st.executeQuery(queryString);
				rs.next();
				return rs.getObject(1).toString();
			}
		} catch (SQLException e) {
			throw e;
		}finally {
			try {
				getConnection().close();
			} catch (SQLException e) {
				throw e;
			}
		}
	}
	
	
	public static boolean executeUpdate(String ddlString) throws SQLException {
		int recAffected = 0;
		try (Statement st = getConnection().createStatement()) {
			recAffected = st.executeUpdate(ddlString);
		} catch (SQLException e) {
			throw e;
		}finally {
			try {
				getConnection().close();
			} catch (SQLException e) {
				throw e;
			}
		}
		return recAffected>0;	
	}
}
