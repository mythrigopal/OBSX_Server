/**
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this file,
 * You can obtain one at http://mozilla.org/MPL/2.0/.
 */
package org.obsplatform.portfolio.client.service;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collection;

import org.obsplatform.infrastructure.codes.data.CodeValueData;
import org.obsplatform.infrastructure.core.domain.JdbcSupport;
import org.obsplatform.infrastructure.core.service.TenantAwareRoutingDataSource;
import org.obsplatform.infrastructure.security.service.PlatformSecurityContext;
import org.obsplatform.portfolio.client.data.ClientIdentifierData;
import org.obsplatform.portfolio.client.exception.ClientIdentifierNotFoundException;
import org.obsplatform.useradministration.domain.AppUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Service;

/**
 * @author hugo
 *
 */
@Service
public class ClientIdentifierReadPlatformServiceImp implements ClientIdentifierReadPlatformService {

    private final JdbcTemplate jdbcTemplate;
    private final PlatformSecurityContext context;

    @Autowired
    public ClientIdentifierReadPlatformServiceImp(final PlatformSecurityContext context, final TenantAwareRoutingDataSource dataSource) {
        this.context = context;
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    /* (non-Javadoc)
     * @see #retrieveClientIdentifiers(java.lang.Long)
     */
    @Override
    public Collection<ClientIdentifierData> retrieveClientIdentifiers(final Long clientId) {

        final AppUser currentUser = this.context.authenticatedUser();
        final String hierarchy = currentUser.getOffice().getHierarchy();
        final String hierarchySearchString = hierarchy + "%";

        final ClientIdentityMapper rm = new ClientIdentityMapper();

        String sql = "select " + rm.schema();

        sql += " order by ci.id";

        return this.jdbcTemplate.query(sql, rm, new Object[] { clientId, hierarchySearchString });
    }

    /* (non-Javadoc)
     * @see #retrieveClientIdentifier(java.lang.Long, java.lang.Long)
     */
    @Override
    public ClientIdentifierData retrieveClientIdentifier(final Long clientId, final Long clientIdentifierId) {
        try {
            final AppUser currentUser = this.context.authenticatedUser();
            final String hierarchy = currentUser.getOffice().getHierarchy();
            final String hierarchySearchString = hierarchy + "%";

            final ClientIdentityMapper rm = new ClientIdentityMapper();

            String sql = "select " + rm.schema();

            sql += " and ci.id = ?";

            final ClientIdentifierData clientIdentifierData = this.jdbcTemplate.queryForObject(sql, rm, new Object[] { clientId,
                    hierarchySearchString, clientIdentifierId });

            return clientIdentifierData;
        } catch (final EmptyResultDataAccessException e) {
            throw new ClientIdentifierNotFoundException(clientIdentifierId);
        }

    }

    private static final class ClientIdentityMapper implements RowMapper<ClientIdentifierData> {

        public ClientIdentityMapper() {}

        public String schema() {
            return "ci.id as id, ci.client_id as clientId, ci.document_type_id as documentTypeId, ci.document_key as documentKey,"
                    + " ci.description as description, cv.code_value as documentType "
                    + " from m_client_identifier ci, m_client c, m_office o, m_code_value cv"
                    + " where ci.client_id=c.id and c.office_id=o.id" + " and ci.document_type_id=cv.id"
                    + " and ci.client_id = ? and o.hierarchy like ? ";
        }

        @Override
        public ClientIdentifierData mapRow(final ResultSet rs, final int rowNum) throws SQLException {

            final Long id = JdbcSupport.getLong(rs, "id");
            final Long clientId = JdbcSupport.getLong(rs, "clientId");
            final Long documentTypeId = JdbcSupport.getLong(rs, "documentTypeId");
            final String documentKey = rs.getString("documentKey");
            final String description = rs.getString("description");
            final String documentTypeName = rs.getString("documentType");

            final CodeValueData documentType = CodeValueData.instance(documentTypeId, documentTypeName);

            return ClientIdentifierData.singleItem(id, clientId, documentType, documentKey, description);
        }

    }
    
}