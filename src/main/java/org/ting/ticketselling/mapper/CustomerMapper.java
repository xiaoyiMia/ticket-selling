package org.ting.ticketselling.mapper;

import org.mapstruct.Mapper;
import org.ting.ticketselling.aggregate.customer.Customer;
import org.ting.ticketselling.aggregate.customer.CustomerDto;

@Mapper(componentModel="spring")
public interface CustomerMapper {

	public Customer generateFromDto(CustomerDto customerDto);
}
