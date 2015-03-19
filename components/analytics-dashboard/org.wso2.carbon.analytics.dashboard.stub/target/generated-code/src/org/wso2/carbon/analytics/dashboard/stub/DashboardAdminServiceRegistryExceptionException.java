
/**
 * DashboardAdminServiceRegistryExceptionException.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis2 version: 1.6.1-wso2v10  Built on : Sep 04, 2013 (02:02:54 UTC)
 */

package org.wso2.carbon.analytics.dashboard.stub;

public class DashboardAdminServiceRegistryExceptionException extends java.lang.Exception{

    private static final long serialVersionUID = 1426668917991L;
    
    private org.wso2.carbon.analytics.dashboard.admin.DashboardAdminServiceRegistryException faultMessage;

    
        public DashboardAdminServiceRegistryExceptionException() {
            super("DashboardAdminServiceRegistryExceptionException");
        }

        public DashboardAdminServiceRegistryExceptionException(java.lang.String s) {
           super(s);
        }

        public DashboardAdminServiceRegistryExceptionException(java.lang.String s, java.lang.Throwable ex) {
          super(s, ex);
        }

        public DashboardAdminServiceRegistryExceptionException(java.lang.Throwable cause) {
            super(cause);
        }
    

    public void setFaultMessage(org.wso2.carbon.analytics.dashboard.admin.DashboardAdminServiceRegistryException msg){
       faultMessage = msg;
    }
    
    public org.wso2.carbon.analytics.dashboard.admin.DashboardAdminServiceRegistryException getFaultMessage(){
       return faultMessage;
    }
}
    