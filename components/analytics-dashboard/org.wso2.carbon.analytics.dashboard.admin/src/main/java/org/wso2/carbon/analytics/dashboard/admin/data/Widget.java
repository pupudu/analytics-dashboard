package org.wso2.carbon.analytics.dashboard.admin.data;

public class Widget {

    String id;
    String title;
    WidgetConfig config;
    WidgetDimensions position;

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

    public WidgetDimensions getPosition() {
        return position;
    }

    public void setPosition(WidgetDimensions position) {
        this.position = position;
    }
}
