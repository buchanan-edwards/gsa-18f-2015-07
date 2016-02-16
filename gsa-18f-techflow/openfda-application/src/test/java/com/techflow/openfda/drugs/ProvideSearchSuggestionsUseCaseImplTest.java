package com.techflow.openfda.drugs;

import static org.hamcrest.Matchers.contains;
import static org.hamcrest.Matchers.containsInAnyOrder;
import static org.hamcrest.Matchers.empty;
import static org.hamcrest.Matchers.hasSize;
import static org.junit.Assert.assertThat;
import java.util.List;
import org.junit.Test;
import com.techflow.openfda.drug.usecase.ProvideSearchSuggestionsRequest;
import com.techflow.openfda.drug.usecase.ProvideSearchSuggestionsResponse;

public class ProvideSearchSuggestionsUseCaseImplTest
{
	@Test
	public void shouldFindAspirin() throws Exception
	{
		final ProvideSearchSuggestionsUseCaseImpl useCase = new ProvideSearchSuggestionsUseCaseImpl(new MockDrugRepository());

		useCase.setRequest(new ProvideSearchSuggestionsRequest() {

			@Override
			public String getDrug()
			{
				return "asp";
			}

		});
		final SuggestionsResponseSpy response = new SuggestionsResponseSpy();
		useCase.setResponse(response);
		useCase.execute();

		assertThat(response.getResults(), contains("aspirin"));
	}

	@Test
	public void shouldFindTylenol() throws Exception
	{
		final ProvideSearchSuggestionsUseCaseImpl useCase = new ProvideSearchSuggestionsUseCaseImpl(new MockDrugRepository());

		useCase.setRequest(new ProvideSearchSuggestionsRequest() {

			@Override
			public String getDrug()
			{
				return "TY";
			}

		});
		final SuggestionsResponseSpy response = new SuggestionsResponseSpy();
		useCase.setResponse(response);
		useCase.execute();

		assertThat(response.getResults(), containsInAnyOrder("tylenol", "tylenol pm"));
	}

	@Test
	public void dontSearchForBlank() throws Exception
	{
		final ProvideSearchSuggestionsUseCaseImpl useCase = new ProvideSearchSuggestionsUseCaseImpl(new MockDrugRepository());

		useCase.setRequest(new ProvideSearchSuggestionsRequest() {
			@Override
			public String getDrug()
			{
				return "";
			}
		});
		final SuggestionsResponseSpy response = new SuggestionsResponseSpy();
		useCase.setResponse(response);
		useCase.execute();

		assertThat(response.getResults(), empty());
	}

	@Test
	public void dontSearchForNull() throws Exception
	{
		final ProvideSearchSuggestionsUseCaseImpl useCase = new ProvideSearchSuggestionsUseCaseImpl(new MockDrugRepository());

		useCase.setRequest(new ProvideSearchSuggestionsRequest() {
			@Override
			public String getDrug()
			{
				return null;
			}
		});
		final SuggestionsResponseSpy response = new SuggestionsResponseSpy();
		useCase.setResponse(response);
		useCase.execute();

		assertThat(response.getResults(), empty());
	}

	@Test
	public void limitSuggestionsTo5() throws Exception
	{
		final ProvideSearchSuggestionsUseCaseImpl useCase = new ProvideSearchSuggestionsUseCaseImpl(new MockDrugRepository());

		useCase.setRequest(new ProvideSearchSuggestionsRequest() {
			@Override
			public String getDrug()
			{
				return "x";
			}
		});
		final SuggestionsResponseSpy response = new SuggestionsResponseSpy();
		useCase.setResponse(response);
		useCase.execute();

		assertThat(response.getResults(), hasSize(5));
	}

	private final class SuggestionsResponseSpy implements ProvideSearchSuggestionsResponse
	{
		private List<String> results;

		@Override
		public void setSuggestions(List<String> results)
		{
			this.results = results;
		}

		public List<String> getResults()
		{
			return results;
		}
	}
}
