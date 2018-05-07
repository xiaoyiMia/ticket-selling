package org.ting.ticketselling.aggregate.customer;

import org.springframework.stereotype.Component;
import org.ting.ticketselling.exception.InvalidField;
import org.ting.ticketselling.exception.InvalidRequestException;
import org.ting.ticketselling.exception.message.InvalidData;

@Component
public class CustomerValidator {

	public void validatePayloadForRegister(CustomerDto customerDto) {
		if (customerDto == null) {
			InvalidRequestException.throwException(new InvalidField(null, InvalidData.DATA_CANNOT_BLANK));
		} else {
			InvalidRequestException.throwException(customerDto.registerValidate());
		}
	}
}
