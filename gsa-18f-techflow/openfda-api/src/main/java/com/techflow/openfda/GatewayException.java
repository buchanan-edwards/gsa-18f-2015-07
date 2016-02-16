package com.techflow.openfda;

@SuppressWarnings("serial")
public class GatewayException extends Exception
{
	public GatewayException(Exception e) {
		super(e);
	}

	public GatewayException() {
		super();
	}
}
