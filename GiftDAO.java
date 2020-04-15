package com.aca.demo.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import com.aca.demo.model.Gift;

public class GiftDAO {

	public static void main(String[] args) {
		
	}
	
	
	private final static String allGiftColumns = 
			"   sku, description, imageUrl, needed, purchased, price, registryId, storeId ";
	
	
	private final static String sqlSelectAllGifts = 
//			"   SELECT sku, description, needed, purchased, price, registryId, storeId " + 
			"   SELECT " + allGiftColumns + " " +
			"   FROM gifts ";
	
	private final static String sqlSelectByGiftSku = 
			"   SELECT sku, description, needed, purchased, price, registryId, storeId " + 
			"   FROM gifts " +
			"   WHERE sku = ?";
	
	private final static String sqlSelectByStoreId = 
			"   SELECT sku, description, needed, purchased, price, registryId, storeId " + 
			"   FROM gifts " +
			"   WHERE (storeId = ?) AND (registryId = ?)";
	
	private final static String sqlSelectGiftsByRegistry = 
			"   SELECT sku, description, needed, purchased, price, registryId, storeId " + 
			"   FROM gifts " +
			"   WHERE (registryId = ?)";
	
	
	private final static String sqlDeleteGiftsByRegistry = 
	"   DELETE " +
	"   FROM gifts " +
	"   WHERE registryId = ?";
	
	private final static String sqlDeleteGiftsFromAmazon = 
			"   DELETE " +
			"   FROM gifts " +
			"   WHERE (storeId = ?) AND (registryId = ?)";
	
	private final static String sqlDeleteGiftsFromTarget = 
			"   DELETE " +
			"   FROM gifts " +
			"   WHERE (storeId = ?) AND (registryId = ?)";
	
	
	private final static String sqlInsertGift = 
			"   Insert INTO gifts ( sku, description, needed, purchased, price, registryId, storeId ) " +
			"   VALUES (?,?,?,?,?,?,?) ";
	
	private final static String sqlUpdateGift = 
			"   UPDATE gifts " + 
			"   SET description = ? , price = ? " + 
			"   WHERE sku = ?";
	
	private final static String sqlDeleteStatement = 
			"   DELETE " +
			"   FROM gifts " +
			"   WHERE sku = ?";
	

