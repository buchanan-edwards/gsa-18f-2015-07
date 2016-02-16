package com.techflow.openfda.drug.usecase;

/**
 * Interface for command pattern use cases.
 */
public interface UseCase<REQ, RESP>
{
	REQ getRequest();

	void setRequest(REQ request);

	RESP getResponse();

	void setResponse(RESP response);

	/**
	 * Execute the use case.
	 */
	void execute() throws Exception;
}
