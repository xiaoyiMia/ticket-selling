package org.ting.ticketselling.registration;

import static org.mockito.BDDMockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.restdocs.request.RequestDocumentation.*;
import static org.springframework.restdocs.payload.PayloadDocumentation.*;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;

import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.junit.*;
import org.junit.runner.*;
import org.mockito.InjectMocks;
import org.springframework.beans.factory.annotation.*;
import org.springframework.boot.test.autoconfigure.restdocs.AutoConfigureRestDocs;
import org.springframework.boot.test.autoconfigure.web.servlet.*;
import org.springframework.boot.test.mock.mockito.*;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.ting.ticketselling.aggregate.customer.Customer;
import org.ting.ticketselling.aggregate.customer.CustomerDto;
import org.ting.ticketselling.aggregate.customer.CustomerMapper;
import org.ting.ticketselling.aggregate.customer.CustomerValidator;
import org.ting.ticketselling.domin.registration.CustomerController;
import org.ting.ticketselling.domin.registration.CustomerRepository;
import org.ting.ticketselling.email.RegistrationEmailService;

import com.fasterxml.jackson.databind.ObjectMapper;

@RunWith(SpringRunner.class)
@WebMvcTest(CustomerController.class)
@Import(UserRegistrationContext.class)
@AutoConfigureRestDocs(outputDir = "target/snippets")
public class CustomerControllerTests {

	@MockBean
	private CustomerRepository customerRepository;
	@MockBean
	private RegistrationEmailService emailService;
	@MockBean
	private CustomerValidator customerValidator;
	@MockBean
	private CustomerMapper customerMapper;
	@InjectMocks
	private CustomerController customerController;

	@Autowired
	private MockMvc mockMvc;
	@Autowired
	private UserRegistrationContext registContext;

	@Test
	public void CustomerRegisterSuccess() throws Exception {
		String registerEmail = "ting@guiker.com";
		String registerPassword = "12345678";
		String jsonPayload = registContext.generateRegistJsonString(registerEmail, registerPassword);
		System.out.println(jsonPayload);

		given(customerRepository.findByEmail(eq(jsonPayload))).willReturn(null);
		Customer customer = registContext.customerBuilder().id(1L).email(registerEmail).password(registerPassword).build();
		given(customerRepository.save(any())).willReturn(customer);

		this.mockMvc.perform(post("/api/customers/").contentType(MediaType.APPLICATION_JSON).content(jsonPayload))
		    .andExpect(status().isCreated()).andDo(document("customer-register", requestFields(
		    		fieldWithPath("email").description("The register email"),
		    		fieldWithPath("password").description("The register password")
		    		)));
	}
}