	public static List<Gift> getAllGifts() {
		List <Gift> gifts = new ArrayList<Gift>();
		
		ResultSet result = null;
		Statement statement = null;
		
		Connection conn = MariaDbUtil.getConnection();
		
		try {
		statement = conn.createStatement();
		result = statement.executeQuery(sqlSelectAllGifts);
		
		while(result.next()) {
			Gift gift = makeGift(result);
			
			gifts.add(gift);
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
		return gifts;
  }

	public static ArrayList<Gift> getByGiftSku(String sku) {
		ArrayList<Gift> gifts = new ArrayList<Gift>();
		
		ResultSet result = null;
		PreparedStatement statement = null;
		
		Connection conn = MariaDbUtil.getConnection();
		
		try {
		statement = conn.prepareStatement(sqlSelectByGiftSku);
		statement.setString(1, sku);
		
		result = statement.executeQuery();
		
		while(result.next()) {
			Gift gift = makeGift(result);
			
			gifts.add(gift);
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
		return gifts;
		
	}
	
	public static ArrayList<Gift> getGiftsByStoreId(String storeId, String registryId) {
		ArrayList<Gift> gifts = new ArrayList<Gift>();
		
		ResultSet result = null;
		PreparedStatement statement = null;
		
		Connection conn = MariaDbUtil.getConnection();
		
		try {
		statement = conn.prepareStatement(sqlSelectByStoreId);
		statement.setString(1, storeId);
		statement.setString(2, registryId);
		
		result = statement.executeQuery();
		
		while(result.next()) {
			Gift gift = makeGift(result);
			
			gifts.add(gift);
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
		return gifts;
		
	}
	
	
	public static ArrayList<Gift> getGiftsByRegistry(String registryId) {
		ArrayList<Gift> gifts = new ArrayList<Gift>();
		
		ResultSet result = null;
		PreparedStatement statement = null;
		
		Connection conn = MariaDbUtil.getConnection();
		
		try {
		statement = conn.prepareStatement(sqlSelectGiftsByRegistry);
		statement.setString(1, registryId);
		
		result = statement.executeQuery();
		
		while(result.next()) {
			Gift gift = makeGift(result);
			
			gifts.add(gift);
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
		return gifts;
		
	}

	public static int insertGiftsAmazon(List <AmazonGift> gifts, int registryId) {
		int rowCount = 0;
		
		deleteGiftsFromAmazon(registryId, "1");
		
		PreparedStatement statement = null;
		Connection conn = MariaDbUtil.getConnection();
		
		try {
			statement = conn.prepareStatement(sqlInsertGift);
			
			for(AmazonGift newGift: gifts) {
			statement.setString(1, newGift.getSku());
			statement.setString(2, newGift.getDescription());
//			statement.setString(3, newGift.getPictureUrl());
			statement.setString(3, newGift.getNeeded());
			statement.setString(4, newGift.getPurchased());
			statement.setString(5, newGift.getPrice());
			statement.setInt(6, registryId);
			statement.setString(7, newGift.getStoreId());
				
			rowCount = statement.executeUpdate();
			}
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
	
	
		
	
	public static int insertGifts(Gift newGift) {
		int rowCount = 0;
		
		PreparedStatement statement = null;
		Connection conn = MariaDbUtil.getConnection();
		
		try {
			statement = conn.prepareStatement(sqlInsertGift);
			
			statement.setString(1, newGift.getSku());
			statement.setString(2, newGift.getDescription());
//			statement.setString(3, newGift.getPictureUrl());
			statement.setString(3, newGift.getNeeded());
			statement.setString(4, newGift.getPurchased());
			statement.setString(5, newGift.getPrice());
			statement.setInt(6, newGift.getRegistryId());
			statement.setString(7, newGift.getStoreId());
			
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
	
	
	private static Gift makeGift (ResultSet result) throws SQLException{
		Gift gift = new Gift();

		gift.setSku(result.getString("sku"));
		gift.setDescription(result.getString("description"));
//		gift.setPictureUrl(result.getString("pictureUrl"));
		gift.setNeeded(result.getString("needed"));
		gift.setPurchased(result.getString("purchased"));
		gift.setPrice(result.getString("price"));
		gift.setRegistryId(result.getInt("registryId"));
		gift.setStoreId(result.getString("storeId"));
		
		
		return gift;
	}

	public static int deleteGifts(String sku) {
		int rowCount = 0;
		PreparedStatement statement = null;
		Connection conn = MariaDbUtil.getConnection();
		try {
			statement = conn.prepareStatement(sqlDeleteStatement);
			statement.setString(1, sku);
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
		System.out.println("number of gifts deleted: " + rowCount);
		return rowCount;
	}
	
	
	public static int deleteGiftsByRegistryId(int registryId) {
		int rowCount = 0;
		PreparedStatement statement = null;
		Connection conn = MariaDbUtil.getConnection();
		try {
			statement = conn.prepareStatement(sqlDeleteGiftsByRegistry);
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
		System.out.println("number of gifts deleted: " + rowCount);
		return rowCount;
	}
	
	public static int deleteGiftsFromAmazon (int registryId,String storeId) {
		int rowCount = 0;
		PreparedStatement statement = null;
		Connection conn = MariaDbUtil.getConnection();
		try {
			statement = conn.prepareStatement(sqlDeleteGiftsFromAmazon);
			statement.setString(1, storeId);
			statement.setInt(2, registryId);
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
		return rowCount;
	}
	
	public static int deleteGiftsFromTarget (int registryId,String storeId) {
		int rowCount = 0;
		PreparedStatement statement = null;
		Connection conn = MariaDbUtil.getConnection();
		try {
			statement = conn.prepareStatement(sqlDeleteGiftsFromTarget);
			statement.setString(1, storeId);
			statement.setInt(2, registryId);
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
		return rowCount;
	}
	

	public static Gift updateGifts(Gift gift) {
		int rowCount = 0;
		
		PreparedStatement statement = null;
		Connection conn = MariaDbUtil.getConnection();
		
		try {
			statement = conn.prepareStatement(sqlUpdateGift);
			
//			sku, description, pictureUrl, needed, purchased, price, registryId, storeId 
			
			statement.setString(3, gift.getSku());
			statement.setString(1, gift.getDescription());
			statement.setString(2, gift.getPrice());
//			statement.setString(4, gift.getRegistryId());
			
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
		
		return gift;
		
	}

}
