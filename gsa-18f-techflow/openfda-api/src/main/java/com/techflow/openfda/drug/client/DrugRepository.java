package com.techflow.openfda.drug.client;

import java.util.List;

public interface DrugRepository
{
	List<String> startsWith(String drugName);
}
