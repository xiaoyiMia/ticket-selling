package org.ting.ticketselling.domin.registration;

import org.ting.ticketselling.aggregate.customer.CustomerDto;

public interface RegistrationService {

	public CustomerDto createCustomer(CustomerDto customerDto);

	public CustomerDto findById(int customerId);
}
