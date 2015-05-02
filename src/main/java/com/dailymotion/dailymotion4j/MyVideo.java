package com.dailymotion.dailymotion4j;

import com.dailymotion.dailymotion4j.annotation.Dailymotion;


public class MyVideo {
	
	@Dailymotion("title")
	private String name;
	@Dailymotion("owner.screenname")
	private String id;
	
	/**
	 * @return the id
	 */
	public String getId() {
		return id;
	}
	
	/**
	 * @param id the id to set
	 */
	public void setId(String id) {
		this.id = id;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}
	
	

}
