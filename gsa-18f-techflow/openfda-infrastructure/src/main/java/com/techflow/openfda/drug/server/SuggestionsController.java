package com.techflow.openfda.drug.server;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import springfox.documentation.annotations.ApiIgnore;
import com.techflow.openfda.drug.usecase.ProvideSearchSuggestionsRequest;
import com.techflow.openfda.drug.usecase.ProvideSearchSuggestionsUseCase;
import com.techflow.openfda.drug.usecase.OpenFdaUseCaseFactory;

@Api(value = "/api/suggestions", protocols = "http")
@Controller
@RequestMapping("/api/suggestions")
public class SuggestionsController
{
	private final OpenFdaUseCaseFactory useCaseFactory;

	@Autowired
	public SuggestionsController(OpenFdaUseCaseFactory useCaseFactory)
	{
		this.useCaseFactory = useCaseFactory;
	}

	@ApiOperation(value = "Provides autocomplete suggestions", produces = MediaType.APPLICATION_JSON_VALUE)
	@ApiResponses({
			@ApiResponse(message = "Success", code = 200, response = SuggestionsResponse.class),
			@ApiResponse(message = "Internal error", code = 500, response = ErrorResponse.class)
	})
	@RequestMapping(value = "", method = RequestMethod.GET)
	@ApiImplicitParams({
			@ApiImplicitParam(name = "name", value = "Partial name of a drug", paramType = "query", required = true, dataType = "string")
	})
	@ResponseBody
	public ResponseEntity<SuggestionsResponse> autoComplete(@ApiIgnore DescribeDrugRequest request) throws Exception
	{
		final ProvideSearchSuggestionsUseCase useCase = useCaseFactory.newAutocompleteUseCase();
		final SuggestionsResponse response = new SuggestionsResponse();
		useCase.setRequest(new ProvideSearchSuggestionsRequest() {
			@Override
			public String getDrug()
			{
				return request.getName();
			}
		});
		useCase.setResponse(response);
		useCase.execute();

		return new ResponseEntity<SuggestionsResponse>(response, HttpStatus.OK);
	}
}
