package org.shalim.restaurantfinder.domain;

public class Input {
	private String dbPath;
	private String apiKey;
	private String dumpPath;
	
	public Input() {}
	
	public Input(String dbPath, String apiKey, String dumpPath) {
		setDbPath(dbPath);
		setApiKey(apiKey);
		setDumpPath(dumpPath);
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

	public String getDumpPath() {
		return dumpPath;
	}

	public void setDumpPath(String dumpPath) {
		this.dumpPath = dumpPath;
	}
}
