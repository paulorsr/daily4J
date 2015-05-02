package com.dailymotion.dailymotion4j.annotation;

import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

import org.codehaus.jackson.JsonNode;
import org.codehaus.jackson.JsonProcessingException;
import org.codehaus.jackson.map.ObjectMapper;

public final class DailymotionMapper {
	
	private static DailymotionMapper instance;
	private ObjectMapper mapper;
	
	private DailymotionMapper() {
		mapper = new ObjectMapper();
	}
	
	public static synchronized DailymotionMapper getInstance() {
		return instance == null ? new DailymotionMapper() : instance;
	}
	
	public <T> T mapJsonToObject(final JsonNode jsonNode, final Class<T> objectClass) throws IllegalArgumentException, IllegalAccessException, JsonProcessingException, IOException, NoSuchMethodException, SecurityException, InvocationTargetException, InstantiationException {
		
		T object = objectClass.newInstance();
		final Field[] fields = objectClass.getDeclaredFields();
		for (final Field field : fields) {
	        final Dailymotion fieldAnnotation = field.getAnnotation(Dailymotion.class);
	        if (fieldAnnotation != null) {
	        	if (jsonNode != null) {
	        		final String methodName = "set" + field.getName().substring(0, 1).toUpperCase() + field.getName().substring(1);
	        		final Method m = objectClass.getDeclaredMethod(methodName, field.getType());
	        		final JsonNode node = jsonNode.get(fieldAnnotation.value().isEmpty() ? field.getName() : fieldAnnotation.value());
	        		if (node != null) {
	        			m.invoke(object, node.asText());
	        		}
	        	}
	        }
	    }
		return object;
	}
	
	public <T> List<T> mapJsonToList(final JsonNode jsonNode, final Class<T> objectClass) throws IllegalArgumentException, IllegalAccessException, JsonProcessingException, IOException, NoSuchMethodException, SecurityException, InvocationTargetException, InstantiationException {
		
		List<T> objects = new ArrayList<T>();
		JsonNode objs = jsonNode.get("list");
		for (JsonNode obj: objs) {
			T object = mapJsonToObject(obj, objectClass);
			if (object != null) {
				objects.add(object);
			}
		}
		return objects;
	}

}
