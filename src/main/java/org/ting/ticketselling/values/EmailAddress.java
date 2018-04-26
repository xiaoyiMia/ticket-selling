package org.ting.ticketselling.values;

import org.ting.ticketselling.exception.InvalidField;
import org.ting.ticketselling.exception.message.InvalidData;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import com.google.auto.value.AutoValue;

@AutoValue
public abstract class EmailAddress {

	@JsonCreator
	public static EmailAddress create(String email) {
		return new AutoValue_EmailAddress(email);
	}

	@JsonValue
	public abstract String email();

	public InvalidField validate(String fieldName) {
		String checkingRegex = "^[a-z0-9!#$%&'*+/=?^_`{|}~-]+"
		    + "(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*@(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+"
		    + "[a-z0-9](?:[a-z0-9-]*[a-z0-9])?$";
		if (this.email().matches(checkingRegex)) {
			return null;
		} else {
			return new InvalidField(fieldName, InvalidData.INVALID_EMAIL);
		}
	}

}
