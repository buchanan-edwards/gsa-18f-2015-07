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
import com.techflow.openfda.GatewayException;
import com.techflow.openfda.drug.usecase.FindDrugUseCase;
import com.techflow.openfda.drug.usecase.OpenFdaUseCaseFactory;

@Api(value = "/api/drugs", protocols = "http")
@Controller
@RequestMapping("/api/drugs")
public class DrugController
{
	private final OpenFdaUseCaseFactory useCaseFactory;

	@Autowired
	public DrugController(OpenFdaUseCaseFactory useCaseFactory)
	{
		this.useCaseFactory = useCaseFactory;
	}

	@ApiOperation(value = "Describe a drug", produces = MediaType.APPLICATION_JSON_VALUE)
	@ApiResponses({
			@ApiResponse(message = "Success", code = 200, response = DescribeDrugResponse.class),
			@ApiResponse(message = "Drug not found", code = 404),
			@ApiResponse(message = "Internal error", code = 500, response = ErrorResponse.class)
	})
	@RequestMapping(value = "", method = RequestMethod.GET)
	@ApiImplicitParams({ @ApiImplicitParam(name = "name", value = "The name of a drug", paramType = "query", required = false, dataType = "string")
	})
	@ResponseBody
	public ResponseEntity<DescribeDrugResponse> describeDrug(@ApiIgnore DescribeDrugRequest request) throws GatewayException
	{
		final FindDrugUseCase useCase = useCaseFactory.newFindDrugUseCase();
		final DescribeDrugResponse response = new DescribeDrugResponse();
		useCase.setRequest(request);
		useCase.setResponse(response);
		useCase.execute();

		if (response.isNotFound()) {
			return new ResponseEntity<DescribeDrugResponse>(HttpStatus.NOT_FOUND);
		}

		final ResponseEntity<DescribeDrugResponse> responseEntity = new ResponseEntity<DescribeDrugResponse>(response, HttpStatus.OK);
		return responseEntity;
	}
}
