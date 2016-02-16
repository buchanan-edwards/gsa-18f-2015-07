package com.techflow.openfda.drugs;

import java.util.ArrayList;
import java.util.List;
import com.techflow.openfda.drug.client.DrugRepository;
import com.techflow.openfda.drug.usecase.BaseUseCase;
import com.techflow.openfda.drug.usecase.ProvideSearchSuggestionsRequest;
import com.techflow.openfda.drug.usecase.ProvideSearchSuggestionsResponse;
import com.techflow.openfda.drug.usecase.ProvideSearchSuggestionsUseCase;

public class ProvideSearchSuggestionsUseCaseImpl extends BaseUseCase<ProvideSearchSuggestionsRequest, ProvideSearchSuggestionsResponse> implements ProvideSearchSuggestionsUseCase
{
	private final DrugRepository drugRepository;

	public ProvideSearchSuggestionsUseCaseImpl(DrugRepository drugRepository) {
		this.drugRepository = drugRepository;
	}

	@Override
	public void execute() throws Exception
	{
		final String term = request.getDrug();
		if (term == null || term.isEmpty()) {
			response.setSuggestions(new ArrayList<String>());
			return;
		}

		final List<String> suggestions = drugRepository.startsWith(term);
		if (suggestions.size() > 5) {
			response.setSuggestions(suggestions.subList(0, 5));
		} else {
			response.setSuggestions(suggestions);
		}
	}
}
