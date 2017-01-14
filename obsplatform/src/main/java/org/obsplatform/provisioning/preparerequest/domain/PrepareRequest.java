package org.obsplatform.provisioning.preparerequest.domain;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import org.obsplatform.infrastructure.core.domain.AbstractAuditableCustom;
import org.obsplatform.portfolio.order.data.OrderStatusEnumaration;
import org.obsplatform.portfolio.order.domain.StatusTypeEnum;
import org.obsplatform.useradministration.domain.AppUser;

@Entity
@Table(name = "b_prepare_request")
public class PrepareRequest extends AbstractAuditableCustom<AppUser, Long>{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Column(name = "client_id")
	private Long clientId;

	@Column(name = "order_id")
	private Long orderId;

	@Column(name = "status")
	private String status;

	@Column(name = "request_type")
	private String requestType;
	
	@Column(name = "is_provisioning")
	private char isProvisioning;
	
	@Column(name = "provisioning_sys")
	private String provisioningSystem;
	
	@Column(name = "plan_name")
	private String planCode;
	
	@Column(name = "is_notify")
	private char isNotify='N';
	
	
	 public  PrepareRequest() {
		
	}


	public PrepareRequest(Long clientId, Long orderId, String requstStatus,	String provisioningSystem, char isProvisioning,
			String generateRequest, String planCode) {
		this.clientId=clientId;
		this.orderId=orderId;
		this.status=OrderStatusEnumaration.OrderStatusType(StatusTypeEnum.INACTIVE).getValue();
		this.isProvisioning=provisioningSystem.equalsIgnoreCase("NONE")?'Y':'N';
		this.requestType=requstStatus;
		this.planCode=planCode;
		this.provisioningSystem=provisioningSystem;
		
	}

	public Long getClientId() {
		return clientId;
	}


	public Long getOrderId() {
		return orderId;
	}


	public String getStatus() {
		return status;
	}


	public String getRequestType() {
		return requestType;
	}


	public char getIsProvisioning() {
		return isProvisioning;
	}


	public void updateProvisioning(char status) {
		this.isProvisioning=status;
		this.status=StatusTypeEnum.ACTIVE.toString();
		
	}


	/**
	 * @param clientId the clientId to set
	 */
	public void setClientId(Long clientId) {
		this.clientId = clientId;
	}


	/**
	 * @param orderId the orderId to set
	 */
	public void setOrderId(Long orderId) {
		this.orderId = orderId;
	}


	/**
	 * @param status the status to set
	 */
	public void setStatus(String status) {
		this.status = status;
	}


	/**
	 * @param requestType the requestType to set
	 */
	public void setRequestType(String requestType) {
		this.requestType = requestType;
	}


	/**
	 * @param isProvisioning the isProvisioning to set
	 */
	public void setIsProvisioning(char isProvisioning) {
		
		this.isProvisioning = isProvisioning;
	}


	/**
	 * @param provisioningSystem the provisioningSystem to set
	 */
	public void setProvisioningSystem(String provisioningSystem) {
		this.provisioningSystem = provisioningSystem;
	}


	public void setCancelStatus(String cancelStatus) {
		this.status=cancelStatus;
		this.isProvisioning='C';
		
	}


	public char getIsNotify() {
		return isNotify;
	}


	public void setIsNotify(char isNotify) {
		this.isNotify = isNotify;
	}
	
			
	}
 
	
	
