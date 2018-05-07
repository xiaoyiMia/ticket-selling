package org.ting.ticketselling.aggregate.customer;

import java.time.ZonedDateTime;
import java.util.UUID;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import org.ting.ticketselling.aggregate.UserStatus;
import org.ting.ticketselling.values.Password;

@Entity
public class Customer {

	@Id
	@GeneratedValue
	private Long id;
	private String email;
	private String password;
	private UserStatus status;
	private String externalId;
	private String activationToken;
	private ZonedDateTime tokenExpiresAt;
	private String nickName;
	private String description;
	private String avatar;
	private Boolean isDeleted;
	private ZonedDateTime createdAt;
	private ZonedDateTime updatedAt;

	public Customer() {
		this.status = UserStatus.INACTIVE;
		this.nickName = "ET";
		this.createdAt = ZonedDateTime.now();
		this.updatedAt = ZonedDateTime.now();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public UserStatus getStatus() {
		return status;
	}

	public void setStatus(UserStatus status) {
		this.status = status;
	}

	public String getExternalId() {
		return externalId;
	}

	public void setExternalId(String externalId) {
		this.externalId = externalId;
	}

	public String getActivationToken() {
		return activationToken;
	}

	public void setActivationToken(String activationToken) {
		this.activationToken = activationToken;
	}

	public ZonedDateTime getTokenExpiresAt() {
		return tokenExpiresAt;
	}

	public void setTokenExpiresAt(ZonedDateTime tokenExpiresAt) {
		this.tokenExpiresAt = tokenExpiresAt;
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

	public String getAvatar() {
		return avatar;
	}

	public void setAvatar(String avatar) {
		this.avatar = avatar;
	}

	public Boolean getIsDeleted() {
		return isDeleted;
	}

	public void setIsDeleted(Boolean isDeleted) {
		this.isDeleted = isDeleted;
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

	public void setupActivationToken() {
		if (this.getActivationToken() == null || this.getTokenExpiresAt().isBefore(ZonedDateTime.now())) {
			this.setActivationToken(UUID.randomUUID().toString());
		}
	}
	
	public void setupPassword(Password password) {
		if(password == null) {
			this.setPassword(UUID.randomUUID().toString());
		} else {
			this.setPassword(password.hashedPassword());
		}
	}

}
