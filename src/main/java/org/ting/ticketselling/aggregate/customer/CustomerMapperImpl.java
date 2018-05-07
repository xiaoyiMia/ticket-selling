package org.ting.ticketselling.aggregate.customer;

import org.springframework.stereotype.Component;

@Component
public class CustomerMapperImpl implements CustomerMapper{

	@Override
	public Customer generateForRegister(CustomerDto customerDto) {
		Customer customer = new Customer();
		customer.setEmail(customerDto.getEmail().email());
		customer.setPassword(customerDto.getPassword().hashedPassword());
		customer.setupActivationToken();
		return customer;
	}

}
