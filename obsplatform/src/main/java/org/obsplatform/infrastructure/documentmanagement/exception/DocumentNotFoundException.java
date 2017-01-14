/**
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this file,
 * You can obtain one at http://mozilla.org/MPL/2.0/.
 */
package org.obsplatform.infrastructure.documentmanagement.exception;

import org.obsplatform.infrastructure.core.exception.AbstractPlatformResourceNotFoundException;

public class DocumentNotFoundException extends AbstractPlatformResourceNotFoundException {

	private static final long serialVersionUID = 1L;

	public DocumentNotFoundException(final String entityType, final Long entityId, final Long id) {
        super("error.msg.document.id.invalid", "Document with identifier " + id + " does not exist for the " + entityType
                + " with Identifier " + entityId, id);
    }
}
