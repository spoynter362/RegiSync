package com.aca.demo.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import com.aca.demo.model.Registry;

public class RegistryDAO {
	
	private final static String allRegistryColumns = 
			"   registryId, firstName, lastName, email, amazonUrl ";
	
	private final static String sqlSelectAllRegistries = 
			"   SELECT registryId, firstName, lastName, email, amazonUrl " + 
			"   FROM registries ";
	
	private final static String sqlSelectByRegistryId = 
			"   SELECT registryId, firstName, lastName, email, amazonUrl " + 
			"   FROM registries " +
			"   WHERE registryId = ?";
	
	private final static String sqlInsertRegistry = 
			"   Insert INTO registries ( " + allRegistryColumns + " ) " +
			"   VALUES (?,?,?,?,?) ";
	
	private final static String sqlUpdateRegistry = 
			"   UPDATE registries " + 
			"   SET firstName = ?, lastName = ?, email = ?, amazonUrl = ? " + 
			"   WHERE registryId = ?";
	
	private final static String sqlDeleteStatement = 
			"   DELETE " +
			"   FROM registries " +
			"   WHERE registryId = ?";

	public static List<Registry> getAllRegistries() {
		List <Registry> registries = new ArrayList<Registry>();
		
		ResultSet result = null;
		Statement statement = null;
		
		Connection conn = MariaDbUtil.getConnection();
		
		try {
		statement = conn.createStatement();
		result = statement.executeQuery(sqlSelectAllRegistries);
		
		while(result.next()) {
			Registry registry = makeRegistry(result);
			
			registries.add(registry);
		}
	}catch (SQLException e) {
		e.printStackTrace();
	} finally {
		try {
			result.close();
			statement.close();
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
		return registries;
  }

	public static ArrayList<Registry> getByRegistryId(String registryId) {
		ArrayList<Registry> registries = new ArrayList<Registry>();
		
		ResultSet result = null;
		PreparedStatement statement = null;
		
		Connection conn = MariaDbUtil.getConnection();
		
		try {
		statement = conn.prepareStatement(sqlSelectByRegistryId);
		statement.setString(1, registryId);
		
		result = statement.executeQuery();
		
		while(result.next()) {
			Registry registry = makeRegistry(result);
			
			registries.add(registry);
		}
	}catch (SQLException e) {
		e.printStackTrace();
	} finally {
		try {
			result.close();
			statement.close();
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
		return registries;
		
	}

	public static int insertRegistries(Registry newRegistry) {
		int rowCount = 0;
		
		PreparedStatement statement = null;
		Connection conn = MariaDbUtil.getConnection();
		
		try {
			statement = conn.prepareStatement(sqlInsertRegistry);
			
			statement.setInt(1, newRegistry.getRegistryId());
			statement.setString(2, newRegistry.getFirstName());
			statement.setString(3, newRegistry.getLastName());
			statement.setString(4, newRegistry.getEmail());
			statement.setString(5, newRegistry.getAmazonUrl());
			
			rowCount = statement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			try {
				statement.close();
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return rowCount;
	}
	
	
	private static Registry makeRegistry (ResultSet result) throws SQLException{
		Registry registry = new Registry();
		
		registry.setRegistryId(result.getInt("registryId"));
		registry.setFirstName(result.getString("firstName"));
		registry.setLastName(result.getString("lastName"));
		registry.setEmail(result.getString("email"));
		registry.setAmazonUrl(result.getString("amazonUrl"));
		
		
		
		
		return registry;
	}

	public static int deleteRegistries(int registryId) {
		
		
		int rowCount = 0;
		PreparedStatement statement = null;
		Connection conn = MariaDbUtil.getConnection();
		try {
			statement = conn.prepareStatement(sqlDeleteStatement);
			statement.setInt(1, registryId);
			rowCount = statement.executeUpdate();
		}catch (SQLException e) {
			e.printStackTrace();
		}finally {
			try {
				statement.close();
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		System.out.println("number of Registries deleted: " + rowCount);
		return rowCount;
	}

	public static Registry updateRegistries(Registry registry) {
		int rowCount = 0;
		
		PreparedStatement statement = null;
		Connection conn = MariaDbUtil.getConnection();
		
		try {
			statement = conn.prepareStatement(sqlUpdateRegistry);
			
			statement.setInt(5, registry.getRegistryId());
			statement.setString(1, registry.getFirstName());
			statement.setString(2, registry.getLastName());
			statement.setString(3, registry.getEmail());
			statement.setString(4, registry.getAmazonUrl());
			
			rowCount = statement.executeUpdate();
			System.out.println(rowCount);
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			try {
				statement.close();
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		return registry;
		
	}

}
