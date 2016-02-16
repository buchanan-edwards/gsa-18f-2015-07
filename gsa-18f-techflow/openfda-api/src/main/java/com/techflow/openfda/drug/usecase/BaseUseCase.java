package com.techflow.openfda.drug.usecase;

/**
 * Base implementation of UseCase.
 */
public abstract class BaseUseCase<REQ, RESP> implements UseCase<REQ, RESP>
{
	protected REQ request;

	protected RESP response;

	@Override
	public REQ getRequest()
	{
		return request;
	}

	@Override
	public void setRequest(REQ request)
	{
		this.request = request;
	}

	@Override
	public RESP getResponse()
	{
		return response;
	}

	@Override
	public void setResponse(RESP response)
	{
		this.response = response;
	}
}
