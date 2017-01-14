package org.obsplatform.billing.selfcare.exception;

import org.obsplatform.infrastructure.core.exception.AbstractPlatformDomainRuleException;

public class SelfCareTemporaryGeneratedKeyNotFoundException extends AbstractPlatformDomainRuleException{

	public SelfCareTemporaryGeneratedKeyNotFoundException(final String generatedKey, String uniqueReference){
		 super("error.msg.billing.generatedKey.not.found", "AutoGenerated Key not found with this : " 
	            + generatedKey + " and this Email Address : " + uniqueReference);
	}
}
