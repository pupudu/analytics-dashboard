/**
 * Copyright (c) 2015, WSO2 Inc. (http://www.wso2.org) All Rights Reserved.

 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.wso2.carbon.analytics.dashboard.admin.data;

import org.apache.axis2.AxisFault;

import java.util.ArrayList;
import java.util.List;

public class DataView {
	private String id;
	private String displayName;
	private String type;
	private String dataSource;
	private List<Column> columns = new ArrayList<>();
	private String filter;
	private List<Widget> widgets = new ArrayList<>();

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

	public List getColumns() {
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

	public List getWidgets() {
		return widgets;
	}

	public void setWidgets(List<Widget> widgets) {
		this.widgets = widgets;
	}

	public void addWidget(Widget widget) throws AxisFault {
		for (Widget ExistingWidget : widgets) {
			if (ExistingWidget.getId().equals(widget.getId())) {
				throw new AxisFault("Widget with given ID already exists");
			}
		}
		widgets.add(widget);
	}

	public boolean updateWidget(Widget widget) throws AxisFault {
		try {
			boolean updateStatus = deleteWidget(widget.getId());
			widgets.add(widget);
			return updateStatus;
		}catch(Exception e){
			throw new AxisFault("Failed to update Widget");
		}
	}

	public Widget getWidget(String widgetID) throws AxisFault {
		if(widgets==null){
			widgets=new ArrayList<>();
		}
		for (Widget widget : widgets) {
			if (widget.getId().equals(widgetID)) {
				return widget;
			}
		}
		throw new AxisFault("Widget With given ID does not exist in the dashboard");
	}

	public boolean deleteWidget(String widgetID) throws AxisFault {
		for (Widget existingWidget : widgets) {
			if (existingWidget.getId().equals(widgetID)) {
				widgets.remove(existingWidget);
				return true;
			}
		}
		return false;
	}

}
