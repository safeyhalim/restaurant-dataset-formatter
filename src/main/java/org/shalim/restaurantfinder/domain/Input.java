package org.shalim.restaurantfinder.domain;

public class Input {
	private String dbPath;
	private String apiKey;
	
	public Input() {}
	
	public Input(String dbPath, String apiKey) {
		setDbPath(dbPath);
		setApiKey(apiKey);
	}
	
	public String getDbPath() {
		return dbPath;
	}
	
	public void setDbPath(String dbPath) {
		this.dbPath = dbPath;
	}
	
	public String getApiKey() {
		return apiKey;
	}
	
	public void setApiKey(String apiKey) {
		this.apiKey = apiKey;
	}
}
