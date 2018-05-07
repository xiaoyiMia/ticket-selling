package org.ting.ticketselling.exception;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpStatus;

public class InvalidRequestException extends HuaMusicException {

	private static final long serialVersionUID = -7405772297404588896L;

	private List<InvalidField> invalidFields;
	private ExceptionLevel level;
	
	public InvalidRequestException() {
		super(ExceptionLevel.Warning);
	}

	public InvalidRequestException(List<InvalidField> invalidFields) {
		this();
		this.invalidFields = invalidFields;
	}

	public List<InvalidField> getInvalidFields() {
		return invalidFields;
	}

	public void setInvalidFields(List<InvalidField> invalidFields) {
		this.invalidFields = invalidFields;
	}

	public ExceptionLevel getLevel() {
		return level;
	}

	public void setLevel(ExceptionLevel level) {
		this.level = level;
	}

	public HttpStatus getHttpStatus() {
		return HttpStatus.BAD_REQUEST;
	}

	public static void throwException(List<InvalidField> invalidFields) {
		if (invalidFields != null && !invalidFields.isEmpty()) {
			throw new InvalidRequestException(invalidFields);
		}
	}

	public static void throwException(InvalidField invalidField) {
		if (invalidField != null) {
			List<InvalidField> invalidFields = new ArrayList<InvalidField>();
			invalidFields.add(invalidField);
			throw new InvalidRequestException(invalidFields);
		}
	}

}
