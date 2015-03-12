

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
                    * @param testService0
                
         */

         
                     public java.lang.String testService(

                        java.lang.String inbox1)
                        throws java.rmi.RemoteException
             ;

        
         /**
            * Auto generated method signature for Asynchronous Invocations
            * 
                * @param testService0
            
          */
        public void starttestService(

            java.lang.String inbox1,

            final org.wso2.carbon.analytics.dashboard.stub.DashboardAdminServiceCallbackHandler callback)

            throws java.rmi.RemoteException;

     

        
       //
       }
    