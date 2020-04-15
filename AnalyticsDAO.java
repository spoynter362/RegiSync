package com.aca.demo.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


public class AnalyticsDAO {

	
	private final static String GiftCountByRegistry = 
			"   SELECT  COUNT(sku) AS value " +
			"   FROM gifts "  +
			"   WHERE registryId = ? ";
	
	private final static String CostByRegistry = 
			"   SELECT  price, needed " +
			"   FROM gifts "  +
			"   WHERE registryId = ? ";
	
	private final static String GiftsNeeded = 
			"   SELECT  SUM(needed) AS valueNeeded " +
			"   FROM gifts "  +
			"   WHERE registryId = ? ";
	
	private final static String GiftsPurchased = 
			"   SELECT  SUM(purchased) AS valuePurchased " +
			"   FROM gifts "  +
			"   WHERE registryId = ? ";
	
	
	
	
	public static int giftCountByRegistry(int registryId) {
		int value = 0;
		
		ResultSet result = null;
		PreparedStatement statement = null;
		
		Connection conn = MariaDbUtil.getConnection();
		
		try {
		statement = conn.prepareStatement(GiftCountByRegistry);
		statement.setInt(1, registryId);
		
		result = statement.executeQuery();
		
		while(result.next()) {
			value = result.getInt("value");
			
			
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
		return value;
		
	}
	
	
	public static int giftsPurchasedByRegistry(int registryId) {
		int valuePurchased = 0;
		
		ResultSet result = null;
		PreparedStatement statement = null;
		
		Connection conn = MariaDbUtil.getConnection();
		
		try {
		statement = conn.prepareStatement(GiftsPurchased);
		statement.setInt(1, registryId);
		
		result = statement.executeQuery();
		
		while(result.next()) {
			valuePurchased = result.getInt("valuePurchased");
			
			
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
		return valuePurchased;
		
	}
	
	
	public static int giftsNeededByRegistry(int registryId) {
		int valueNeeded = 0;
		
		ResultSet result = null;
		PreparedStatement statement = null;
		
		Connection conn = MariaDbUtil.getConnection();
		
		try {
		statement = conn.prepareStatement(GiftsNeeded);
		statement.setInt(1, registryId);
		
		result = statement.executeQuery();
		
		while(result.next()) {
			valueNeeded = result.getInt("valueNeeded") - 999;
			
			
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
		return valueNeeded;
		
	}
	
	public static double costByRegistry(int registryId) {
		double cost = 0;
		double totalCost = 0;
		int numberNeeded = 0;
		
		ResultSet result = null;
		PreparedStatement statement = null;
		
		Connection conn = MariaDbUtil.getConnection();
		
		try {
		statement = conn.prepareStatement(CostByRegistry);
		statement.setInt(1, registryId);
		
		result = statement.executeQuery();
		
		while(result.next()) {
			String price = result.getString("price");
			String needed = result.getString("needed");
			numberNeeded = Integer.parseInt(needed);
			
			price = price.replace("$", "");
			price = price.replaceAll("[^0-9.]", "");
			
			if (numberNeeded > 99) {
				numberNeeded = 1;
			}			
			try {
				cost = Double.parseDouble(price) * numberNeeded;
			} catch (NumberFormatException e) {
				
				cost = 0;
			}
				
			totalCost = totalCost + cost;
			
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
		return totalCost;
		
	}
	
}
