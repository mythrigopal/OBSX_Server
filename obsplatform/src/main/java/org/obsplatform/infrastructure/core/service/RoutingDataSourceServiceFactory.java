package org.obsplatform.infrastructure.core.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

/**
 * Factory class to get data source service based on the details stored in
 * {@link ThreadLocal} variable for this request
 * 
 * {@link ThreadLocalContextUtil} is used to retrieve the Context
 * 
 */
@Component
public class RoutingDataSourceServiceFactory {

    @Autowired
    private ApplicationContext applicationContext;

    public RoutingDataSource determineDataSourceService() {
        String serviceName = "tomcatJdbcDataSourcePerTenantService";
        if (ThreadLocalContextUtil.CONTEXT_TENANTS.equalsIgnoreCase(ThreadLocalContextUtil.getDataSourceContext())) {
            serviceName = "dataSourceForTenants";
        }
        return applicationContext.getBean(serviceName, RoutingDataSource.class);

    }
}
