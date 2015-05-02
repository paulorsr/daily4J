package com.dailymotion.dailymotion4j.model;

import com.dailymotion.dailymotion4j.annotation.Dailymotion;

/**
 * 
 * @author paulo
 *
 */
public final class Video {
	
	@Dailymotion("id")
	private String id;
	
	@Dailymotion("title")
	private String title;
	
	@Dailymotion("channel")
	private String channel;
	
	@Dailymotion("owner")
	private String ownerId;

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
	 * @return the title
	 */
	public String getTitle() {
		return title;
	}

	/**
	 * @param title the title to set
	 */
	public void setTitle(String title) {
		this.title = title;
	}

	/**
	 * @return the channel
	 */
	public String getChannel() {
		return channel;
	}

	/**
	 * @param channel the channel to set
	 */
	public void setChannel(String channel) {
		this.channel = channel;
	}

	/**
	 * @return the ownerId
	 */
	public String getOwnerId() {
		return ownerId;
	}

	/**
	 * @param ownerId the ownerId to set
	 */
	public void setOwnerId(String ownerId) {
		this.ownerId = ownerId;
	}

}
