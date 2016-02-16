package com.techflow.openfda.drug.usecase;

import java.util.Map;

public interface ListDrugEventsResponse
{
	void setEvents(Map<String, Integer> drugEvents);
}
