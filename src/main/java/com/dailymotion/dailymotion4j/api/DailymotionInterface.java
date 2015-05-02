package com.dailymotion.dailymotion4j.api;

import java.util.Set;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.dailymotion.dailymotion4j.exceptions.DailymotionResponseProcessingException;
import com.dailymotion.dailymotion4j.exceptions.DailymotionTokenException;

@Path("/")
public interface DailymotionInterface {

	@GET
	@Consumes(MediaType.APPLICATION_JSON)
	@Path("/user/{id}")
	Response getUser(@PathParam("id") String id);
	
	@GET
	@Consumes(MediaType.APPLICATION_JSON)
	@Path("user/me")
	Response getMe(@QueryParam("access_token") String accessToken) throws DailymotionTokenException;
	
	@GET
	@Consumes(MediaType.APPLICATION_JSON)
	@Path("/video/{id}")
	Response getVideo(@PathParam("id") String id, @QueryParam("fields") Set<String> selectionFields) throws DailymotionResponseProcessingException;
	
	@GET
	@Consumes(MediaType.APPLICATION_JSON)
	@Path("/videos")
	Response getVideos(@Context Set<String> sortingFields, @QueryParam("fields") Set<String> selectionFields, @QueryParam("page") int page, @QueryParam("limit") int limit) throws DailymotionResponseProcessingException;
	
	@GET
	@Consumes(MediaType.APPLICATION_JSON)
	@Path("/videos")
	Response getVideos(@QueryParam("search") Set<String> searchFields, @Context Set<String> sortingFields, @QueryParam("fields") Set<String> selectionFields, @QueryParam("page") int page, @QueryParam("limit") int limit) throws DailymotionResponseProcessingException;
	
}
