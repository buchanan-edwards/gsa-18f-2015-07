package com.techflow.openfda.drugs;

import com.techflow.openfda.drug.client.DrugRepository;
import com.techflow.openfda.drug.client.OpenFdaGateway;
import com.techflow.openfda.drug.usecase.ProvideSearchSuggestionsUseCase;
import com.techflow.openfda.drug.usecase.ListDrugEventsUseCase;
import com.techflow.openfda.drug.usecase.OpenFdaUseCaseFactory;

public class SimpleOpenFdaSpringUseCaseFactory implements OpenFdaUseCaseFactory
{
	private final OpenFdaGateway openFdaGateway;

	private final DrugRepository drugRepository;

	public SimpleOpenFdaSpringUseCaseFactory(OpenFdaGateway openFdaGateway, DrugRepository drugRepository) {
		this.openFdaGateway = openFdaGateway;
		this.drugRepository = drugRepository;
	}

	@Override
	public FindDrugUseCaseImpl newFindDrugUseCase()
	{
		return new FindDrugUseCaseImpl(openFdaGateway);
	}

	@Override
	public ListDrugEventsUseCase newListDrugEventsUseCase()
	{
		return new ListDrugEventsUseCaseImpl(openFdaGateway);
	}

	@Override
	public ProvideSearchSuggestionsUseCase newAutocompleteUseCase()
	{
		return new ProvideSearchSuggestionsUseCaseImpl(drugRepository);
	}
}
