package org.ting.ticketselling.domin.registration;

import org.springframework.data.repository.CrudRepository;
import org.ting.ticketselling.aggregate.customer.Customer;

public interface CustomerRepository extends CrudRepository<Customer, Integer> {

	public Customer findByEmail(String email);
}
