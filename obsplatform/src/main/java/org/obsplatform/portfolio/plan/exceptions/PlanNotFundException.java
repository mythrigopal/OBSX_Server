package org.obsplatform.portfolio.plan.exceptions;

import org.obsplatform.infrastructure.core.exception.AbstractPlatformResourceNotFoundException;

public class PlanNotFundException extends AbstractPlatformResourceNotFoundException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public PlanNotFundException() {
		super("error.msg.depositproduct.id.invalid","Charge Code already exists with same plan");
	}

public PlanNotFundException(Long planId) {
	super("error.msg.plan.with.id.not.exists","Plan was alreay deleted");

}

}
