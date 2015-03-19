package org.wso2.carbon.analytics.dashboard.admin;

import com.google.gson.Gson;
import org.apache.axiom.om.OMElement;
import org.apache.axiom.om.OMNode;
import org.apache.axis2.AxisFault;
import org.wso2.carbon.analytics.dashboard.admin.data.Column;
import org.wso2.carbon.analytics.dashboard.admin.data.Widget;
import org.wso2.carbon.context.CarbonContext;
import org.wso2.carbon.context.RegistryType;
import org.wso2.carbon.registry.api.Collection;
import org.wso2.carbon.registry.api.Registry;
import org.wso2.carbon.registry.api.RegistryException;
import org.wso2.carbon.registry.api.Resource;

import javax.xml.namespace.QName;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Iterator;

public class RegistryUtils {

	private static final String Q_NAME_PREIX="http://data.admin.dashboard.analytics.carbon.wso2.org/xsd";
	static CarbonContext carbonContext = CarbonContext.getThreadLocalCarbonContext();
	static Registry registry = carbonContext.getRegistry(RegistryType.SYSTEM_GOVERNANCE);

	/**
	 * Writes an object to the given registry url and registry type as a Json String
	 *
	 * @param url     relative url to where the resource will be saved
	 * @param content data to be written to the registry as a json string. Must be a bean object(eg- String objects are not supported)
	 */
	protected static void writeResource(String url, Object content) throws AxisFault {
		try {
			Gson gson = new Gson();

			Resource resource = registry.newResource(); //new resource in the registry
			resource.setContent(gson.toJson(content));
			System.out.println(gson.toJson(content));

			resource.setMediaType("application/json");
			registry.put(url, resource);

		} catch (Exception re) {
			throw new AxisFault(re.getMessage(), re);
		}
	}

	/**
	 * Reads a resource from the registry
	 *
	 * @param url         relative url from where the resource will be read
	 * @param targetClass target object type which the json content will be mapped into
	 * @return Object by mapping into a given class, the json read from the registry
	 */
	protected static Object readResource(String url, Class targetClass) throws AxisFault {
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
	 *
	 * @param url Resource url relative to the registry
	 * @return true if resource exists
	 * @throws AxisFault
	 */
	protected static boolean isResourceExist(String url) throws AxisFault {
		try {
			return registry.resourceExists(url);
		} catch (RegistryException e) {
			throw new AxisFault(e.getMessage(), e);
		}
	}

	/**
	 * Get all the resources in a directory url as a Collection
	 *
	 * @param collectionURL collection url relative to the registry
	 * @return Collection
	 * @throws AxisFault
	 */
	protected static Collection readCollection(String collectionURL) throws AxisFault {

		try {
			return (Collection) registry.get(collectionURL);
		} catch (Exception e) {
			throw new AxisFault(e.getMessage(), e);
		}
	}

	/**
	 *
	 * @param url url of the resource to be deleted from the registry
	 * @throws AxisFault
	 */
	protected static void deleteResource(String url) throws AxisFault {
		try {
			registry.delete(url);
		} catch (Exception e) {
			throw new AxisFault(e.getMessage(), e);
		}
	}

	protected static Widget parseWidget(OMElement OMWidget){

		String id=OMWidget.getFirstChildWithName(new QName(Q_NAME_PREIX,"id")).getText();
		String title=OMWidget.getFirstChildWithName(new QName(Q_NAME_PREIX,"title")).getText();
		String config=OMWidget.getFirstChildWithName(new QName(Q_NAME_PREIX,"config")).getText();

		Widget widget= new Widget();
		widget.setId(id);
		widget.setTitle(title);
		widget.setConfig(config);
		return  widget;
	}

	protected static Column parseColumn(OMElement OMColumn){

		String name=OMColumn.getFirstChildWithName(new QName(Q_NAME_PREIX,"name")).getText();
		String type=OMColumn.getFirstChildWithName(new QName(Q_NAME_PREIX,"type")).getText();

		Column column=new Column();
		column.setName(name);
		column.setType(type);
		return column;
	}
}
