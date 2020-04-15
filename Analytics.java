package com.aca.demo.model;

public class Analytics {
	
	private int totalGifts;  
	private int totalItems;
	private double totalCost;
	private double averageGiftCost;
	private int giftsNeeded;
	private int giftsPurchased;

	
	public int getTotalGifts() {
		return totalGifts;
	}
	public void setTotalGifts(int totalGifts) {
		this.totalGifts = totalGifts;
	}
	public int getTotalItems() {
		return totalItems;
	}
	public void setTotalItems(int totalItems) {
		this.totalItems = totalItems;
	}
	public double getTotalCost() {
		return totalCost;
	}
	public void setTotalCost(double totalCost) {
		this.totalCost = totalCost;
	}
	public double getAverageGiftCost() {
		return averageGiftCost;
	}
	public void setAverageGiftCost(double averageGiftCost) {
		this.averageGiftCost = averageGiftCost;
	}
	public int getGiftsNeeded() {
		return giftsNeeded;
	}
	public void setGiftsNeeded(int giftsNeeded) {
		this.giftsNeeded = giftsNeeded;
	}
	public int getGiftsPurchased() {
		return giftsPurchased;
	}
	public void setGiftsPurchased(int giftsPurchased) {
		this.giftsPurchased = giftsPurchased;
	}
	
}
