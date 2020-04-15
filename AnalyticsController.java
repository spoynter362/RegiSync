package com.aca.demo.controller;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import com.aca.demo.model.Analytics;
import com.aca.demo.service.AnalyticsService;


@Path("/analytics")
public class AnalyticsController {
	
	
	@GET
	@Path("/{registryId}")
	@Produces(MediaType.APPLICATION_JSON)
	public Analytics getAnalyticsByRegistryId(@PathParam ("registryId")int registryId){	
		
		return AnalyticsService.getAnalytics(registryId);
	}	

}
