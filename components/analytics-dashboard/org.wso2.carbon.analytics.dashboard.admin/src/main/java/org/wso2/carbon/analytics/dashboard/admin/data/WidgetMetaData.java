package org.wso2.carbon.analytics.dashboard.admin.data;

/**
 * Created by dodan on 3/13/15.
 */
public class WidgetMetaData {
    String id;
    WidgetDimensions dimensions;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public WidgetDimensions getDimensions() {
        return dimensions;
    }

    public void setDimensions(WidgetDimensions dimensions) {
        this.dimensions = dimensions;
    }
}
