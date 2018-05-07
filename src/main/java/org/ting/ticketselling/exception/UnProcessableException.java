package org.ting.ticketselling.exception;

import org.ting.jsonapi.ExceptionResponse;
import org.ting.ticketselling.exception.message.ErrorMessageEnum;

public class UnProcessableException extends HuaMusicException implements ExceptionResponse {

	private static final long serialVersionUID = -7044561803700005353L;

	private ErrorMessageEnum errorMessage;
	
	public UnProcessableException() {
		super(ExceptionLevel.Warning);
	}
	
	public UnProcessableException(ErrorMessageEnum errorMessage) {
		this();
		this.errorMessage = errorMessage;
	}

	public ErrorMessageEnum getErrorMessage() {
		return errorMessage;
	}

	public void setErrorMessage(ErrorMessageEnum errorMessage) {
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
		return this.errorMessage.getTitle();
	}

	@Override
	public String _getErrorDetail() {
		return this.errorMessage.getDescription();
	}
	
}
