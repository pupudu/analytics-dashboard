package org.wso2.carbon.analytics.dashboard.restapi.interceptor;

/*
* Copyright (c) 2015, WSO2 Inc. (http://www.wso2.org) All Rights Reserved.
*
* WSO2 Inc. licenses this file to you under the Apache License,
* Version 2.0 (the "License"); you may not use this file except
* in compliance with the License.
* You may obtain a copy of the License at
*
* http://www.apache.org/licenses/LICENSE-2.0
*
* Unless required by applicable law or agreed to in writing,
* software distributed under the License is distributed on an
* "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
* KIND, either express or implied.  See the License for the
* specific language governing permissions and limitations
* under the License.
*/

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.cxf.jaxrs.interceptor.JAXRSInInterceptor;
import org.wso2.carbon.analytics.dashboard.restapi.Constants;

/**
 * This class will intercept all the request calls that coming to analytics web application.
 * All request call should contain the Authorization headers with valid OAuth access token in-order
 * to pass this interceptor.
 */

public class AnalyticsAuthenticationInterceptor extends JAXRSInInterceptor {

    Log logger = LogFactory.getLog(AnalyticsAuthenticationInterceptor.class);

    private static final String AUTHORIZATION = "Authorization";
    private static final String GENERATE_TOKEN_PATH = "/".concat(Constants.ResourcePath.GENERATE_TOKEN);


    public AnalyticsAuthenticationInterceptor() {
        super();
    }


}
