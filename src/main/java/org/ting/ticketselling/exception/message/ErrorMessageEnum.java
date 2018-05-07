package org.ting.ticketselling.exception.message;

import org.springframework.http.HttpStatus;

public interface ErrorMessageEnum {

	public HttpStatus getStatusCode();

	public String getDetailCode();
	
	public String getTitle();

	public String getDescription();
}
