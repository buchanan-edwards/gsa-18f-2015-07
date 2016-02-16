package com.techflow.openfda.drugs;

import java.util.ArrayList;
import java.util.List;
import com.techflow.openfda.drug.client.DrugRepository;

public class NaiveInMemoryDrugRepository implements DrugRepository
{
	protected List<String> drugs = new ArrayList<String>();

	@Override
	public List<String> startsWith(String drugName)
	{
		final List<String> results = new ArrayList<String>();

		final String lowerCaseDrugName = drugName.toLowerCase();
		for (final String drug : drugs) {
			if (drug.startsWith(lowerCaseDrugName)) {
				results.add(drug);
			}
		}

		return results;
	}

	public void add(String drug)
	{
		drugs.add(drug.toLowerCase());
	}
}
