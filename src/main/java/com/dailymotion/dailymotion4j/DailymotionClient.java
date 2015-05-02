package com.dailymotion.dailymotion4j;

import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import org.codehaus.jackson.JsonNode;
import org.codehaus.jackson.map.ObjectMapper;
import org.jboss.resteasy.client.jaxrs.ResteasyWebTarget;

import com.dailymotion.dailymotion4j.annotation.DailymotionMapper;
import com.dailymotion.dailymotion4j.api.DailymotionInterface;
import com.dailymotion.dailymotion4j.api.SearchField;
import com.dailymotion.dailymotion4j.api.SelectionField;
import com.dailymotion.dailymotion4j.api.SortingField;
import com.dailymotion.dailymotion4j.exceptions.DailymotionResponseProcessingException;
import com.dailymotion.dailymotion4j.exceptions.DailymotionTokenException;
import com.dailymotion.dailymotion4j.model.Video;
import com.dailymotion.dailymotion4j.model.User;

public final class DailymotionClient {
	
	private final String baseUrl = "https://api.dailymotion.com";
	private String accessToken;
	private DailymotionInterface dInterface;
	private ObjectMapper mapper;
	
	public DailymotionClient() {
		Client client = ClientBuilder.newClient();
        WebTarget target = client.target(baseUrl);
        ResteasyWebTarget rtarget = (ResteasyWebTarget) target;
        this.dInterface = rtarget.proxy(DailymotionInterface.class);
        this.mapper = new ObjectMapper();
	}
	
	public DailymotionClient(final String accessToken) {
		this();
		this.accessToken = accessToken;
	}
	
	public User getMe() throws DailymotionTokenException, DailymotionResponseProcessingException {
		if (accessToken == null) {
			throw new DailymotionTokenException("You need an access token to acompplish this!");
		} else {
			Response response = dInterface.getMe(accessToken);
			if (response.getStatus() == Status.OK.getStatusCode()) {
				return response.readEntity(User.class);
			} else {
				throw new DailymotionResponseProcessingException("Status code " + response.getStatus());
			}
		}
	}
	
	public User getUser(final String id) throws DailymotionResponseProcessingException {
		Response response = dInterface.getUser(id);
		if (response.getStatus() == Status.OK.getStatusCode()) {
			return response.readEntity(User.class);
		} else {
			throw new DailymotionResponseProcessingException("Status code " + response.getStatus());
		}
	}
	
	public Video getVideo(final String id) throws DailymotionResponseProcessingException {
		
		try {
			 Response response = dInterface.getVideo(id, null);
			// Check response status
			if (response.getStatus() == Status.OK.getStatusCode()) {
				return DailymotionMapper.getInstance().mapJsonToObject(mapper.readTree(response.readEntity(String.class)), Video.class);
			} else {
				throw new DailymotionResponseProcessingException("Status code " + response.getStatus());
			}

		} catch (Exception e) {
			throw new DailymotionResponseProcessingException(e);
		}
	}
	
	public <T> T getVideo(final String id, SelectionField selectionFields, Class<T> objectClass) throws DailymotionResponseProcessingException {
		
		try {
			 Response response = dInterface.getVideo(id, selectionFields.getFields());
			// Check response status
			if (response.getStatus() == Status.OK.getStatusCode()) {
				return DailymotionMapper.getInstance().mapJsonToObject(mapper.readTree(response.readEntity(String.class)), objectClass);
			} else {
				throw new DailymotionResponseProcessingException("Status code " + response.getStatus());
			}

		} catch (Exception e) {
			throw new DailymotionResponseProcessingException(e);
		}
	}
	
	public <T> List<T> getVideos(final SearchField searchFields, SortingField sortingFields, SelectionField selectionFields, Class<T> objectClass, int userLimit) throws DailymotionResponseProcessingException {
		
		final int pageLimit = 100;
		List<T> finalList = new ArrayList<T>();
		boolean hasMore = false;
		int page = 1;
		int localLimit = 0;
		
		if (userLimit <= 0 || userLimit > 10000) {
			userLimit = 10000;
		}
		
		try {
			do {
				Response response = dInterface.getVideos(searchFields.getFields(), sortingFields.getFields(), selectionFields.getFields(), page++, pageLimit);
				JsonNode jsonNode = mapper.readTree(response.readEntity(String.class));
				if (jsonNode.get("has_more") != null) {
					hasMore = Boolean.parseBoolean(jsonNode.get("has_more").asText());
				} else {
					hasMore = false;
				}
				if (response.getStatus() == Status.OK.getStatusCode()) {
					final List<T> objectList = DailymotionMapper.getInstance().mapJsonToList(jsonNode, objectClass);
					int listSize = objectList.size();
					for (int index = 0 ; index < listSize && localLimit < userLimit ; index++, localLimit++) {
						finalList.add(objectList.get(index));
					}
				} else {
					throw new DailymotionResponseProcessingException("Status code " + response.getStatus());
				}
			} while(hasMore && localLimit < userLimit);
			return finalList;
		} catch (Exception e) {
			if (e instanceof NullPointerException) {
				throw new DailymotionResponseProcessingException("Parameters can not be null!");
			} else {
				throw new DailymotionResponseProcessingException(e);
			}
		}
	}
	
}
