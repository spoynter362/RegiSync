package com.aca.demo.model;

public class Gift {
	private String sku;  
	private String description;
	private String pictureUrl;
	private String needed;
	private String purchased;
	private String price;
	private int registryId;
	private String storeId;
	
	public String getSku() {
		return sku;
	}
	public void setSku(String sku) {
		this.sku = sku;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getPictureUrl() {
		return pictureUrl;
	}
	public void setPictureUrl(String pictureUrl) {
		this.pictureUrl = pictureUrl;
	}
	public String getNeeded() {
		return needed;
	}
	public void setNeeded(String needed) {
		this.needed = needed;
	}
	public String getPurchased() {
		return purchased;
	}
	public void setPurchased(String purchased) {
		this.purchased = purchased;
	}
	public String getPrice() {
		return price;
	}
	public void setPrice(String price) {
		this.price = price;
	}
	
	public int getRegistryId() {
		return registryId;
	}
	public void setRegistryId(int registryId) {
		this.registryId = registryId;
	}
	
	
	@Override
	public String toString() {		
		return "sku: " + sku + ", description: " + description + ", price: " + price;
	}
	public String getStoreId() {
		return storeId;
	}
	
	public void setStoreId(String storeId) {
		this.storeId = storeId;
	}

}