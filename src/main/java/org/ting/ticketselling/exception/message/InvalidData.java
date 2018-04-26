package org.ting.ticketselling.exception.message;

import org.springframework.http.HttpStatus;

public enum InvalidData implements ErrorMessageEnum {
	DATA_CANNOT_BLANK("01", "Data cannot be blank"), INVALID_EMAIL("02", "Invalid email address"), DATA_TOO_LONG("03",
	    "Data length too long"), DATA_TOO_SHORT("04", "Data length too short");

	private String detailCode;
	private String description;

	InvalidData(String detailCode, String description) {
		this.detailCode = detailCode;
		this.description = description;
	}

	@Override
	public HttpStatus getStatusCode() {
		return HttpStatus.BAD_REQUEST;
	}

	@Override
	public String getDetailCode() {
		return HttpStatus.BAD_REQUEST.toString() + CategoryCode.Common + detailCode;
	}

	@Override
	public String getDescription() {
		return description;
	}

}
