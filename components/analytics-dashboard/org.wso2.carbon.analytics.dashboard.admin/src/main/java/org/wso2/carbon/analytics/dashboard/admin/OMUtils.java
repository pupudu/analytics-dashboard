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
package org.wso2.carbon.analytics.dashboard.admin;

import org.apache.axiom.om.OMElement;
import org.apache.axis2.AxisFault;
import org.wso2.carbon.analytics.dashboard.admin.data.*;
import javax.xml.namespace.QName;
import java.util.ArrayList;
import java.util.List;

/** Has methods to parse OMElement Objects to bean Classes*/
public class OMUtils {

	/**
	 * Namespace URI for Qname
	 */
	private static final String URI = "http://data.admin.dashboard.analytics.carbon.wso2.org/xsd";

	/**
	 * Process OMElements and parse to correct classes if the DataView contains OMElements
	 * @return DataView with parsed widgets and columns
	 */
	protected static DataView processDataView(DataView dataView) {
		/** OMElement Objects to be parsed*/
		List<Widget> OMWidgets = dataView.getWidgets();
		List<Column> OMColumns = dataView.getColumns();

		/** Array-lists to store Parsed Objects */
		ArrayList<Widget> parsedWidgets = new ArrayList<>();
		ArrayList<Column> parsedColumns = new ArrayList<>();

		/**Parse Widgets*/
		for (int index = 0; index < OMWidgets.size(); index++) {
			parsedWidgets.add(OMUtils.parseWidget((OMElement) OMWidgets.get(index)));
		}
		/**Parse Columns*/
		for (int index = 0; index < OMColumns.size(); index++) {
			parsedColumns.add(OMUtils.parseColumn((OMElement) OMColumns.get(index)));
		}

		/**Replace OMElement Objects with parsed objects*/
		dataView.setWidgets(parsedWidgets);
		dataView.setColumns(parsedColumns);

		return dataView;
	}

	/**
	 * Process OMElements and parse to correct classes if the Dashboard contains OMElements
	 * @return Dashboard with parsed widgetMetaData and Roles
	 */
	protected static Dashboard processDashboard(Dashboard dashboard) throws AxisFault {
		/** OMElement Objects to be parsed*/
		ArrayList<WidgetMetaData> OMWidgets = dashboard.getWidgets();
		ArrayList<Role> OMRoles = dashboard.getRoles();

		/** Array-lists to store Parsed Objects */
		ArrayList<WidgetMetaData> parsedWidgets = new ArrayList<>();
		ArrayList<Role> parsedRoles = new ArrayList<>();

		/**Parse WidgetMetaData Objects*/
		for (int index = 0; index < OMWidgets.size(); index++) {
			parsedWidgets.add(OMUtils.parseWidgetMeta((OMElement) OMWidgets.get(index)));
		}
		/**Parse Role Objects*/
		for (int index = 0; index < OMRoles.size(); index++) {
			parsedRoles.add(OMUtils.parseRoles((OMElement) OMRoles.get(index)));
		}

		/**Replace OMElement Objects with parsed objects*/
		dashboard.setWidgets(parsedWidgets);
		dashboard.setRoles(parsedRoles);
		return dashboard;
	}

	/**
	 * Parses an OMElement to a Role Object
	 */
	private static Role parseRoles(OMElement OMRoles){
		/**Assigning the vales of OMElement objects to new variables*/
		String roleStr = OMRoles.getFirstChildWithName(new QName(URI, "role")).getText();

		/**Creating a new Role object with the extracted attributes */
		Role role =new Role();
		role.setRole(roleStr);
		return role;
	}

	/**
	 * Parses an OMElement to a Widget Object
	 */
	private static Widget parseWidget(OMElement OMWidget) {
		/**Assigning the vales of OMElement objects to new variables*/
		String id = OMWidget.getFirstChildWithName(new QName(URI, "id")).getText();
		String title = OMWidget.getFirstChildWithName(new QName(URI, "title")).getText();
		String config = OMWidget.getFirstChildWithName(new QName(URI, "config")).getText();

		/**Creating a new Widget object with the extracted attributes */
		Widget widget = new Widget();
		widget.setId(id);
		widget.setTitle(title);
		widget.setConfig(config);
		return widget;
	}

	/**
	 * Parses an OMElement to a Column Object
	 */
	private static Column parseColumn(OMElement OMColumn) {
		/**Assigning the vales of OMElement objects to new variables*/
		String name = OMColumn.getFirstChildWithName(new QName(URI, "name")).getText();
		String type = OMColumn.getFirstChildWithName(new QName(URI, "type")).getText();

		/**Creating a new Column object with the extracted attributes */
		Column column = new Column();
		column.setName(name);
		column.setType(type);
		return column;
	}

	/**
	 * Parses an OMElement to a WidgetMetaData Object
	 */
	private static WidgetMetaData parseWidgetMeta(OMElement OMWidget) {

		/**Assigning the vales of OMElement objects to new variables*/
		String id = OMWidget.getFirstChildWithName(new QName(URI, "id")).getText();
		OMElement OMDimensions = OMWidget.getFirstChildWithName(new QName(URI, "dimensions"));

		WidgetDimensions widgetDimensions = parseDimensions(OMDimensions);

		/**Creating a new WidgetMetaData object with the extracted attributes */
		WidgetMetaData widgetMetaData = new WidgetMetaData();
		widgetMetaData.setId(id);
		widgetMetaData.setDimensions(widgetDimensions);

		return widgetMetaData;
	}

	/**
	 * Parses an OMElement to a WidgetDimensions Object
	 */
	private static WidgetDimensions parseDimensions(OMElement OMDimension) {

		/**Assigning the vales of OMElement objects to new variables*/
		String column = OMDimension.getFirstChildWithName(new QName(URI, "column")).getText();
		String row = OMDimension.getFirstChildWithName(new QName(URI, "row")).getText();
		String height = OMDimension.getFirstChildWithName(new QName(URI, "height")).getText();
		String width = OMDimension.getFirstChildWithName(new QName(URI, "width")).getText();

		/**Creating a new WidgetDimensions object with the extracted attributes */
		WidgetDimensions widgetDimensions=new WidgetDimensions();
		widgetDimensions.setHeight(Integer.parseInt(height));
		widgetDimensions.setWidth(Integer.parseInt(width));
		widgetDimensions.setColumn(Integer.parseInt(column));
		widgetDimensions.setRow(Integer.parseInt(row));

		return widgetDimensions;
	}
}
