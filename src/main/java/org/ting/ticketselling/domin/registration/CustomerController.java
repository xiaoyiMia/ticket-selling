package org.ting.ticketselling.domin.registration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.ting.ticketselling.aggregate.customer.Customer;
import org.ting.ticketselling.aggregate.customer.CustomerDto;
import org.ting.ticketselling.exception.InvalidField;
import org.ting.ticketselling.exception.InvalidRequestException;
import org.ting.ticketselling.exception.message.InvalidData;
import org.ting.ticketselling.mapper.CustomerMapper;

@RestController
@RequestMapping("/api/customers")
public class CustomerController {

	@Autowired
	private CustomerRepository customerRepository;

	@Autowired
	private RegistrationService registService;
	
	@Autowired
	private CustomerMapper customerMapper;

	/**
	 * Customer Register.
	 * 
	 * @param customer
	 *          A customer object contains register information.
	 * @return
	 */
	@PostMapping("/")
	public void register(CustomerDto customerDto) {
		if (customerDto == null) {
			InvalidRequestException.throwException(new InvalidField(null, InvalidData.DATA_CANNOT_BLANK));
		} else {
			InvalidRequestException.throwException(customerDto.registerValidate());
		}

		Customer customer = customerRepository.findByEmail(customerDto.getEmail().email());
		if (customer == null) {
			customer = customerMapper.generateFromDto(customerDto);
			customer.setupActivationToken();
			customer = customerRepository.save(customer);
		}
	}

	@GetMapping("/{customer_id}")
	public CustomerDto getCustomer() {
		CustomerDto customer = registService.findById(0);
		customer = CustomerDto.builder().email("ting@guiker.com").password("12345678").nickName("xiaoyi")
		    .description("I am a ghost").build();
		return customer;
	}

}
