
/**
 * DashboardAdminServiceCallbackHandler.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis2 version: 1.6.1-wso2v10  Built on : Sep 04, 2013 (02:02:54 UTC)
 */

    package org.wso2.carbon.analytics.dashboard.stub;

    /**
     *  DashboardAdminServiceCallbackHandler Callback class, Users can extend this class and implement
     *  their own receiveResult and receiveError methods.
     */
    public abstract class DashboardAdminServiceCallbackHandler{



    protected Object clientData;

    /**
    * User can pass in any object that needs to be accessed once the NonBlocking
    * Web service call is finished and appropriate method of this CallBack is called.
    * @param clientData Object mechanism by which the user can pass in user data
    * that will be avilable at the time this callback is called.
    */
    public DashboardAdminServiceCallbackHandler(Object clientData){
        this.clientData = clientData;
    }

    /**
    * Please use this constructor if you don't want to set any clientData
    */
    public DashboardAdminServiceCallbackHandler(){
        this.clientData = null;
    }

    /**
     * Get the client data
     */

     public Object getClientData() {
        return clientData;
     }

        
           /**
            * auto generated Axis2 call back method for getDashboards method
            * override this method for handling normal response from getDashboards operation
            */
           public void receiveResultgetDashboards(
                    org.wso2.carbon.analytics.dashboard.admin.data.xsd.Dashboard[] result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from getDashboards operation
           */
            public void receiveErrorgetDashboards(java.lang.Exception e) {
            }
                
               // No methods generated for meps other than in-out
                
               // No methods generated for meps other than in-out
                
               // No methods generated for meps other than in-out
                
               // No methods generated for meps other than in-out
                
               // No methods generated for meps other than in-out
                
           /**
            * auto generated Axis2 call back method for getDashboard method
            * override this method for handling normal response from getDashboard operation
            */
           public void receiveResultgetDashboard(
                    org.wso2.carbon.analytics.dashboard.admin.data.xsd.Dashboard result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from getDashboard operation
           */
            public void receiveErrorgetDashboard(java.lang.Exception e) {
            }
                
           /**
            * auto generated Axis2 call back method for getWidgets method
            * override this method for handling normal response from getWidgets operation
            */
           public void receiveResultgetWidgets(
                    org.wso2.carbon.analytics.dashboard.admin.data.xsd.Widget[] result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from getWidgets operation
           */
            public void receiveErrorgetWidgets(java.lang.Exception e) {
            }
                
               // No methods generated for meps other than in-out
                
               // No methods generated for meps other than in-out
                
           /**
            * auto generated Axis2 call back method for getDataView method
            * override this method for handling normal response from getDataView operation
            */
           public void receiveResultgetDataView(
                    org.wso2.carbon.analytics.dashboard.admin.data.xsd.DataView result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from getDataView operation
           */
            public void receiveErrorgetDataView(java.lang.Exception e) {
            }
                
           /**
            * auto generated Axis2 call back method for getDataViews method
            * override this method for handling normal response from getDataViews operation
            */
           public void receiveResultgetDataViews(
                    org.wso2.carbon.analytics.dashboard.admin.data.xsd.DataView[] result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from getDataViews operation
           */
            public void receiveErrorgetDataViews(java.lang.Exception e) {
            }
                
               // No methods generated for meps other than in-out
                
               // No methods generated for meps other than in-out
                
           /**
            * auto generated Axis2 call back method for getWidget method
            * override this method for handling normal response from getWidget operation
            */
           public void receiveResultgetWidget(
                    org.wso2.carbon.analytics.dashboard.admin.data.xsd.DataView result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from getWidget operation
           */
            public void receiveErrorgetWidget(java.lang.Exception e) {
            }
                
               // No methods generated for meps other than in-out
                


    }
    