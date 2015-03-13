package org.wso2.carbon.analytics.dashboard.admin.data;

import java.util.ArrayList;

public class Dashboard {

    int id;
    String title;
    String group;
    ArrayList<String> roles=new ArrayList<>();
    ArrayList<String> widgets =new ArrayList<>();

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

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public ArrayList<String> getRoles() {
        return roles;
    }

    public void setRoles(ArrayList<String> roles) {
        this.roles = roles;
    }

    public ArrayList<String> getWidgets() {
        return widgets;
    }

    public void setWidgets(ArrayList<String> widgets) {
        this.widgets = widgets;
    }


}
