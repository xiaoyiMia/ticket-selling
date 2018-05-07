package org.ting.ticketselling.aggregate.customer;

import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;

import org.ting.jsonapi.annotations.JsonApiId;
import org.ting.jsonapi.annotations.JsonApiResource;
import org.ting.ticketselling.aggregate.BaseBuilder;
import org.ting.ticketselling.aggregate.UserDto;
import org.ting.ticketselling.aggregate.UserStatus;
import org.ting.ticketselling.exception.InvalidField;
import org.ting.ticketselling.exception.message.InvalidData;
import org.ting.ticketselling.values.EmailAddress;
import org.ting.ticketselling.values.Password;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;

@JsonApiResource(type = "customer")
@JsonIgnoreProperties(ignoreUnknown = true)
public class CustomerDto extends UserDto {

	@JsonApiId
	private Long id;
	private EmailAddress email;
	@JsonProperty(access = Access.WRITE_ONLY)
	private Password password;
	private UserStatus status;
	private String nickName;
	private String description;
	private ZonedDateTime createdAt;
	private ZonedDateTime updatedAt;

	public CustomerDto() {
		super();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public EmailAddress getEmail() {
		return email;
	}

	public void setEmail(EmailAddress email) {
		this.email = email;
	}

	public Password getPassword() {
		return password;
	}

	public void setPassword(Password password) {
		this.password = password;
	}

	public UserStatus getStatus() {
		return status;
	}

	public void setStatus(UserStatus status) {
		this.status = status;
	}

	public String getNickName() {
		return nickName;
	}

	public void setNickName(String nickName) {
		this.nickName = nickName;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public ZonedDateTime getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(ZonedDateTime createdAt) {
		this.createdAt = createdAt;
	}

	public ZonedDateTime getUpdatedAt() {
		return updatedAt;
	}

	public void setUpdatedAt(ZonedDateTime updatedAt) {
		this.updatedAt = updatedAt;
	}

	public List<InvalidField> registerValidate() {
		List<InvalidField> invalidFields = new ArrayList<InvalidField>();
		if (this.email == null) {
			invalidFields.add(new InvalidField("email", InvalidData.DATA_CANNOT_BLANK));
		} else {
			invalidFields.add(this.email.validate("email"));
		}

		if (this.password == null) {
			invalidFields.add(new InvalidField("password", InvalidData.DATA_CANNOT_BLANK));
		} else {
			invalidFields.add(this.password.validateForLogin("password"));
		}
		
		return invalidFields;
	}

	public static Builder builder() {
		return new Builder();
	}

	public static class Builder extends BaseBuilder<CustomerDto> {
		private CustomerDto customer;

		public Builder() {
			customer = new CustomerDto();
		}

		public Builder id(Long id) {
			customer.setId(id);
			return this;
		}

		public Builder email(String email) {
			customer.setEmail(EmailAddress.create(email));
			return this;
		}

		public Builder password(String password) {
			customer.setPassword(Password.create(password));
			return this;
		}

		public Builder nickName(String nickName) {
			customer.setNickName(nickName);
			return this;
		}

		public Builder description(String description) {
			customer.setDescription(description);
			return this;
		}

		public Builder createdAt(ZonedDateTime createdAt) {
			customer.setCreatedAt(createdAt);
			return this;
		}

		public Builder updatedAt(ZonedDateTime updatedAt) {
			customer.setUpdatedAt(updatedAt);
			return this;
		}

		@Override
		public CustomerDto build() {
			return customer;
		}

	}
}
