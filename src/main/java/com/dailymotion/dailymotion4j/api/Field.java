package com.dailymotion.dailymotion4j.api;

import java.util.HashSet;
import java.util.Set;

public abstract class Field {

	Set<String> fields = new HashSet<String>();
	
	@SuppressWarnings("unchecked")
	public <T extends Field> T addField(final String field) {
		fields.add(field);
		return (T) this;
	}
	
	public void addFields(final Set<String> fields) {
		this.fields = fields;
	}
	
	public Set<String> getFields() {
		return this.fields;
	}
	
}
