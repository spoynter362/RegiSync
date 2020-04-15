package com.aca.demo.service;

import java.util.List;

import com.aca.demo.dao.GiftDAO;
import com.aca.demo.model.Gift;

public class GiftService {
	
	public static List<Gift> getAllGifts() {		
		return GiftDAO.getAllGifts();
	}
	
	public static List<Gift> getByGiftSku(String sku) {
		
		return GiftDAO.getByGiftSku(sku);
	}
	
public static List<Gift> getGiftsByRegistry(String registryId) {
		
		return GiftDAO.getGiftsByRegistry(registryId);
	}

	public static Gift insertGift(Gift newGift) {
		int rowCount = GiftDAO.insertGifts(newGift);
		System.out.println("number of gift inserts: " + rowCount);
		return newGift;
	}

	public static int deleteGift(String sku) {
		return GiftDAO.deleteGifts(sku);
	}

	public static Gift updateGifts(Gift gift) {
		GiftDAO.updateGifts(gift);
		
		return gift;
	}

	public static List<Gift> getGiftsByStoreId(String storeId, String registryId) {
		return GiftDAO.getGiftsByStoreId(storeId, registryId);
	}

	public static int deleteGiftsByRegistryId(int registryId) {
		return GiftDAO.deleteGiftsByRegistryId(registryId);
	}

}
