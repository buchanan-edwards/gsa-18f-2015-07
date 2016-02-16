package com.techflow.openfda.drug.usecase;

import com.techflow.openfda.GatewayException;

/**
 * Use Case:
 * <pre>
 * Given a name of a drug
 * Then return the details of the drug
 * </pre>
 */
public interface FindDrugUseCase extends UseCase<FindDrugRequest, FindDrugResponse>
{
	@Override
	public void execute() throws GatewayException;
}
