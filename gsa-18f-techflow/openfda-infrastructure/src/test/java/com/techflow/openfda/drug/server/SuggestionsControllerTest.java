package com.techflow.openfda.drug.server;

import static org.hamcrest.Matchers.contains;
import static org.hamcrest.Matchers.containsInAnyOrder;
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
public class SuggestionsControllerTest
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
	public void shouldReturnAutocomplete() throws Exception
	{
		mockFdaGateway.exception = new GatewayException();
		final MvcResult mvcResult = mvc.perform(
				MockMvcRequestBuilders.get("/api/suggestions")
						.contentType(MediaType.TEXT_PLAIN_VALUE)
						.accept(MediaType.APPLICATION_JSON)
						.param("name", "asp")
						.param("view", "autocomplete"))
				.andExpect(MockMvcResultMatchers.status().isOk())
				.andReturn();

		final SuggestionsResponse response = SimpleObjectMapper.mapResponse(mvcResult.getResponse(), SuggestionsResponse.class);

		assertThat(response.getSuggestions(), contains("aspirin"));
	}

	@Test
	public void autoCompleteforTylenol() throws Exception
	{
		mockFdaGateway.exception = new GatewayException();
		final MvcResult mvcResult = mvc.perform(
				MockMvcRequestBuilders.get("/api/suggestions")
						.contentType(MediaType.TEXT_PLAIN_VALUE)
						.accept(MediaType.APPLICATION_JSON)
						.param("name", "Ty")
						.param("view", "autocomplete"))
				.andExpect(MockMvcResultMatchers.status().isOk())
				.andReturn();

		final SuggestionsResponse response = SimpleObjectMapper.mapResponse(mvcResult.getResponse(), SuggestionsResponse.class);

		assertThat(response.getSuggestions(), containsInAnyOrder("tylenol", "tylenol pm"));
	}
}
