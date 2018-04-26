package org.ting.ticketselling.exception;

import org.ting.jsonapi.ExceptionResponse;
import org.ting.ticketselling.exception.message.InvalidData;

/**
 * Record exception information for an invalid attribute when bad request is
 * received
 * 
 * @author xiaoyi
 *
 */
public class InvalidField implements ExceptionResponse {

	/**
	 * true if the invalid field is an request body attribute; false if the invalid
	 * field is a query parameter. Default to true
	 */
	private boolean isInvalidPayload;
	private String feildFullName;
	private InvalidData errorMessage;

	public InvalidField(String feildName, InvalidData errorMessage) {
		super();
		this.isInvalidPayload = true;
		this.feildFullName = feildName;
		this.errorMessage = errorMessage;
	}

	public InvalidField(boolean isInvalidPayload, String feildName, InvalidData errorMessage) {
		super();
		this.isInvalidPayload = isInvalidPayload;
		this.feildFullName = feildName;
		this.errorMessage = errorMessage;
	}

	public boolean isInvalidPayload() {
		return isInvalidPayload;
	}

	public void setInvalidPayload(boolean isInvalidPayload) {
		this.isInvalidPayload = isInvalidPayload;
	}

	public String getFeildFullName() {
		return feildFullName;
	}

	public void setFeildFullName(String feildFullName) {
		this.feildFullName = feildFullName;
	}

	public InvalidData getErrorMessage() {
		return errorMessage;
	}

	public void setErrorMessage(InvalidData errorMessage) {
		this.errorMessage = errorMessage;
	}

	@Override
	public String _getStatusCode() {
		return this.errorMessage.getStatusCode().toString();
	}

	@Override
	public String _getDetailCode() {
		return this.errorMessage.getDetailCode();
	}

	@Override
	public String _getErrorTitle() {
		return this.errorMessage.name();
	}

	@Override
	public String _getErrorDetail() {
		return this.errorMessage.getDescription();
	}

	@Override
	public String _getInvalidPayloadAttribute() {
		if (this.isInvalidPayload) {
			return this.feildFullName;
		} else {
			return null;
		}
	}

	@Override
	public String _getInvalidQueryParameter() {
		if (this.isInvalidPayload) {
			return null;
		} else {
			return this.feildFullName;
		}
	}

}
