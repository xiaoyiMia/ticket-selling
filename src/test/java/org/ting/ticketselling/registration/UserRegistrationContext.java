package org.ting.ticketselling.registration;

import java.time.ZonedDateTime;

import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.stereotype.Component;
import org.ting.ticketselling.aggregate.BaseBuilder;
import org.ting.ticketselling.aggregate.customer.Customer;

@Component
public class UserRegistrationContext {
	
	public String generateRegistJsonString(String email, String password) throws JSONException {
		return new JSONObject().put("email", email).put("password", password).toString();
	}
	
	public CustomerBuilder customerBuilder() {
		return new CustomerBuilder();
	}

	public static class CustomerBuilder extends BaseBuilder<Customer> {
		private Customer customer;

		public CustomerBuilder() {
			customer = new Customer();
		}

		public CustomerBuilder id(Long id) {
			customer.setId(id);
			return this;
		}

		public CustomerBuilder email(String email) {
			customer.setEmail(email);
			return this;
		}

		public CustomerBuilder password(String password) {
			customer.setPassword(password);
			return this;
		}

		public CustomerBuilder nickName(String nickName) {
			customer.setNickName(nickName);
			return this;
		}

		public CustomerBuilder description(String description) {
			customer.setDescription(description);
			return this;
		}

		public CustomerBuilder createdAt(ZonedDateTime createdAt) {
			customer.setCreatedAt(createdAt);
			return this;
		}

		public CustomerBuilder updatedAt(ZonedDateTime updatedAt) {
			customer.setUpdatedAt(updatedAt);
			return this;
		}

		@Override
		public Customer build() {
			return customer;
		}
	}
}
