package org.ting.ticketselling.exception.message;

import org.springframework.http.HttpStatus;

public enum RegistrationError implements ErrorMessageEnum  {
	CUSTOMER_EXIST("01", "Customer with same email adress exists"),
	CUSTOMER_DENIED("02", "Customer cannot use the service");

	private String detailCode;
	private String description;

	RegistrationError(String detailCode, String description) {
		this.detailCode = detailCode;
		this.description = description;
	}
	
	@Override
	public HttpStatus getStatusCode() {
		return HttpStatus.UNPROCESSABLE_ENTITY;
	}

	@Override
	public String getDetailCode() {
		return CategoryCode.Registration + detailCode;
	}

	@Override
	public String getDescription() {
		// TODO Auto-generated method stub
		return description;
	}

	@Override
	public String getTitle() {
		return this.name();
	}

}
