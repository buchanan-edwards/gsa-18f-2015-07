package com.techflow.openfda.drug.usecase;

import com.techflow.openfda.GatewayException;

/**
 * Use Case:
 * <pre>
 * Given a the NDC of a drug
 * Then return events related to the drug
 * </pre>
 */
public interface ListDrugEventsUseCase extends UseCase<ListDrugEventsRequest, ListDrugEventsResponse>
{
	@Override
	public void execute() throws GatewayException;
}
