package org.ting.ticketselling.exception.message;

public enum CategoryCode {
	Common("001"),
	Registration("010");

	private String categoryCode;

	CategoryCode(String categoryCode) {
		this.categoryCode = categoryCode;
	}

	public String categoryCode() {
		return categoryCode;
	}
}
