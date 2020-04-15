package com.aca.demo.model;

public class Registry {
	private int registryId;
	private String firstName;
	private String lastName;
	private String email;
	private String amazonUrl;
	
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public int getRegistryId() {
		return registryId;
	}
	public void setRegistryId(int registryId) {
		this.registryId = registryId;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getAmazonUrl() {
		return amazonUrl;
	}
	public void setAmazonUrl(String amazonUrl) {
		this.amazonUrl = amazonUrl;
	}

}
