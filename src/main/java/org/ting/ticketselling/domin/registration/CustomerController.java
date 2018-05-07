package org.ting.ticketselling.domin.registration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.HttpStatus;

import org.ting.ticketselling.aggregate.UserStatus;
import org.ting.ticketselling.aggregate.customer.Customer;
import org.ting.ticketselling.aggregate.customer.CustomerDto;
import org.ting.ticketselling.aggregate.customer.CustomerMapper;
import org.ting.ticketselling.aggregate.customer.CustomerValidator;
import org.ting.ticketselling.email.RegistrationEmailService;
import org.ting.ticketselling.exception.UnProcessableException;
import org.ting.ticketselling.exception.message.RegistrationError;

@RestController
@RequestMapping("/api/customers")
public class CustomerController {

	@Autowired
	private CustomerRepository customerRepository;

	@Autowired
	private RegistrationEmailService emailService;
	
	@Autowired
	private CustomerMapper customerMapper;
	@Autowired
	private CustomerValidator customerValidator;

	/**
	 * Customer Register.
	 * 
	 * @param customer
	 *          A customer object contains register information.
	 * @return
	 */
	@PostMapping("/")
	@ResponseStatus(HttpStatus.CREATED)
	public void register(@RequestBody CustomerDto customerDto) {
		customerValidator.validatePayloadForRegister(customerDto);

		Customer customer = customerRepository.findByEmail(customerDto.getEmail().email());
		if (customer == null) {
			customer = customerMapper.generateForRegister(customerDto);
			saveAndSendConfirmationEmail(customer);
			
		} else if(customer.getIsDeleted()) {
			customer.setIsDeleted(false);
			customer.setStatus(UserStatus.INACTIVE);
			customer.setupActivationToken();
			saveAndSendConfirmationEmail(customer);
			
		} else if(customer.getStatus() == UserStatus.ACTIVE) {
			throw new UnProcessableException(RegistrationError.CUSTOMER_EXIST);
			
		} else if(customer.getStatus() == UserStatus.INACTIVE) {
			customer.setupActivationToken();
			saveAndSendConfirmationEmail(customer);
			
		} else if(customer.getStatus() == UserStatus.DENY) {
			throw new UnProcessableException(RegistrationError.CUSTOMER_DENIED);
		}
	}
	
	private void saveAndSendConfirmationEmail(Customer customer) {
		customer = customerRepository.save(customer);
		emailService.customerRegistrationConfirmation(customer.getId());
	}

}
