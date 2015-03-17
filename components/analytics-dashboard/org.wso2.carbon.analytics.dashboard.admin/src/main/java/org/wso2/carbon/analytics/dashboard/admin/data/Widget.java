package org.wso2.carbon.analytics.dashboard.admin.data;

public class Widget {

	String id;
	String title;
	WidgetConfig config;

	public WidgetConfig getConfig() {
		return config;
	}

	public void setConfig(WidgetConfig config) {
		this.config = config;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

}
