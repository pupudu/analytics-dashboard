package org.wso2.carbon.analytics.dashboard.admin.data;

import java.util.ArrayList;

public class Dashboard {

    String id;
    String title;
    String group;
    ArrayList<String> roles = new ArrayList<>();
    ArrayList<WidgetMetaData> widgets = new ArrayList<>();

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getGroup() {
        return group;
    }

    public void setGroup(String group) {
        this.group = group;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public ArrayList<String> getRoles() {
        return roles;
    }

    public void setRoles(ArrayList<String> roles) {
        this.roles = roles;
    }

    public ArrayList<WidgetMetaData> getWidgets() {
        return widgets;
    }

    public void setWidgets(ArrayList<WidgetMetaData> widgets) {
        this.widgets = widgets;
    }


}
