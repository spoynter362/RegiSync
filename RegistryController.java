package com.aca.demo.controller;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import com.aca.demo.model.Registry;
import com.aca.demo.service.RegistryService;
	@Path("/registries")
	public class RegistryController {

		@GET
		@Produces(MediaType.APPLICATION_JSON)
		public List<Registry> getAllRegistries() {	
			
			return RegistryService.getAllRegistries();
		}		
		
		@PUT
		@Consumes(MediaType.APPLICATION_JSON)
		@Produces(MediaType.APPLICATION_JSON)
		public Registry updateRegistry(Registry updateRegistry) {	
			return RegistryService.updateRegistries(updateRegistry);
		}	
		
		@POST
		@Consumes(MediaType.APPLICATION_JSON)
		@Produces(MediaType.APPLICATION_JSON)
		public Registry insertRegistry(Registry insertRegistry) {	
			return RegistryService.insertRegistry(insertRegistry);
		}
		
		@DELETE
		@Path("/{registryId}")
		@Consumes(MediaType.APPLICATION_JSON)
		@Produces(MediaType.APPLICATION_JSON)
		public int deleteRegistries(@PathParam ("registryId")int registryId){
			
			System.out.println("delete registry: " + registryId);
			int registry = RegistryService.deleteRegistry(registryId);
			
			return registry;
		}
		
		@GET
		@Path("/{registryId}")
		@Produces(MediaType.APPLICATION_JSON)
		public List<Registry> getByRegistryId(@PathParam ("registryId")String registryId){	
			
			return RegistryService.getByRegistryId(registryId);
		}		
	}
