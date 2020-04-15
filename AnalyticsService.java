package com.aca.demo.service;

import com.aca.demo.dao.AnalyticsDAO;
import com.aca.demo.model.Analytics;

public class AnalyticsService {

	public static Analytics getAnalytics( int registryId) {		
		Analytics a = new Analytics();
	 int value = AnalyticsDAO.giftCountByRegistry(registryId);
	 	a.setTotalGifts(value);
 	int valuePurchased = AnalyticsDAO.giftsPurchasedByRegistry(registryId);
	 	a.setGiftsPurchased(valuePurchased);
//	 	ValueNeeded is also the total item count
	 int valueNeeded = AnalyticsDAO.giftsNeededByRegistry(registryId);
	 	a.setGiftsNeeded(valueNeeded);
	 double totalCost = AnalyticsDAO.costByRegistry(registryId);
	 	a.setTotalCost(totalCost);
	
	 
	 return a;
	}
	
	
	
}
