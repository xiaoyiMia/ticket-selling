package org.ting.ticketselling.model.value;

import org.junit.Assert;
import org.junit.Test;
import org.ting.ticketselling.exception.InvalidField;
import org.ting.ticketselling.exception.message.InvalidData;
import org.ting.ticketselling.values.EmailAddress;

public class EmailValidation {

	@Test
	public void characterOnlyGmail() {
		String emailAddress = "test@gmail.com";
		EmailAddress email = EmailAddress.create(emailAddress);
		Assert.assertNull(email.validate("email"));
	}

	@Test
	public void characterWithPlusInBetweenGmail() {
		String emailAddress = "test+1@gmail.com";
		EmailAddress email = EmailAddress.create(emailAddress);
		Assert.assertNull(email.validate("email"));
	}

	@Test
	public void dotOnlyGmail() {
		String emailAddress = ".@gmail.com";
		EmailAddress email = EmailAddress.create(emailAddress);

		InvalidField invalidField = email.validate("email");
		Assert.assertNotNull(invalidField);
		Assert.assertEquals(InvalidData.INVALID_EMAIL, invalidField.getErrorMessage());
	}

	@Test
	public void characterWithDotInBetweenGmail() {
		String emailAddress = "li.test@gmail.com";
		EmailAddress email = EmailAddress.create(emailAddress);
		Assert.assertNull(email.validate("email"));
	}

	@Test
	public void characterOnlyNoDomain() {
		String emailAddress = "test@guiker";
		EmailAddress email = EmailAddress.create(emailAddress);

		InvalidField invalidField = email.validate("email");
		Assert.assertNotNull(invalidField);
		Assert.assertEquals(InvalidData.INVALID_EMAIL, invalidField.getErrorMessage());
	}

	@Test
	public void emptyString() {
		String emailAddress = "";
		EmailAddress email = EmailAddress.create(emailAddress);

		InvalidField invalidField = email.validate("email");
		Assert.assertNotNull(invalidField);
		Assert.assertEquals(InvalidData.INVALID_EMAIL, invalidField.getErrorMessage());
	}
}
