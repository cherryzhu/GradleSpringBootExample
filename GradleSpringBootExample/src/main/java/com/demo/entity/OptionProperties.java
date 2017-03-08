package com.demo.entity;

import java.util.Map;

public abstract class OptionProperties {
	private Map<String, String> options;

	public Map<String, String> getOptions() {
		return options;
	}

	public void setOptions(Map<String, String> options) {
		this.options = options;
	}
}
