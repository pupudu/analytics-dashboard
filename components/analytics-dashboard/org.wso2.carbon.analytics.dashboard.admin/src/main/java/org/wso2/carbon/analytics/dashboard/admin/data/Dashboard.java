package org.wso2.carbon.analytics.dashboard.admin.data;

import org.apache.axis2.AxisFault;

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

	public void addWidget(WidgetMetaData widget) throws AxisFault {
		for (WidgetMetaData existingWidget : widgets) {
			if (existingWidget.getId().equals(widget.getId())) {
				throw new AxisFault("Widget with given ID already exists in the dashboard");
			}
		}
		widgets.add(widget);
	}

	public void updateWidget(WidgetMetaData widget) throws AxisFault {
		deleteWidget(widget.getId());
		widgets.add(widget);
	}

	public void deleteWidget(String widgetID) throws AxisFault {
		for (WidgetMetaData existingWidget : widgets) {
			if (existingWidget.getId().equals(widgetID)) {
				widgets.remove(existingWidget);
				return;
			}
		}
		throw new AxisFault("Widget With given ID does not exist in the dashboard");
	}

}
