package com.techflow.openfda.drug.client;

import com.techflow.openfda.GatewayException;
import com.techflow.openfda.drugs.DrugEventSummary;
import com.techflow.openfda.drugs.DrugLabel;
import com.techflow.openfda.drugs.Seriousness;

public interface OpenFdaGateway
{
	DrugLabel getLabel(String drugName) throws GatewayException;

	DrugEventSummary getEvents(String productNdc, Seriousness seriousness) throws GatewayException;
}
