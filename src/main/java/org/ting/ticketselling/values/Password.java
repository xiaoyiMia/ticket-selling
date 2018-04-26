package org.ting.ticketselling.values;

import org.ting.ticketselling.exception.InvalidField;
import org.ting.ticketselling.exception.message.InvalidData;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import com.google.auto.value.AutoValue;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@AutoValue
public abstract class Password {

	@JsonCreator
	public static Password create(String password) {
		return new AutoValue_Password(password);
	}

	@JsonValue
	public abstract String password();

	private int minLoginLength = 6;
	private int maxLoginLength = 20;

	public InvalidField validateForLogin(String fieldName) {
		int length = this.password().length();
		if (length < minLoginLength) {
			return new InvalidField(fieldName, InvalidData.DATA_TOO_SHORT);
		} else if (length > maxLoginLength) {
			return new InvalidField(fieldName, InvalidData.DATA_TOO_LONG);
		} else {
			return null;
		}
	}

	public String hashedPassword() {
		return new BCryptPasswordEncoder().encode(this.password());
	}

	public boolean matchPassword(String hashedPassword) {
		return new BCryptPasswordEncoder().matches(this.password(), hashedPassword);
	}

}
