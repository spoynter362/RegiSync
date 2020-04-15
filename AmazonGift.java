package com.aca.demo.dao;


	public class AmazonGift {

		private String sku;  //is this unique in Amazon/gift?
		private String description;
		private String pictureUrl;
		private String needed;
		private String purchased;
		private String price;
		private int registryId;
		private String storeId = "1";
		
		public String getSku() {
			return sku;
		}
		
		public String getDescription() {
			return description;
		}
		
		public String getPictureUrl() {
			return pictureUrl;
		}
		
		public String getNeeded() {
			return needed;
		}
		
		public String getPurchased() {
			return purchased;
		}
		
		public String getPrice() {
			return price;
		}
		
		public void setSku(String sku) {
			this.sku = sku;
		}
		
		public int getRegistryId() {
			return registryId;
		}
		public void setRegistryId(int registryId) {
			this.registryId = registryId;
		}
		
		public String getStoreId() {
			return storeId;
		}
		
		public void setDescription(String description) {
			this.description = description;
		}
		
		public void setPictureUrl(String pictureUrl) {
			this.pictureUrl = pictureUrl;
		}
		
		public void setNeeded(String needed) {
			this.needed = needed;
		}
		
		public void setPurchased(String purchased) {
			this.purchased = purchased;
		}
		
		public void setPrice(String price) {
			this.price = price;
		}
		
		@Override
		public String toString() {
			return "sku: " + sku + "\n" +
					"	description: " + description + "\n" +
					"	 pictureUrl: " + pictureUrl + "\n" +
					"	     needed: " + needed + "\n" +
					"	  purchased: " + purchased + "\n" +
					"	      price: " + price + "\n";
		}
		
	}
