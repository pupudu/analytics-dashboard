package org.wso2.carbon.analytics.dashboard.admin;

import org.codehaus.jackson.JsonParser;
import org.wso2.carbon.analytics.dashboard.admin.data.DataView;
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

import java.io.InputStream;

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
    public DataView getDataView(String name) {
        DataView dataview=(DataView)readFromRegistry(DATAVIEWS_DIR + "name", DataView.class);
        return dataview;
    }

    /**Saves a Registry resource with given dataview object as the resource content*/
    public void setDataview(DataView dataview){
        writeToRegistry(DATAVIEWS_DIR+dataview.getName(),dataview);
    }

    /**Writes an object to the given registry url and registry type as a Json String*/
    public void writeToRegistry(RegistryType registryType, String url, Object content) {
        CarbonContext cctx = CarbonContext.getThreadLocalCarbonContext();
        Registry registry = cctx.getRegistry(registryType);

        try {
            Resource resource = registry.newResource();
            StringBuilderWriter writer = new StringBuilderWriter();
            MappingJsonFactory factory = new MappingJsonFactory();
            JsonGenerator generator = factory.createJsonGenerator(writer);
            generator.writeObject(content);
            resource.setContent(writer.toString());
            resource.setMediaType("application/json");
            registry.put(url, resource);
            logger.debug("Object saved on " + registryType.toString() + " at " + url);

        } catch (Exception re) {
            System.err.println("Exception");
            re.printStackTrace();
        }
    }

    /**default registry type is System Governance*/
    public void writeToRegistry(String url, Object Content) {
        writeToRegistry(RegistryType.SYSTEM_GOVERNANCE, url, Content);
    }

    /**
     * Reads a json from the registry, maps into a given class and returns as an object
     * @param registryType System Governance, User Govenrnanace, System Config, Local, etc
     * @param url relative url from where the resource will be read
     * @param targetClass target object type which the json content will be mapped into
     * @return
     */
    public Object readFromRegistry(RegistryType registryType, String url, Class targetClass) {
        Object content = null;
        CarbonContext cctx = CarbonContext.getThreadLocalCarbonContext();
        Registry registry = cctx.getRegistry(registryType);

        try {
            Resource resource = registry.get(url);
            MappingJsonFactory factory = new MappingJsonFactory();
            JsonParser parser = factory.createJsonParser((InputStream) resource.getContentStream());
            Object output = parser.readValueAs(targetClass.getClass());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return content;
    }

    /**default registry type is System Governance*/
    public Object readFromRegistry(String url,Class targetClass) {
        return readFromRegistry(RegistryType.SYSTEM_GOVERNANCE, url,targetClass);
    }
}
