package com.techflow.openfda.drugs;

import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.assertThat;
import java.util.Map;
import org.junit.Test;
import com.techflow.openfda.GatewayException;
import com.techflow.openfda.drug.client.MockOpenFdaGateway;
import com.techflow.openfda.drug.usecase.ListDrugEventsRequest;
import com.techflow.openfda.drug.usecase.ListDrugEventsResponse;

public class ListDrugEventsUseCaseImplTest
{

	@Test
	public void test() throws GatewayException
	{
		final ListDrugEventsUseCaseImpl useCase = new ListDrugEventsUseCaseImpl(new MockOpenFdaGateway());
		useCase.setRequest(new ListDrugEventsRequest() {

			@Override
			public String getProductNdc()
			{
				return MockOpenFdaGateway.ASPIRIN_NDC;
			}
		});

		final ListDrugEventsResponseSpy response = new ListDrugEventsResponseSpy();
		useCase.setResponse(response);
		useCase.execute();

		assertThat(response.getCongenitalAnomali(), equalTo(1));
		assertThat(response.getDeath(), equalTo(2));
		assertThat(response.getDisabling(), equalTo(1));
		assertThat(response.getHospitalization(), equalTo(1));
		assertThat(response.getLifeThreatening(), equalTo(1));
		assertThat(response.getOther(), equalTo(2));
		assertThat(response.getTotal(), equalTo(0));
	}

	private final class ListDrugEventsResponseSpy implements ListDrugEventsResponse
	{
		private Map<String, Integer> drugEvents;

		@Override
		public void setEvents(Map<String, Integer> drugEvents)
		{
			this.drugEvents = drugEvents;
		}

		public int getTotal()
		{
			return 0;
		}

		public int getOther()
		{
			return drugEvents.get(Seriousness.OTHER.key());
		}

		public int getLifeThreatening()
		{
			return drugEvents.get(Seriousness.LIFE_THREATENING.key());
		}

		public int getHospitalization()
		{
			return drugEvents.get(Seriousness.HOSPITALIZATION.key());
		}

		public int getDisabling()
		{
			return drugEvents.get(Seriousness.DISABLING.key());
		}

		public int getDeath()
		{
			return drugEvents.get(Seriousness.DEATH.key());
		}

		public int getCongenitalAnomali()
		{
			return drugEvents.get(Seriousness.CONGENITAL_ANOMALI.key());
		}
	}
}
