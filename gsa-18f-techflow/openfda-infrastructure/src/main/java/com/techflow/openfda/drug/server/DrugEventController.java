package com.techflow.openfda.drug.server;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import com.techflow.openfda.GatewayException;
import com.techflow.openfda.drug.usecase.ListDrugEventsRequest;
import com.techflow.openfda.drug.usecase.ListDrugEventsUseCase;
import com.techflow.openfda.drug.usecase.OpenFdaUseCaseFactory;

/**
 * Spring MVC controller for drug events REST API.
 */
@Api(value = "/api/events", protocols = "http")
@Controller
@RequestMapping("/api/events")
public class DrugEventController
{
	private final OpenFdaUseCaseFactory useCaseFactory;

	@Autowired
	public DrugEventController(OpenFdaUseCaseFactory useCaseFactory) {
		this.useCaseFactory = useCaseFactory;
	}

	@ApiOperation(value = "Events for a drug", produces = MediaType.APPLICATION_JSON_VALUE, response = DrugEventResponse.class)
	@RequestMapping(value = "", method = RequestMethod.GET)
	@ResponseBody
	public ResponseEntity<DrugEventResponse> getDrugEvents(@RequestParam("productNdc") String productNdc) throws GatewayException
	{
		final ListDrugEventsUseCase useCase = useCaseFactory.newListDrugEventsUseCase();
		useCase.setRequest(new ListDrugEventsRequest() {
			@Override
			public String getProductNdc()
			{
				return productNdc;
			}
		});
		final DrugEventResponse response = new DrugEventResponse();
		useCase.setResponse(response);
		useCase.execute();

		return new ResponseEntity<DrugEventResponse>(response, HttpStatus.OK);
	}
}
