package com.techflow.openfda.drug.server;

import java.util.List;
import com.techflow.openfda.drug.usecase.ProvideSearchSuggestionsResponse;

public class SuggestionsResponse implements ProvideSearchSuggestionsResponse
{
	private List<String> suggestions;

	public List<String> getSuggestions()
	{
		return suggestions;
	}

	@Override
	public void setSuggestions(List<String> suggestions)
	{
		this.suggestions = suggestions;
	}
}
