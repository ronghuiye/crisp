package com.ryan.crisp.config;

import java.util.HashMap;
import java.util.List;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties("order")
public class OrderConfig {

	private List<String> validations;
	private HashMap<String,Integer> columns;
	
	public List<String> getValidations() {
		return validations;
	}
	public void setValidations(List<String> validations) {
		this.validations = validations;
	}
	public HashMap<String, Integer> getColumns() {
		return columns;
	}
	public void setColumns(HashMap<String, Integer> columns) {
		this.columns = columns;
	}
}
