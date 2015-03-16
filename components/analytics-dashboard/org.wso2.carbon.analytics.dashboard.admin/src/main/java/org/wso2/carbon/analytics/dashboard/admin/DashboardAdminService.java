package org.wso2.carbon.analytics.dashboard.admin;

import com.google.gson.Gson;
import org.apache.axis2.AxisFault;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.wso2.carbon.analytics.dashboard.admin.data.*;
import org.wso2.carbon.context.CarbonContext;
import org.wso2.carbon.context.RegistryType;
import org.wso2.carbon.core.AbstractAdmin;
import org.wso2.carbon.registry.api.Collection;
import org.wso2.carbon.registry.api.Registry;
import org.wso2.carbon.registry.api.RegistryException;
import org.wso2.carbon.registry.api.Resource;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class DashboardAdminService extends AbstractAdmin {

    /**
     * Relative Registry locations for dataviews and dashboards
     */
    static final String DATAVIEWS_DIR = "/dataviews/";
    static final String DASHBOARDS_DIR = "/dashboards/";

    /**
     * The logger.
     */
    private Log logger = LogFactory.getLog(DashboardAdminService.class);

    public ArrayList<DataView> getDataViews() throws AxisFault, RegistryException {
        ArrayList<DataView> dataViews=new ArrayList<>();
        Collection dataViewsCollection=getCollection(DATAVIEWS_DIR);
        String[] resourceNames = dataViewsCollection.getChildren();
        for(String resourceName:resourceNames){
            DataView dataview=getDataView(resourceName.replace(DATAVIEWS_DIR,""));
            dataview.setWidgets(null);
            dataViews.add(dataview);
        }
        return dataViews;
    }

    /**
     * returns a dataview object which is read from the registry
     * @param name
     * @return
     * @throws AxisFault
     */
    public DataView getDataView(String name) throws AxisFault { //TODO not name, id
        DataView dataview = (DataView) readFromRegistry(DATAVIEWS_DIR + name, DataView.class);
        return dataview;
    }

    /**
     * appends a dataview object to the registry with the dataview as the resource content
     * @param dataview
     * @throws AxisFault
     */
    public void addDataView(DataView dataview) throws AxisFault {
        if (!isResourceExist(DATAVIEWS_DIR + dataview.getName())) {
            writeToRegistry(DATAVIEWS_DIR + dataview.getName(), dataview);
        } else {
            throw new AxisFault("DataView with name:" + dataview.getName() + " already exists");
        }
    }

    /**
     * updates an existing dataview
     * @param dataview
     * @throws AxisFault if dataview does not exist
     */
    public void updateDataView(DataView dataview) throws AxisFault {
        if (isResourceExist(DATAVIEWS_DIR + dataview.getName())) {
            writeToRegistry(DATAVIEWS_DIR + dataview.getName(), dataview);
        } else {
            throw new AxisFault("DataView with given name does not exist"); //TODO - create new or send error message?
        }
    }

    /**
     * deletes dataview resource with the given name
     * @param dataViewName
     */
    public void deleteDataView(String dataViewName) throws AxisFault {   //TODO update method
        if (isResourceExist(DATAVIEWS_DIR + dataViewName)) {
//            deleteFromRegistry(DATAVIEWS_DIR + dataViewName);
        } else {
            throw new AxisFault("DataView with given name does not exist");
        }
    }

    /**
     * appends a widget to an existing DataView
     * @param dataViewName existing dataview
     * @param widget       widget to be appended
     * @throws AxisFault
     */
    public void addWidget(String dataViewName, Widget widget) throws AxisFault {
        DataView dataView = getDataView(dataViewName);
        dataView.addWidget(widget);
        updateDataView(dataView);
    }

    /**
     * updates a widget of an existing DataView
     * @param dataViewName existing dataview
     * @param widget       widget to be updated
     * @throws AxisFault
     */
    public void updateWidget(String dataViewName, Widget widget) throws AxisFault {
        DataView dataView = getDataView(dataViewName);
        dataView.updateWidget(widget);
        updateDataView(dataView);
    }

    //TODO add widget to dashboard option to be added(dash ID, widget ID , dimensions????? and separate params
    //TODO nice to have feature- when dimensions are not given, find a place for it from the server side

    /**
     * Returns a dataview object with a SINGLE widget
     * @param dataViewName dataview name in which the target widget resides
     * @param widgetID     widget to be included in the dataview object
     * @return
     * @throws AxisFault
     */
    public DataView getWidget(String dataViewName, String widgetID) throws AxisFault {
        DataView dataView = getDataView(dataViewName);
        Widget widget = dataView.getWidget(widgetID);
        dataView.setWidgets(null);
        dataView.addWidget(widget);
        return dataView;
    }

    /**
     * Replaces the widget list of an existing dataview
     * @param dataViewName
     * @param widgets
     * @throws AxisFault
     */
    public void setWidgets(String dataViewName, ArrayList<Widget> widgets) throws AxisFault {
        DataView dataview = getDataView(dataViewName);
        dataview.setWidgets(widgets);
        updateDataView(dataview);
    }

    /**
     * Returns the widget list of given dataview as an array
     * @param dataViewName
     * @return
     * @throws AxisFault
     */
    public ArrayList<Widget> getWidgets(String dataViewName) throws AxisFault {
        return getDataView(dataViewName).getWidgets();
    }

    /**
     * @return All the existing dashboards as an Arraylist
     * @throws AxisFault
     * @throws RegistryException
     */
    public ArrayList<Dashboard> getDashboards() throws AxisFault, RegistryException {
        ArrayList<Dashboard> dashboards=new ArrayList<>();
        Collection dashboardsCollection=getCollection(DASHBOARDS_DIR);
        String[] resourceNames = dashboardsCollection.getChildren();
        for(String resourceName:resourceNames){
            Dashboard dashboard=getDashboard(resourceName.replace(DASHBOARDS_DIR, ""));
            dashboard.setWidgets(null);
            dashboards.add(dashboard);
        }
        return dashboards;
    }

    /**
     * Returns a complete dashboard
     * @param dashboardID
     * @return
     * @throws AxisFault
     */
    public Dashboard getDashboard(String dashboardID) throws AxisFault {
        return (Dashboard) readFromRegistry(dashboardID, Dashboard.class);
    }

    // TODO add getDAtAVIEWs and get Dashboards

    /**
     * Adds a new dashboard to the registry, does not allow to replace existing dashboard
     * @param dashboard
     * @throws AxisFault
     */
    public void addDashboard(Dashboard dashboard) throws AxisFault {
        if (!isResourceExist(DASHBOARDS_DIR + dashboard.getId())) {
            writeToRegistry(DASHBOARDS_DIR + dashboard.getId(), dashboard);
        } else {
            throw new AxisFault("Dashboard with name:" + dashboard.getId() + " already exists");
        }
    }

    /**
     * Updates an existing dashboard
     * @param dashboard
     * @throws AxisFault if dashboard does not exist
     */
    public void updateDashboard(Dashboard dashboard) throws AxisFault {
        if (isResourceExist(DASHBOARDS_DIR + dashboard.getId())) {
            writeToRegistry(DASHBOARDS_DIR + dashboard.getId(), dashboard);
        } else {
            throw new AxisFault("Dashboard with name:" + dashboard.getId() + " does not exist");
        }
    }



    /**
     * Writes an object to the given registry url and registry type as a Json String
     * @param url     relative url to where the resource will be saved
     * @param content data to be written to the registry as a json string. Must be a bean object(eg- String objects are not supported)
     */
    private void writeToRegistry(String url, Object content) throws AxisFault {
        CarbonContext cctx = CarbonContext.getThreadLocalCarbonContext();
        Registry registry = cctx.getRegistry(RegistryType.SYSTEM_GOVERNANCE);

        try {
            Gson gson = new Gson();

            Resource resource = registry.newResource(); //new resource in the registry
            resource.setContent(gson.toJson(content));

            resource.setMediaType("application/json");
            registry.put(url, resource);

        } catch (Exception re) {
            throw new AxisFault(re.getMessage(), re);
        }
    }

    /**
     * Reads a json from the registry, maps into a given class and returns as an object
     * @param url         relative url from where the resource will be read
     * @param targetClass target object type which the json content will be mapped into
     * @return
     */
    private Object readFromRegistry(String url, Class targetClass) throws AxisFault {
        CarbonContext cctx = CarbonContext.getThreadLocalCarbonContext();
        Registry registry = cctx.getRegistry(RegistryType.SYSTEM_GOVERNANCE);

        try {
            Resource resource = registry.get(url);
            Gson gson = new Gson();

            InputStream contentStream = resource.getContentStream();
            InputStreamReader isr = new InputStreamReader(contentStream);

            return gson.fromJson(isr, targetClass);

        } catch (Exception e) {
            throw new AxisFault(e.getMessage(), e);
        }
    }

    /**
     * Checks if a resource with given name(url->location+resource name) exits in the registry
     * @param url
     * @return true if resource exists
     * @throws AxisFault
     */
    private boolean isResourceExist(String url) throws AxisFault {
        CarbonContext cctx = CarbonContext.getThreadLocalCarbonContext();
        Registry registry = cctx.getRegistry(RegistryType.SYSTEM_GOVERNANCE);
        try {
            return registry.resourceExists(url);
        } catch (RegistryException e) {
            throw new AxisFault(e.getMessage(), e);
        }
    }

    public Collection getCollection(String collectionURL) throws RegistryException {
        CarbonContext cctx = CarbonContext.getThreadLocalCarbonContext();
        Registry registry = cctx.getRegistry(RegistryType.SYSTEM_GOVERNANCE);
        return (Collection)registry.get(collectionURL);
    }

}
