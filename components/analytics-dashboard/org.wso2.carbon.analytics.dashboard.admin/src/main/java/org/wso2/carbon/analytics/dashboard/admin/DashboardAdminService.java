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

import org.apache.axis2.AxisFault;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.wso2.carbon.analytics.dashboard.admin.data.Dashboard;
import org.wso2.carbon.analytics.dashboard.admin.data.DataView;
import org.wso2.carbon.analytics.dashboard.admin.data.Widget;
import org.wso2.carbon.analytics.dashboard.admin.data.WidgetMetaData;
import org.wso2.carbon.core.AbstractAdmin;
import org.wso2.carbon.registry.api.Collection;
import org.wso2.carbon.registry.api.RegistryException;

import java.util.ArrayList;
import java.util.List;

public class DashboardAdminService extends AbstractAdmin {

	/**
	 * Relative Registry locations for dataViews and dashboards.
	 */
	private static final String DATAVIEWS_DIR = "/dataViews/";
	private static final String DASHBOARDS_DIR = "/dashboards/";

	/**
	 * @return All the dataView objects saved in the registry.
	 * @throws AxisFault
	 */
	public DataView[] getDataViewsInfo() throws AxisFault { //TODO Use Arrays, Log errors, Exceptions
		ArrayList<DataView> dataViews = new ArrayList<>();
		Collection dataViewsCollection = RegistryUtils.readCollection(DATAVIEWS_DIR);
		String[] resourceNames;
		try {
			resourceNames = dataViewsCollection.getChildren();
		} catch (RegistryException e) {
			throw new AxisFault(e.getMessage(), e);
		}
		for (String resourceName : resourceNames) {
			DataView dataView = getDataView(resourceName.replace(DATAVIEWS_DIR, ""));
			dataView.setWidgets(new Widget[0]);
			dataViews.add(dataView);
		}
		DataView[] dataViewArray=new DataView[dataViews.size()];
		return dataViews.toArray(dataViewArray);
	}

	/**
	 * @param dataViewID Id of the target dataView to be read.
	 * @return DataView object which is read from the registry.
	 * @throws AxisFault
	 */
	public DataView getDataView(String dataViewID) throws AxisFault {
		return (DataView) RegistryUtils.readResource(DATAVIEWS_DIR + dataViewID, DataView.class);
	}

	/**
	 * Appends a dataView object to the registry with the dataView as the resource content.
	 *
	 * @param dataView Object to be appended to the registry.
	 * @throws AxisFault
	 */
	public void addDataView(DataView dataView) throws AxisFault {

			if (!RegistryUtils.isResourceExist(DATAVIEWS_DIR + dataView.getId())) {
				RegistryUtils.writeResource(DATAVIEWS_DIR + dataView.getId(), dataView);
			} else {
				throw new AxisFault("DataView with ID:" + dataView.getId() + " already exists");
			}

	}

	/**
	 * Updates an existing dataView.
	 *
	 * @param dataView Object to be updated.
	 * @throws AxisFault If a matching dataView does not exist.
	 */
	public void updateDataView(DataView dataView) throws AxisFault {

			if (RegistryUtils.isResourceExist(DATAVIEWS_DIR + dataView.getId())) {
				RegistryUtils.writeResource(DATAVIEWS_DIR + dataView.getId(), dataView);
			} else {
				throw new AxisFault("DataView with given ID does not exist");
			}

	}

	/**
	 * Deletes the dataView resource with the given name.
	 *
	 * @param dataViewID Id of the dataView to be deleted.
	 */
	public void deleteDataView(String dataViewID) throws AxisFault {
		if (RegistryUtils.isResourceExist(DATAVIEWS_DIR + dataViewID)) {
			RegistryUtils.deleteResource(DATAVIEWS_DIR + dataViewID);
		} else {
			throw new AxisFault("DataView with given name does not exist");
		}
	}

	/**
	 * Appends a widget to an existing DataView.
	 *
	 * @param dataViewID Existing dataView.
	 * @param widget     Widget to be appended.
	 * @throws AxisFault
	 */
	public void addWidget(String dataViewID, Widget widget) throws AxisFault {//TODO-try catch to throw fault
		DataView dataView = getDataView(dataViewID);
		dataView.addWidget(widget);
		updateDataView(dataView);
	}

