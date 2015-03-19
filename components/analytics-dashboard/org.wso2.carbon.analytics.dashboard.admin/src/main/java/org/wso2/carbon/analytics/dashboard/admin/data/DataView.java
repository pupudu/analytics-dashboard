package org.wso2.carbon.analytics.dashboard.admin.data;

import org.apache.axis2.AxisFault;

import java.util.ArrayList;

public class DataView {
	String id;
	String displayName;
	String type;
	String dataSource;
	ArrayList<Column> columns = new ArrayList<>();
	String filter;
	ArrayList<Widget> widgets = new ArrayList<>();

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getDisplayName() {
		return displayName;
	}

	public void setDisplayName(String displayName) {
		this.displayName = displayName;
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

	public ArrayList<Column> getColumns() {
		return columns;
	}

	public void setColumns(ArrayList<Column> columns) {
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

	public void addWidget(Widget widget) throws AxisFault {
		if(widgets==null){
			widgets=new ArrayList<>();
		}
		if (getWidget(widget.getId()) == null) {
			widgets.add(widget);
		} else {
			throw new AxisFault("Widget with given ID already exists");
		}
	}

	public void updateWidget(Widget widget) throws AxisFault {
		Widget existingWidget = getWidget(widget.getId());
		if (existingWidget != null) {
			widgets.remove(existingWidget);
			widgets.add(widget);
		} else {
			throw new AxisFault("Widget with given ID does not exist");
		}
	}

	public Widget getWidget(String widgetID) {
		if(widgets==null){
			return null;
		}
		for (Widget widget : widgets) {
			if (widget.getId().equals(widgetID)) {
				return widget;
			}
		}
		return null;
	}

}
