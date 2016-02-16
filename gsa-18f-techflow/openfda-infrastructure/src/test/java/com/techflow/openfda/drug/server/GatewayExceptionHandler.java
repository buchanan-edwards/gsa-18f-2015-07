package com.techflow.openfda.drug.server;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import com.techflow.openfda.GatewayException;

/**
 * Return HTTP status code 500 to client when a GatewayException occurs.
 */
@ControllerAdvice
public class GatewayExceptionHandler
{
	@ExceptionHandler(GatewayException.class)
	@ResponseBody
	public ResponseEntity<ErrorResponse> handleGatewayException()
	{
		final ErrorResponse errorResponse = new ErrorResponse("Error communicating with OpenFDA API");

		return new ResponseEntity<ErrorResponse>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
	}
}
