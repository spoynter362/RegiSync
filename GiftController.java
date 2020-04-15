package com.aca.demo.controller;

import java.util.List;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import com.aca.demo.dao.AmazonGift;
import com.aca.demo.dao.AmazonRegistry;
import com.aca.demo.dao.GiftDAO;
import com.aca.demo.model.Gift;
import com.aca.demo.service.GiftService;

@Path("/gifts")
public class GiftController {

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<Gift> getAllGifts() {	
		
		return GiftService.getAllGifts();
	}	
	
	@GET
	@Path("/{registryId}")
	@Produces(MediaType.APPLICATION_JSON)
	public List<Gift> getByRegistryId(@PathParam ("registryId")String registryId){	
		
		return GiftService.getGiftsByRegistry(registryId);
	}	
	
	@GET
	@Path("/amazon")
	@Produces(MediaType.APPLICATION_JSON)
	public List<AmazonGift> getAmazonGifts(@QueryParam("name") String name, @QueryParam("registryId") int registryId){
		AmazonRegistry aRegistry = new AmazonRegistry();
		List<AmazonGift> gifts = aRegistry.findGifts(name);
		GiftDAO.insertGiftsAmazon(gifts, registryId);
		return gifts;
	}
	
	
	@GET
	@Path("/{registryId}/{storeId}")
	@Produces(MediaType.APPLICATION_JSON)
	public List<Gift> getByStoreId(@PathParam ("storeId")String storeId,@PathParam ("registryId")String registryId){	
		
		return GiftService.getGiftsByStoreId(storeId, registryId);
	}		
	
	@DELETE
	@Path("/{sku}")
	@Produces(MediaType.APPLICATION_JSON)
	public int deleteGifts(@PathParam ("sku")String sku){
		
		System.out.println("delete gift: " + sku);
		int gift = GiftService.deleteGift(sku);
		
		return gift;
	}
	
}
