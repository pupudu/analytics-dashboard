package org.wso2.carbon.analytics.dashboard.admin;

import com.google.gson.Gson;
import org.apache.axis2.AxisFault;
import org.codehaus.jackson.JsonParser;
import org.codehaus.jackson.map.ObjectMapper;
import org.wso2.carbon.analytics.dashboard.admin.data.*;
import org.wso2.carbon.core.AbstractAdmin;
import org.wso2.carbon.context.CarbonContext;
import org.wso2.carbon.context.RegistryType;
import org.wso2.carbon.registry.api.Registry;
import org.wso2.carbon.registry.api.RegistryException;
import org.wso2.carbon.registry.api.Resource;
import org.codehaus.jackson.map.MappingJsonFactory;
import org.codehaus.jackson.JsonGenerator;
import org.apache.commons.io.output.StringBuilderWriter;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import javax.xml.crypto.Data;
import java.io.BufferedReader;
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

    /** returns a dataview object which is read from the registry*/
    public DataView getDataView(String name) throws AxisFault {
        DataView dataview=(DataView)readFromRegistry(DATAVIEWS_DIR + name, DataView.class);
        return dataview;
    }

    /**Saves a Registry resource with given dataview object as the resource content*/
    public void setDataview(DataView dataview) throws AxisFault {
        writeToRegistry(DATAVIEWS_DIR+dataview.getName(),dataview);
    }

    public void addWidget(String dataViewName, Widget widget) throws AxisFault {
        DataView dataView=getDataView(dataViewName);
        dataView.addWidget(widget);
        setDataview(dataView);
    }

    public DataView getWidget(String dataViewName, String widgetID) throws AxisFault {
        DataView dataView=getDataView(dataViewName);
        Widget widget = dataView.getWidget(widgetID);
        dataView.setWidgets(null);
        dataView.addWidget(widget);
        return dataView;
    }

    public void setWidgets(String dataViewName,ArrayList<Widget> widgets) throws AxisFault {
        DataView dataview=getDataView(dataViewName);
        dataview.setWidgets(widgets);
        setDataview(dataview);
    }

    public ArrayList<Widget> getWidgets(String dataViewName) throws AxisFault {
        return getDataView(dataViewName).getWidgets();
    }



    /**Writes an object to the given registry url and registry type as a Json String
     * @param url relative url to where the resource will be saved
     * @param content data to be written to the registry as a json string. Must be a bean object(eg- String objects are not supported)
     */
    public void writeToRegistry( String url, Object content) throws AxisFault {
        CarbonContext cctx = CarbonContext.getThreadLocalCarbonContext();
        Registry registry = cctx.getRegistry(RegistryType.SYSTEM_GOVERNANCE);

        try {
            Gson gson=new Gson();

            Resource resource = registry.newResource(); //new resource in the registry
            resource.setContent(gson.toJson(content));

            resource.setMediaType("application/json");
            registry.put(url, resource);

        } catch (Exception re) {
            throw new AxisFault(re.getMessage(),re);
        }
    }

    /**
     * Reads a json from the registry, maps into a given class and returns as an object
     * @param url relative url from where the resource will be read
     * @param targetClass target object type which the json content will be mapped into
     * @return
     */
    public Object readFromRegistry( String url ,Class targetClass) throws AxisFault {
        CarbonContext cctx = CarbonContext.getThreadLocalCarbonContext();
        Registry registry = cctx.getRegistry(RegistryType.SYSTEM_GOVERNANCE);

        try {
            Resource resource = registry.get(url);
            Gson gson=new Gson();

            InputStream contentStream = resource.getContentStream();
            InputStreamReader isr=new InputStreamReader(contentStream);

            System.out.println(isr.toString());
            return gson.fromJson( isr, targetClass);
        } catch (Exception e) {
            throw new AxisFault(e.getMessage(),e);
        }
    }

}
