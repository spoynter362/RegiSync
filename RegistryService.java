package com.aca.demo.service;

import java.util.List;

import com.aca.demo.dao.GiftDAO;
import com.aca.demo.dao.RegistryDAO;
import com.aca.demo.model.Registry;

public class RegistryService {
	
	public static List<Registry> getAllRegistries() {		
		return RegistryDAO.getAllRegistries();
	}
	
	public static List<Registry> getByRegistryId(String registryId) {
		
		return RegistryDAO.getByRegistryId(registryId);
	}

	public static Registry insertRegistry(Registry newRegistry) {
		int rowCount = RegistryDAO.insertRegistries(newRegistry);
		System.out.println("number of Registry inserts: " + rowCount);
		return newRegistry;
	}

	public static int deleteRegistry(int registryId) {
				GiftDAO.deleteGiftsByRegistryId(registryId);
		return RegistryDAO.deleteRegistries(registryId);
	}

	public static Registry updateRegistries(Registry registry) {
		RegistryDAO.updateRegistries(registry);
		
		return registry;
	}


}
