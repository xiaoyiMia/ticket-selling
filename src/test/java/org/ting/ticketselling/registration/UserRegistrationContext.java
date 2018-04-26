package org.ting.ticketselling.registration;

import org.springframework.stereotype.Component;
import org.ting.ticketselling.aggregate.customer.CustomerDto;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;

@Component
public class UserRegistrationContext {

	public String customerRegisterContext(CustomerDto customer) {
		JsonNode rootNode = new ObjectMapper().createObjectNode();
		((ObjectNode) rootNode).put("email", customer.getEmail().email());
		((ObjectNode) rootNode).put("password", customer.getPassword().password());

		return rootNode.toString();
	}
}
