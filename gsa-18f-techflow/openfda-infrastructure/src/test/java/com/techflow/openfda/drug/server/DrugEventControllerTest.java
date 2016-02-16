package com.techflow.openfda.drug.server;

import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.assertThat;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.annotation.DirtiesContext.ClassMode;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import com.techflow.openfda.GatewayException;
import com.techflow.openfda.drug.client.MockOpenFdaGateway;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = TestApplication.class)
@DirtiesContext(classMode = ClassMode.AFTER_EACH_TEST_METHOD)
@WebAppConfiguration
public class DrugEventControllerTest
{
	@Autowired
	private MockOpenFdaGateway mockFdaGateway;

	@Autowired
	private WebApplicationContext wac;

	private MockMvc mvc;

	@Before
	public void setup()
	{
		mvc = MockMvcBuilders.webAppContextSetup(wac).build();
	}

	@Test
	public void shouldReturnEvents() throws Exception
	{
		final MvcResult mvcResult = mvc.perform(
				MockMvcRequestBuilders.get("/api/events")
						.contentType(MediaType.APPLICATION_JSON)
						.accept(MediaType.APPLICATION_JSON)
						.param("productNdc", MockOpenFdaGateway.ASPIRIN_NDC))
				.andExpect(MockMvcResultMatchers.status().isOk())
				.andReturn();

		final DrugEventResponse response = SimpleObjectMapper.mapResponse(mvcResult.getResponse(), DrugEventResponse.class);

		assertThat(response.getCongenitalAnomali(), equalTo(1));
		assertThat(response.getDeath(), equalTo(2));
		assertThat(response.getDisabling(), equalTo(1));
		assertThat(response.getHospitalization(), equalTo(1));
		assertThat(response.getLifeThreatening(), equalTo(1));
		assertThat(response.getOther(), equalTo(2));
		assertThat(response.getTotal(), equalTo(8));
	}

	@Test
	public void shouldReturn500OnGatewayException() throws Exception
	{
		mockFdaGateway.exception = new GatewayException();
		final MvcResult mvcResult = mvc.perform(
				MockMvcRequestBuilders.get("/api/events")
						.contentType(MediaType.APPLICATION_JSON)
						.accept(MediaType.APPLICATION_JSON)
						.param("productNdc", MockOpenFdaGateway.ASPIRIN_NDC))
				.andExpect(MockMvcResultMatchers.status().isInternalServerError())
				.andReturn();

		final ErrorResponse response = SimpleObjectMapper.mapResponse(mvcResult.getResponse(), ErrorResponse.class);

		assertThat(response.getMessage(), equalTo("Error communicating with OpenFDA API"));
	}
}
