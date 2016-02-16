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
public class DrugControllerTest
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
	public void acceptJson() throws Exception
	{
		final MvcResult mvcResult = mvc.perform(
				MockMvcRequestBuilders.get("/api/drugs")
						.contentType(MediaType.APPLICATION_JSON)
						.accept(MediaType.APPLICATION_JSON)
						.param("name", "aspirin"))
				.andExpect(MockMvcResultMatchers.status().isOk())
				.andReturn();

		final DescribeDrugResponse response = SimpleObjectMapper.mapResponse(mvcResult.getResponse(), DescribeDrugResponse.class);

		assertThat(response.getIndicationsAndUsage(), equalTo("indications"));
		assertThat(response.getBrandName(), equalTo("brand name"));
		assertThat(response.getGenericName(), equalTo("Aspirin"));
		assertThat(response.getPurpose(), equalTo("purpose"));
		assertThat(response.getActive(), equalTo("active"));
		assertThat(response.getInactive(), equalTo("inactive"));
		assertThat(response.getWarnings(), equalTo("warnings"));
		assertThat(response.getDoNotUse(), equalTo("do not use"));
		assertThat(response.getAskDoctor(), equalTo("ask doctor"));
		assertThat(response.getAskDoctorOrPharmacist(), equalTo("ask doctor or pharmacist"));
		assertThat(response.getDosage(), equalTo("dosage"));
		assertThat(response.getStopUse(), equalTo("stop use"));
		assertThat(response.getAdverseReactions(), equalTo("adverse reactions"));
		assertThat(response.getManufacturerName(), equalTo("manufacturer name"));
		assertThat(response.getProductNdc(), equalTo(MockOpenFdaGateway.ASPIRIN_NDC));
		assertThat(response.getWarningsAndCautions(), equalTo("warnings and cautions"));
	}

	@Test
	public void acceptTextPlain() throws Exception
	{
		final MvcResult mvcResult = mvc.perform(
				MockMvcRequestBuilders.get("/api/drugs")
						.contentType(MediaType.TEXT_PLAIN)
						.accept(MediaType.APPLICATION_JSON)
						.param("name", "aspirin"))
				.andExpect(MockMvcResultMatchers.status().isOk())
				.andReturn();

		final DescribeDrugResponse response = SimpleObjectMapper.mapResponse(mvcResult.getResponse(), DescribeDrugResponse.class);

		assertThat(response.getIndicationsAndUsage(), equalTo("indications"));
		assertThat(response.getBrandName(), equalTo("brand name"));
		assertThat(response.getGenericName(), equalTo("Aspirin"));
		assertThat(response.getPurpose(), equalTo("purpose"));
		assertThat(response.getActive(), equalTo("active"));
		assertThat(response.getInactive(), equalTo("inactive"));
		assertThat(response.getWarnings(), equalTo("warnings"));
		assertThat(response.getDoNotUse(), equalTo("do not use"));
		assertThat(response.getAskDoctor(), equalTo("ask doctor"));
		assertThat(response.getAskDoctorOrPharmacist(), equalTo("ask doctor or pharmacist"));
		assertThat(response.getDosage(), equalTo("dosage"));
		assertThat(response.getStopUse(), equalTo("stop use"));
		assertThat(response.getAdverseReactions(), equalTo("adverse reactions"));
		assertThat(response.getManufacturerName(), equalTo("manufacturer name"));
	}

	@Test
	public void missingDrug() throws Exception
	{
		final MvcResult mvcResult = mvc.perform(
				MockMvcRequestBuilders.get("/api/drugs")
						.contentType(MediaType.TEXT_PLAIN_VALUE)
						.accept(MediaType.APPLICATION_JSON)
						.param("name", ""))
				.andExpect(MockMvcResultMatchers.status().isNotFound())
				.andReturn();

		assertThat(mvcResult.getResponse().getContentAsString(), equalTo(""));
	}

	@Test
	public void handleExceptionGracefully() throws Exception
	{
		mockFdaGateway.exception = new GatewayException();
		final MvcResult mvcResult = mvc.perform(
				MockMvcRequestBuilders.get("/api/drugs")
						.contentType(MediaType.TEXT_PLAIN_VALUE)
						.accept(MediaType.APPLICATION_JSON)
						.param("name", "aspirin"))
				.andExpect(MockMvcResultMatchers.status().isInternalServerError())
				.andReturn();

		final ErrorResponse response = SimpleObjectMapper.mapResponse(mvcResult.getResponse(), ErrorResponse.class);

		assertThat(response.getMessage(), equalTo("Error communicating with OpenFDA API"));
	}
}
