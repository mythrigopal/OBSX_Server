/**
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this file,
 * You can obtain one at http://mozilla.org/MPL/2.0/.
 */
package org.obsplatform.portfolio.client.service;

import java.util.Collection;

import org.obsplatform.portfolio.client.data.ClientIdentifierData;

public interface ClientIdentifierReadPlatformService {

    Collection<ClientIdentifierData> retrieveClientIdentifiers(Long clientId);

    ClientIdentifierData retrieveClientIdentifier(Long clientId, Long clientIdentifierId);

    
}