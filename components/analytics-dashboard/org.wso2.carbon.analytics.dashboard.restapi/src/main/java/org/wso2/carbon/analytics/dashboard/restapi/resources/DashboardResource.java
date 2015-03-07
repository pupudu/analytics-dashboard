package org.wso2.carbon.analytics.dashboard.restapi.resources;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.wso2.carbon.analytics.dashboard.restapi.Constants;
import org.wso2.carbon.analytics.datasource.core.AnalyticsException;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.ArrayList;
import java.util.List;

@Path(Constants.ResourcePath.ROOT_CONTEXT)
public class DashboardResource {

    private static final Log logger = LogFactory.getLog(DashboardResource.class);

    /**
     * List all the tables.
     * @return the response
     * @throws org.wso2.carbon.analytics.datasource.core.AnalyticsException
     */
    @GET
    @Path(Constants.ResourcePath.TABLES)
    @Produces({ MediaType.APPLICATION_JSON })
    public Response listTables() throws AnalyticsException {
        System.out.println("++++++++++++++++ Inside tables +++++++++++++++++++++++");
        int tenantId = -1234;
        if (logger.isDebugEnabled()) {
            logger.debug("Invoking listTables for tenantId :" + tenantId);
        }
        List<String> tables = new ArrayList<String>();
        tables.add("Sales");
        tables.add("Marketing");

        if (logger.isDebugEnabled()) {
            logger.debug("Table List : " + tables);
        }
        return Response.ok(tables).build();
    }
}
