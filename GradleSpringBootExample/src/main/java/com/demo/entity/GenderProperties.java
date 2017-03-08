package com.demo.entity;

import java.util.Map;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties
public class GenderProperties {
	
	private Map<String, String> gender;

	public Map<String, String> getGender() {
		return gender;
	}

	public void setGender(Map<String, String> gender) {
		this.gender = gender;
	}
	
}
