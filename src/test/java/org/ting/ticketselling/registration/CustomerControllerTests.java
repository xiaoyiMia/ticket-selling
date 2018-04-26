package org.ting.ticketselling.registration;

import static org.hamcrest.Matchers.containsString;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.mockito.BDDMockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

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

import com.fasterxml.jackson.databind.ObjectMapper;

import org.ting.ticketselling.aggregate.customer.CustomerDto;
import org.ting.ticketselling.domin.registration.CustomerController;
import org.ting.ticketselling.domin.registration.RegistrationService;

@RunWith(SpringRunner.class)
@WebMvcTest(CustomerController.class)
@Import(UserRegistrationContext.class)
@AutoConfigureRestDocs(outputDir = "target/snippets")
public class CustomerControllerTests {

	@MockBean
	private RegistrationService registrationService;
	@InjectMocks
	private CustomerController customerController;

	@Autowired
	private MockMvc mockMvc;

	@Autowired
	private ObjectMapper objectMapper;
	@Autowired
	private UserRegistrationContext registerContext;

	@Test
	public void shouldReturnDefaultMessage() throws Exception {
		CustomerDto customer = CustomerDto.builder().email("ting@guiker.com").password("12345678").nickName("xiaoyi")
		    .description("I am a ghost").build();
		String expectedBody = objectMapper.writeValueAsString(customer);

		when(registrationService.findById(any(Integer.class))).thenReturn(customer);

		this.mockMvc.perform(get("/api/customers/")).andExpect(status().isOk())
		    .andExpect(content().string(containsString(expectedBody))).andDo(document("test3"));
	}

	@Test
	public void CustomerRegisterSuccess() throws Exception {
		CustomerDto customerInput = CustomerDto.builder().email("ting@guiker.com").password("12345678").build();
		String jsonPayload = registerContext.customerRegisterContext(customerInput);
		System.out.println(jsonPayload);

		CustomerDto customerOutput = CustomerDto.builder().id(Long.valueOf(1)).email("ting@guiker.com").password("12345678")
		    .build();
		String jsonResponse = objectMapper.writeValueAsString(customerOutput);
		System.out.println(jsonResponse);

		given(registrationService.createCustomer(any(CustomerDto.class))).willReturn(customerOutput);

		this.mockMvc.perform(post("/api/customers/").content(jsonPayload)).andExpect(status().isOk())
		    .andExpect(content().string(containsString(jsonResponse))).andDo(document("test2"));
	}
}
