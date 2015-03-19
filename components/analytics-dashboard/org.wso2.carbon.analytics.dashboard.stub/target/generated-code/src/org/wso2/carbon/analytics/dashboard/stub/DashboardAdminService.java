

/**
 * DashboardAdminService.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis2 version: 1.6.1-wso2v10  Built on : Sep 04, 2013 (02:02:54 UTC)
 */

    package org.wso2.carbon.analytics.dashboard.stub;

    /*
     *  DashboardAdminService java interface
     */

    public interface DashboardAdminService {
          

        /**
          * Auto generated method signature
          * 
                    * @param getDashboards18
                
             * @throws org.wso2.carbon.analytics.dashboard.stub.DashboardAdminServiceRegistryExceptionException : 
         */

         
                     public org.wso2.carbon.analytics.dashboard.admin.data.xsd.Dashboard[] getDashboards(

                        )
                        throws java.rmi.RemoteException
             
          ,org.wso2.carbon.analytics.dashboard.stub.DashboardAdminServiceRegistryExceptionException;

        
         /**
            * Auto generated method signature for Asynchronous Invocations
            * 
                * @param getDashboards18
            
          */
        public void startgetDashboards(

            

            final org.wso2.carbon.analytics.dashboard.stub.DashboardAdminServiceCallbackHandler callback)

            throws java.rmi.RemoteException;

     
       /**
         * Auto generated method signature for Asynchronous Invocations
         * 
         */
        public void  deleteDataView(
         java.lang.String dataViewID22

        ) throws java.rmi.RemoteException
        
        ;

        
       /**
         * Auto generated method signature for Asynchronous Invocations
         * 
         */
        public void  setWidgets(
         java.lang.String dataViewID24,org.wso2.carbon.analytics.dashboard.admin.data.xsd.Widget[] widgets25

        ) throws java.rmi.RemoteException
        
        ;

        
       /**
         * Auto generated method signature for Asynchronous Invocations
         * 
         */
        public void  addDataView(
         org.wso2.carbon.analytics.dashboard.admin.data.xsd.DataView dataView27

        ) throws java.rmi.RemoteException
        
        ;

        
       /**
         * Auto generated method signature for Asynchronous Invocations
         * 
         */
        public void  updateWidgetInDashboard(
         java.lang.String dashboardID29,org.wso2.carbon.analytics.dashboard.admin.data.xsd.WidgetMetaData widget30

        ) throws java.rmi.RemoteException
        
        ;

        
       /**
         * Auto generated method signature for Asynchronous Invocations
         * 
         */
        public void  updateDashboard(
         org.wso2.carbon.analytics.dashboard.admin.data.xsd.Dashboard dashboard32

        ) throws java.rmi.RemoteException
        
        ;

        

        /**
          * Auto generated method signature
          * 
                    * @param getDashboard33
                
         */

         
                     public org.wso2.carbon.analytics.dashboard.admin.data.xsd.Dashboard getDashboard(

                        java.lang.String dashboardID34)
                        throws java.rmi.RemoteException
             ;

        
         /**
            * Auto generated method signature for Asynchronous Invocations
            * 
                * @param getDashboard33
            
          */
        public void startgetDashboard(

            java.lang.String dashboardID34,

            final org.wso2.carbon.analytics.dashboard.stub.DashboardAdminServiceCallbackHandler callback)

            throws java.rmi.RemoteException;

     

        /**
          * Auto generated method signature
          * 
                    * @param getWidgets37
                
         */

         
                     public org.wso2.carbon.analytics.dashboard.admin.data.xsd.Widget[] getWidgets(

                        java.lang.String dataViewID38)
                        throws java.rmi.RemoteException
             ;

        
         /**
            * Auto generated method signature for Asynchronous Invocations
            * 
                * @param getWidgets37
            
          */
        public void startgetWidgets(

            java.lang.String dataViewID38,

            final org.wso2.carbon.analytics.dashboard.stub.DashboardAdminServiceCallbackHandler callback)

            throws java.rmi.RemoteException;

     
       /**
         * Auto generated method signature for Asynchronous Invocations
         * 
         */
        public void  updateWidget(
         java.lang.String dataViewID42,org.wso2.carbon.analytics.dashboard.admin.data.xsd.Widget widget43

        ) throws java.rmi.RemoteException
        
        ;

        
       /**
         * Auto generated method signature for Asynchronous Invocations
         * 
         */
        public void  updateDataView(
         org.wso2.carbon.analytics.dashboard.admin.data.xsd.DataView dataView45

        ) throws java.rmi.RemoteException
        
        ;

        

        /**
          * Auto generated method signature
          * 
                    * @param getDataView46
                
         */

         
                     public org.wso2.carbon.analytics.dashboard.admin.data.xsd.DataView getDataView(

                        java.lang.String dataViewID47)
                        throws java.rmi.RemoteException
             ;

        
         /**
            * Auto generated method signature for Asynchronous Invocations
            * 
                * @param getDataView46
            
          */
        public void startgetDataView(

            java.lang.String dataViewID47,

            final org.wso2.carbon.analytics.dashboard.stub.DashboardAdminServiceCallbackHandler callback)

            throws java.rmi.RemoteException;

     

        /**
          * Auto generated method signature
          * 
                    * @param getDataViews50
                
         */

         
                     public org.wso2.carbon.analytics.dashboard.admin.data.xsd.DataView[] getDataViews(

                        )
                        throws java.rmi.RemoteException
             ;

        
         /**
            * Auto generated method signature for Asynchronous Invocations
            * 
                * @param getDataViews50
            
          */
        public void startgetDataViews(

            

            final org.wso2.carbon.analytics.dashboard.stub.DashboardAdminServiceCallbackHandler callback)

            throws java.rmi.RemoteException;

     
       /**
         * Auto generated method signature for Asynchronous Invocations
         * 
         */
        public void  addWidget(
         java.lang.String dataViewID54,org.wso2.carbon.analytics.dashboard.admin.data.xsd.Widget widget55

        ) throws java.rmi.RemoteException
        
        ;

        
       /**
         * Auto generated method signature for Asynchronous Invocations
         * 
         */
        public void  addDashboard(
         org.wso2.carbon.analytics.dashboard.admin.data.xsd.Dashboard dashboard57

        ) throws java.rmi.RemoteException
        
        ;

        

        /**
          * Auto generated method signature
          * 
                    * @param getWidget58
                
         */

         
                     public org.wso2.carbon.analytics.dashboard.admin.data.xsd.DataView getWidget(

                        java.lang.String dataViewID59,java.lang.String widgetID60)
                        throws java.rmi.RemoteException
             ;

        
         /**
            * Auto generated method signature for Asynchronous Invocations
            * 
                * @param getWidget58
            
          */
        public void startgetWidget(

            java.lang.String dataViewID59,java.lang.String widgetID60,

            final org.wso2.carbon.analytics.dashboard.stub.DashboardAdminServiceCallbackHandler callback)

            throws java.rmi.RemoteException;

     
       /**
         * Auto generated method signature for Asynchronous Invocations
         * 
         */
        public void  addWidgetToDashboard(
         java.lang.String dashboardID64,org.wso2.carbon.analytics.dashboard.admin.data.xsd.WidgetMetaData widget65

        ) throws java.rmi.RemoteException
        
        ;

        

        
       //
       }
    