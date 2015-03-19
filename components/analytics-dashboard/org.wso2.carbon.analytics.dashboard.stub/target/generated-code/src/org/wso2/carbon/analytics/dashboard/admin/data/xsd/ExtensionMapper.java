
/**
 * ExtensionMapper.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis2 version: 1.6.1-wso2v10  Built on : Apr 16, 2014 (01:16:09 UTC)
 */

        
            package org.wso2.carbon.analytics.dashboard.admin.data.xsd;
        
            /**
            *  ExtensionMapper class
            */
            @SuppressWarnings({"unchecked","unused"})
        
        public  class ExtensionMapper{

          public static java.lang.Object getTypeObject(java.lang.String namespaceURI,
                                                       java.lang.String typeName,
                                                       javax.xml.stream.XMLStreamReader reader) throws java.lang.Exception{

              
                  if (
                  "http://data.admin.dashboard.analytics.carbon.wso2.org/xsd".equals(namespaceURI) &&
                  "WidgetDimensions".equals(typeName)){
                   
                            return  org.wso2.carbon.analytics.dashboard.admin.data.xsd.WidgetDimensions.Factory.parse(reader);
                        

                  }

              
                  if (
                  "http://data.admin.dashboard.analytics.carbon.wso2.org/xsd".equals(namespaceURI) &&
                  "DataView".equals(typeName)){
                   
                            return  org.wso2.carbon.analytics.dashboard.admin.data.xsd.DataView.Factory.parse(reader);
                        

                  }

              
                  if (
                  "http://data.admin.dashboard.analytics.carbon.wso2.org/xsd".equals(namespaceURI) &&
                  "WidgetConfig".equals(typeName)){
                   
                            return  org.wso2.carbon.analytics.dashboard.admin.data.xsd.WidgetConfig.Factory.parse(reader);
                        

                  }

              
                  if (
                  "http://api.registry.carbon.wso2.org/xsd".equals(namespaceURI) &&
                  "RegistryException".equals(typeName)){
                   
                            return  org.wso2.carbon.registry.api.xsd.RegistryException.Factory.parse(reader);
                        

                  }

              
                  if (
                  "http://data.admin.dashboard.analytics.carbon.wso2.org/xsd".equals(namespaceURI) &&
                  "WidgetMetaData".equals(typeName)){
                   
                            return  org.wso2.carbon.analytics.dashboard.admin.data.xsd.WidgetMetaData.Factory.parse(reader);
                        

                  }

              
                  if (
                  "http://data.admin.dashboard.analytics.carbon.wso2.org/xsd".equals(namespaceURI) &&
                  "Dashboard".equals(typeName)){
                   
                            return  org.wso2.carbon.analytics.dashboard.admin.data.xsd.Dashboard.Factory.parse(reader);
                        

                  }

              
                  if (
                  "http://data.admin.dashboard.analytics.carbon.wso2.org/xsd".equals(namespaceURI) &&
                  "Widget".equals(typeName)){
                   
                            return  org.wso2.carbon.analytics.dashboard.admin.data.xsd.Widget.Factory.parse(reader);
                        

                  }

              
             throw new org.apache.axis2.databinding.ADBException("Unsupported type " + namespaceURI + " " + typeName);
          }

        }
    