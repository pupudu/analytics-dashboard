package org.wso2.carbon.analytics.dashboard.admin.data;

import java.util.ArrayList;

public class DataView {
    String name;
    String type;
    String dataSource;
    ArrayList<String> columns = new ArrayList<>();
    String filter;
    ArrayList<Widget> widgets =new ArrayList<>();

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getDataSource() {
        return dataSource;
    }

    public void setDataSource(String dataSource) {
        this.dataSource = dataSource;
    }

    public ArrayList<String> getColumns() {
        return columns;
    }

    public void setColumns(ArrayList<String> columns) {
        this.columns = columns;
    }

    public String getFilter() {
        return filter;
    }

    public void setFilter(String filter) {
        this.filter = filter;
    }

    public ArrayList<Widget> getWidgets() {
        return widgets;
    }

    public void setWidgets(ArrayList<Widget> widgets) {
        this.widgets = widgets;
    }

    public void addWidget(Widget widget){   //TODO-change return type to boolean???
        widgets.add(widget);
    }

    public boolean deleteWidget(String targetWidgetID){
        for(Widget widget: widgets){
            if(widget.getId().equals(targetWidgetID)){
                widgets.remove(widget);
                return true;
            }
        }
        return false;
    }

    public Widget getWidget(String widgetID) {
        for (Widget widget : widgets) {
            if (widget.getId().equals(widgetID)) {
                return widget;
            }
        }
        return null;
    }
}
