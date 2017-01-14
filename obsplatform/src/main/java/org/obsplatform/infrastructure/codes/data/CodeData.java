/**
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this file,
 * You can obtain one at http://mozilla.org/MPL/2.0/.
 */
package org.obsplatform.infrastructure.codes.data;

/**
 * Immutable data object representing a code.
 */
public class CodeData {

    @SuppressWarnings("unused")
    private final Long id;
    @SuppressWarnings("unused")
    private final String name;
    @SuppressWarnings("unused")
    private final boolean systemDefined;
    @SuppressWarnings("unused")
	private final String description;
    
    public static CodeData instance(final Long id, final String name, final String description,final boolean systemDefined) {
        return new CodeData(id, name, description,systemDefined);
    }

    private CodeData(final Long id, final String name, final String description,final boolean systemDefined) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.systemDefined = systemDefined;
    }

	public Long getCodeId() {
		 return this.id;       
   }
}