	/**
	 * Updates a widget of an existing DataView.
	 *
	 * @param dataViewID Existing dataView object.
	 * @param widget     Widget to be updated.
	 * @throws AxisFault
	 */
	public void updateWidget(String dataViewID, Widget widget) throws AxisFault {
		DataView dataView = getDataView(dataViewID);
		dataView.updateWidget(widget);
		updateDataView(dataView);
	}

	//TODO nice to have feature- when dimensions are not given, find a place for it from the server side

	/**
	 * Returns a dataView object with a SINGLE widget.
	 *
	 * @param dataViewID DataView name in which the target widget resides.
	 * @param widgetID   Widget to be included in the dataView object.
	 * @return DataView object with a single widget in the widget array-list.
	 * @throws AxisFault
	 */
	public DataView getWidget(String dataViewID, String widgetID) //TODO- return the widget itself
			throws AxisFault {
		DataView dataView = getDataView(dataViewID);
		Widget widget = dataView.getWidget(widgetID);
		dataView.setWidgets(new Widget[0]);
		dataView.addWidget(widget);
		return dataView;
	}

	/**
	 * @param dataViewID Target dataView.
	 * @return Widget list of given dataView as an array.
	 * @throws AxisFault
	 */
	public Widget[] getWidgets(String dataViewID) throws AxisFault {
		return getDataView(dataViewID).getWidgets();
	}

	/**
	 * @return All the existing dashboards as an Array-list.
	 * @throws AxisFault
	 */
	public Dashboard[] getDashboards() throws AxisFault {
		try {
			List<Dashboard> dashboards = new ArrayList<>();
			Collection dashboardsCollection = RegistryUtils.readCollection(DASHBOARDS_DIR);
			String[] resourceNames = dashboardsCollection.getChildren();
			for (String resourceName : resourceNames) {
				Dashboard dashboard = getDashboard(resourceName.replace(DASHBOARDS_DIR, ""));
				dashboards.add(dashboard);
			}
			Dashboard[] dashboardArray=new Dashboard[dashboards.size()];
			return dashboards.toArray(dashboardArray);
		}catch(Exception e){
			throw new AxisFault(e.getMessage(),e);
		}
	}

	/**
	 * @param dashboardID Target dashboard ID.
	 * @return Dashboard with widget-meta-Data.
	 * @throws AxisFault
	 */
	public Dashboard getDashboard(String dashboardID) throws AxisFault {
		return (Dashboard) RegistryUtils
				.readResource(DASHBOARDS_DIR + dashboardID, Dashboard.class);
	}

	/**
	 * Adds a new dashboard to the registry, does not allow to replace existing dashboard.
	 *
	 * @param dashboard Object to be appended to the registry.
	 * @throws AxisFault
	 */
	public void addDashboard(Dashboard dashboard) throws AxisFault {
		if (!RegistryUtils.isResourceExist(DASHBOARDS_DIR + dashboard.getId())) {
			RegistryUtils.writeResource(DASHBOARDS_DIR + dashboard.getId(), dashboard);
		} else {
			throw new AxisFault("Dashboard with name:" + dashboard.getId() + " already exists");
		}
	}

	/**
	 * Updates an existing dashboard.
	 *
	 * @param dashboard Object to be updated.
	 * @throws AxisFault If a matching dashboard does not exist.
	 */
	public void updateDashboard(Dashboard dashboard) throws AxisFault {

			if (RegistryUtils.isResourceExist(DASHBOARDS_DIR + dashboard.getId())) {
				RegistryUtils.writeResource(DASHBOARDS_DIR + dashboard.getId(), dashboard);
			} else {
				throw new AxisFault("Dashboard with name:" + dashboard.getId() + " does not exist");
			}

	}

	/**
	 * @param dashboardID Id of the dashboard to which the widget-meta-data will be appended.
	 * @param widget      Metadata to be appended.
	 * @throws AxisFault
	 */
	public void addWidgetToDashboard(String dashboardID, WidgetMetaData widget) throws AxisFault {
		Dashboard dashboard = getDashboard(dashboardID);
		dashboard.addWidget(widget);
		updateDashboard(dashboard);
	}

	/**
	 * @param dashboardID Id of the dashboard to which the widget-meta-data will be updated.
	 * @param widget      Metadata to be updated.
	 * @throws AxisFault
	 */
	public void updateWidgetInDashboard(String dashboardID, WidgetMetaData widget)
			throws AxisFault {
		Dashboard dashboard = getDashboard(dashboardID);
		dashboard.updateWidget(widget);
		updateDashboard(dashboard);
	}
}
