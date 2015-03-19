package org.wso2.carbon.analytics.dashboard.admin;

import org.apache.axiom.om.OMElement;
import org.apache.axiom.om.util.AXIOMUtil;
import org.apache.axis2.AxisFault;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.wso2.carbon.analytics.dashboard.admin.data.*;
import org.wso2.carbon.core.AbstractAdmin;
import org.wso2.carbon.registry.api.Collection;
import org.wso2.carbon.registry.api.RegistryException;

import javax.xml.namespace.QName;
import javax.xml.stream.XMLStreamException;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.util.ArrayList;
import java.util.Iterator;

public class DashboardAdminService extends AbstractAdmin {

	/**
	 * Relative Registry locations for dataViews and dashboards
	 */
	static final String DATAVIEWS_DIR = "/dataViews/";
	static final String DASHBOARDS_DIR = "/dashboards/";

	/**
	 * The logger.
	 */
	private Log logger = LogFactory.getLog(DashboardAdminService.class);

	/**
	 * @return all the dataView objects saved in the registry
	 * @throws AxisFault
	 */
	public ArrayList<DataView> getDataViews() throws AxisFault {    //TODO test1 passed
		ArrayList<DataView> dataViews = new ArrayList<>();
		Collection dataViewsCollection = RegistryUtils.readCollection(DATAVIEWS_DIR);
		String[] resourceNames;
		try {
			resourceNames = dataViewsCollection.getChildren();
		}catch (RegistryException e){
			throw new AxisFault(e.getMessage(),e);
		}
		for (String resourceName : resourceNames) {
			DataView dataView = getDataView(resourceName.replace(DATAVIEWS_DIR, ""));
			dataView.setWidgets(new ArrayList<Widget>());
			dataViews.add(dataView);
		}
		return dataViews;
	}

	/**
	 * returns a dataView object which is read from the registry
	 *
	 * @param dataViewID id of the target dataView to be read
	 * @return DataView Object
	 * @throws AxisFault
	 */
	public DataView getDataView(String dataViewID) throws AxisFault {
		return (DataView) RegistryUtils.readResource(DATAVIEWS_DIR + dataViewID, DataView.class);
	}

	/**
	 * appends a dataView object to the registry with the dataView as the resource content
	 *
	 * @param dataView Object to be appended to the registry
	 * @throws AxisFault
	 */
	public void addDataView(DataView dataView) throws AxisFault {   //TODO test this method

		ArrayList<Widget> OMWidgets=dataView.getWidgets();
		ArrayList<Column> OMColumns=dataView.getColumns();

		ArrayList<Widget> parsedWidgets=new ArrayList<>();
		ArrayList<Column> parsedColumns=new ArrayList<>();

		for(int index=0;index<OMWidgets.size();index++){
			parsedWidgets.add(RegistryUtils.parseWidget((OMElement)OMWidgets.get(index)));
		}

		for(int index=0;index<OMColumns.size();index++){
			parsedColumns.add(RegistryUtils.parseColumn((OMElement)OMColumns.get(index)));
		}
		dataView.setWidgets(parsedWidgets);
		dataView.setColumns(parsedColumns);

		if (!RegistryUtils.isResourceExist(DATAVIEWS_DIR + dataView.getId())) {
			RegistryUtils.writeResource(DATAVIEWS_DIR + dataView.getId(), dataView);
		} else {
			throw new AxisFault("DataView with ID:" + dataView.getId() + " already exists");
		}
	}

	/**
	 * updates an existing dataView
	 *
	 * @param dataView object to be updated
	 * @throws AxisFault if dataView does not exist
	 */
	public void updateDataView(DataView dataView) throws AxisFault {    //TODO test this method
		if (RegistryUtils.isResourceExist(DATAVIEWS_DIR + dataView.getId())) {
			RegistryUtils.writeResource(DATAVIEWS_DIR + dataView.getId(), dataView);
		} else {
			throw new AxisFault("DataView with given ID does not exist");
		}
	}

	/**
	 * deletes dataView resource with the given name
	 *
	 * @param dataViewID id of the dataView to be deleted
	 */
	public void deleteDataView(String dataViewID) throws AxisFault {    //TODO test 1 passed
		if (RegistryUtils.isResourceExist(DATAVIEWS_DIR + dataViewID)) {
			RegistryUtils.deleteResource(DATAVIEWS_DIR + dataViewID);
		} else {
			throw new AxisFault("DataView with given name does not exist");
		}
	}

