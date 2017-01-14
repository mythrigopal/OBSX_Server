/**
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this file,
 * You can obtain one at http://mozilla.org/MPL/2.0/.
 */
package org.obsplatform.infrastructure.core.service;

import java.sql.Connection;
import java.sql.SQLException;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.datasource.AbstractDataSource;
import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;
import org.springframework.stereotype.Service;

/**
 * Based on springs {@link AbstractRoutingDataSource} idea, this is a
 * {@link DataSource} that routes or delegates to another data source depending
 * on the tenant details passed in the request.
 * 
 * The tenant details are process earlier and stored in a {@link ThreadLocal}.
 * 
 * The {@link DataSourcePerTenantService} is responsible for returning the
 * appropriate {@link DataSource} for the tenant of this request.
 */
@Service(value = "tenantAwareDataSource")
public class TenantAwareRoutingDataSource extends AbstractDataSource {

    @Autowired
    private DataSourcePerTenantService dataSourcePerTenantService;

    @Override
    public Connection getConnection() throws SQLException {
        return determineTargetDataSource().getConnection();
    }

    private DataSource determineTargetDataSource() {
        return dataSourcePerTenantService.retrieveDataSource();
    }

    @Override
    public Connection getConnection(final String username, final String password) throws SQLException {
        return determineTargetDataSource().getConnection(username, password);
    }
}