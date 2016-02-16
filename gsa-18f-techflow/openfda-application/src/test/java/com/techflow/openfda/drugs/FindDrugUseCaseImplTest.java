package com.techflow.openfda.drugs;

import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.assertThat;
import org.junit.Test;
import com.techflow.openfda.GatewayException;
import com.techflow.openfda.drug.client.MockOpenFdaGateway;
import com.techflow.openfda.drug.usecase.FindDrugRequest;
import com.techflow.openfda.drug.usecase.FindDrugResponse;

public class FindDrugUseCaseImplTest
{
	@Test
	public void test() throws GatewayException
	{
		final FindDrugUseCaseImpl useCase = new FindDrugUseCaseImpl(new MockOpenFdaGateway());
		useCase.setRequest(new FindDrugRequest() {
			@Override
			public String getName()
			{
				return "aspirin";
			}
		});

		final FindDrugResponseImplementation response = new FindDrugResponseImplementation();
		useCase.setResponse(response);
		useCase.execute();

		assertThat(response.indicationsAndUsage, equalTo("indications"));
		assertThat(response.brandName, equalTo("brand name"));
		assertThat(response.genericName, equalTo("Aspirin"));
		assertThat(response.purpose, equalTo("purpose"));
		assertThat(response.active, equalTo("active"));
		assertThat(response.inactive, equalTo("inactive"));
		assertThat(response.warnings, equalTo("warnings"));
		assertThat(response.doNotUse, equalTo("do not use"));
		assertThat(response.askDoctor, equalTo("ask doctor"));
		assertThat(response.askDoctorOrPharmacist, equalTo("ask doctor or pharmacist"));
		assertThat(response.dosage, equalTo("dosage"));
		assertThat(response.stopUse, equalTo("stop use"));
		assertThat(response.adverseReactions, equalTo("adverse reactions"));
		assertThat(response.manufacturerName, equalTo("manufacturer name"));
		assertThat(response.notFound, equalTo(false));
		assertThat(response.productNdc, equalTo(MockOpenFdaGateway.ASPIRIN_NDC));
		assertThat(response.warningsAndCautions, equalTo("warnings and cautions"));
	}

	private final class FindDrugResponseImplementation implements FindDrugResponse
	{
		public String warningsAndCautions;

		public String productNdc;

		public String manufacturerName;

		public String adverseReactions;

		public String askDoctorOrPharmacist;

		private String warnings;

		private String stopUse;

		private String purpose;

		private boolean notFound;

		private String indicationsAndUsage;

		private String inactive;

		private String genericName;

		private String dosage;

		private String doNotUse;

		private String brandName;

		private String askDoctor;

		private String active;

		@Override
		public void setWarnings(String warnings)
		{
			this.warnings = warnings;
		}

		@Override
		public void setStopUse(String stopUse)
		{
			this.stopUse = stopUse;
		}

		@Override
		public void setPurpose(String purpose)
		{
			this.purpose = purpose;
		}

		@Override
		public void setNotFound(boolean notFound)
		{
			this.notFound = notFound;
		}

		@Override
		public void setIndicationsAndUsage(String indicationsAndUsage)
		{
			this.indicationsAndUsage = indicationsAndUsage;
		}

		@Override
		public void setInactive(String inactive)
		{
			this.inactive = inactive;
		}

		@Override
		public void setGenericName(String genericName)
		{
			this.genericName = genericName;
		}

		@Override
		public void setDosage(String dosage)
		{
			this.dosage = dosage;
		}

		@Override
		public void setDoNotUse(String doNotUse)
		{
			this.doNotUse = doNotUse;
		}

		@Override
		public void setBrandName(String brandName)
		{
			this.brandName = brandName;
		}

		@Override
		public void setAskDoctor(String askDoctor)
		{
			this.askDoctor = askDoctor;
		}

		@Override
		public void setActive(String active)
		{
			this.active = active;
		}

		@Override
		public void setAskDoctorOrPharmacist(String askDoctorOrPharmacist)
		{
			this.askDoctorOrPharmacist = askDoctorOrPharmacist;
		}

		@Override
		public void setAdverseReactions(String adverseReactions)
		{
			this.adverseReactions = adverseReactions;
		}

		@Override
		public void setManufacturerName(String manufacturerNamme)
		{
			manufacturerName = manufacturerNamme;
		}

		@Override
		public void setProductNdc(String productNdc)
		{
			this.productNdc = productNdc;
		}

		@Override
		public void setWarningsAndCautions(String warningsAndCautions)
		{
			this.warningsAndCautions = warningsAndCautions;
		}
	}
}