	/**
	 * appends a widget to an existing DataView
	 *
	 * @param dataViewID existing dataView
	 * @param widget     widget to be appended
	 * @throws AxisFault
	 */
	public void addWidget(String dataViewID, Widget widget) throws AxisFault {
		DataView dataView = getDataView(dataViewID);
		dataView.addWidget(widget);
		updateDataView(dataView);
	}

	/**
	 * updates a widget of an existing DataView
	 *
	 * @param dataViewID existing dataView
	 * @param widget     widget to be updated
	 * @throws AxisFault
	 */
	public void updateWidget(String dataViewID, Widget widget) throws AxisFault {
		DataView dataView = getDataView(dataViewID);
		dataView.updateWidget(widget);
		updateDataView(dataView);
	}

	//TODO nice to have feature- when dimensions are not given, find a place for it from the server side

	/**
	 * Returns a dataView object with a SINGLE widget
	 *
	 * @param dataViewID dataView name in which the target widget resides
	 * @param widgetID   widget to be included in the dataView object
	 * @return a DataView object with a single widget in the widget array-list
	 * @throws AxisFault
	 */
	public DataView getWidget(String dataViewID, String widgetID) throws AxisFault {    //TODO fix exception
		DataView dataView = getDataView(dataViewID);
		Widget widget = dataView.getWidget(widgetID);
		dataView.setWidgets(new ArrayList<Widget>());
		dataView.addWidget(widget);
		return dataView;
	}

	/**
	 * Replaces the widget list of an existing dataView
	 *
	 * @param dataViewID target dataView in which the widgets will be replaced
	 * @param widgets    list of widgets which will replace the existing widgets
	 * @throws AxisFault
	 */
	public void setWidgets(String dataViewID, ArrayList<Widget> widgets) throws AxisFault {
		DataView dataView = getDataView(dataViewID);
		dataView.setWidgets(widgets);
		updateDataView(dataView);
	}

	/**
	 * @param dataViewID target dataView
	 * @return the widget list of given dataView as an array
	 * @throws AxisFault
	 */
	public ArrayList<Widget> getWidgets(String dataViewID) throws AxisFault {
		return getDataView(dataViewID).getWidgets();
	}

	/**
	 * @return All the existing dashboards as an Array-list
	 * @throws AxisFault
	 * @throws RegistryException
	 */
	public ArrayList<Dashboard> getDashboards() throws AxisFault, RegistryException {
		ArrayList<Dashboard> dashboards = new ArrayList<>();
		Collection dashboardsCollection = RegistryUtils.readCollection(DASHBOARDS_DIR);
		String[] resourceNames = dashboardsCollection.getChildren();
		for (String resourceName : resourceNames) {
			Dashboard dashboard = getDashboard(resourceName.replace(DASHBOARDS_DIR, ""));
			dashboard.setWidgets(new ArrayList<WidgetMetaData>());
			dashboards.add(dashboard);
		}
		return dashboards;
	}

	/**
	 * @param dashboardID target dashboard ID
	 * @return a dashboard with widget-meta-Data
	 * @throws AxisFault
	 */
	public Dashboard getDashboard(String dashboardID) throws AxisFault {
		return (Dashboard) RegistryUtils.readResource(dashboardID, Dashboard.class);
	}

	/**
	 * Adds a new dashboard to the registry, does not allow to replace existing dashboard
	 *
	 * @param dashboard object to be appended to the registry
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
	 * Updates an existing dashboard
	 *
	 * @param dashboard object to be updated
	 * @throws AxisFault if dashboard does not exist
	 */
	public void updateDashboard(Dashboard dashboard) throws AxisFault {
		if (RegistryUtils.isResourceExist(DASHBOARDS_DIR + dashboard.getId())) {
			RegistryUtils.writeResource(DASHBOARDS_DIR + dashboard.getId(), dashboard);
		} else {
			throw new AxisFault("Dashboard with name:" + dashboard.getId() + " does not exist");
		}
	}

	/**
	 * @param dashboardID Id of the dashboard to which the widget-meta-data will be appended
	 * @param widget metadata to be appended
	 * @throws AxisFault
	 */
	public void addWidgetToDashboard(String dashboardID, WidgetMetaData widget) throws AxisFault {
		Dashboard dashboard = getDashboard(dashboardID);
		dashboard.addWidget(widget);
		updateDashboard(dashboard);
	}

	/**
	 * @param dashboardID Id of the dashboard to which the widget-meta-data will be updated
	 * @param widget metadata to be updated
	 * @throws AxisFault
	 */
	public void updateWidgetInDashboard(String dashboardID, WidgetMetaData widget)
			throws AxisFault {
		Dashboard dashboard = getDashboard(dashboardID);
		dashboard.updateWidget(widget);
		updateDashboard(dashboard);
	}
}
