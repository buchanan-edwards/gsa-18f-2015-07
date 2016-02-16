package com.techflow.openfda.drugs;

import java.util.HashMap;
import java.util.Map;
import com.techflow.openfda.GatewayException;
import com.techflow.openfda.drug.client.OpenFdaGateway;
import com.techflow.openfda.drug.usecase.BaseUseCase;
import com.techflow.openfda.drug.usecase.ListDrugEventsRequest;
import com.techflow.openfda.drug.usecase.ListDrugEventsResponse;
import com.techflow.openfda.drug.usecase.ListDrugEventsUseCase;

public class ListDrugEventsUseCaseImpl extends BaseUseCase<ListDrugEventsRequest, ListDrugEventsResponse> implements ListDrugEventsUseCase
{
	private final OpenFdaGateway openFdaGateway;

	public ListDrugEventsUseCaseImpl(OpenFdaGateway openFdaGateway) {
		this.openFdaGateway = openFdaGateway;
	}

	@Override
	public void execute() throws GatewayException
	{
		final Map<String, Integer> drugEffects = new HashMap<String, Integer>();
		for (final Seriousness seriousness : Seriousness.values()) {
			final String productNdc = request.getProductNdc();
			final String key = seriousness.key();
			final DrugEventSummary event = openFdaGateway.getEvents(productNdc, seriousness);
			final int count = event.getCount();

			drugEffects.put(key, count);
		}

		response.setEvents(drugEffects);
	}
}
