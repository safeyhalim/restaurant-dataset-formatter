package org.shalim.restaurantfinder.domain;

public class Input {
	private String dbPath;
	private String apiKey;
	private String dumpPath;
	private String dataSetPath;
	private int k;
	private boolean exportUserRatings;
	private boolean exportSocialContexts;
	
	public Input() {}
	
	public Input(String dbPath, String apiKey, String dumpPath, boolean exportUserRatings, boolean exportSocialContexts, String dataSetPath, int k) {
		setDbPath(dbPath);
		setApiKey(apiKey);
		setDumpPath(dumpPath);
		setExportUserRatings(exportUserRatings);
		setExportSocialContexts(exportSocialContexts);
		setDataSetPath(dataSetPath);
		setK(k);
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

	public boolean isExportUserRatings() {
		return exportUserRatings;
	}

	public void setExportUserRatings(boolean exportUserRatings) {
		this.exportUserRatings = exportUserRatings;
	}

	public String getDataSetPath() {
		return dataSetPath;
	}

	public void setDataSetPath(String dataSetPath) {
		this.dataSetPath = dataSetPath;
	}

	public int getK() {
		return k;
	}

	public void setK(int k) {
		this.k = k;
	}

	public boolean isExportSocialContexts() {
		return exportSocialContexts;
	}

	public void setExportSocialContexts(boolean exportSocialContexts) {
		this.exportSocialContexts = exportSocialContexts;
	}
}
