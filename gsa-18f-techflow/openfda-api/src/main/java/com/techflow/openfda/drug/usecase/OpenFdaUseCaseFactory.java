package com.techflow.openfda.drug.usecase;

public interface OpenFdaUseCaseFactory
{
	FindDrugUseCase newFindDrugUseCase();

	ListDrugEventsUseCase newListDrugEventsUseCase();

	ProvideSearchSuggestionsUseCase newAutocompleteUseCase();
}
