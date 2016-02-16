package com.techflow.openfda.drug.client;

import java.io.IOException;
import com.google.api.client.http.LowLevelHttpRequest;
import com.google.api.client.http.LowLevelHttpResponse;
import com.google.api.client.testing.http.MockHttpTransport;
import com.google.api.client.testing.http.MockLowLevelHttpRequest;
import com.google.api.client.testing.http.MockLowLevelHttpResponse;

/**
 * A MockHttpTransport that returns content.
 */
class ContentProducingMockHttpTransport extends MockHttpTransport
{
	private final String responseContent;

	private String method;

	private String url;

	private int statusCode = 200;

	public ContentProducingMockHttpTransport(String responseContent) {
		this.responseContent = responseContent;
	}

	public ContentProducingMockHttpTransport(String responseContent, int statusCode) {
		this.responseContent = responseContent;
		this.statusCode = statusCode;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public LowLevelHttpRequest buildRequest(String method, String url) throws IOException
	{
		// record the inputs
		this.method = method;
		this.url = url;

		final MockLowLevelHttpRequest request = new MockLowLevelHttpRequest() {
			@Override
			public LowLevelHttpResponse execute() throws IOException
			{
				final MockLowLevelHttpResponse response = new MockLowLevelHttpResponse();
				if (url.contains(",")) {
					response.setStatusCode(400);
					response.setContent("{" +
							"\"error\": {" +
							"  \"code\": \"BAD_REQUEST\"," +
							"  \"message\": " +
							"}" +
							"}");
				} else {
					response.setStatusCode(statusCode);
					response.setContent(responseContent);
				}
				return response;
			}
		};
		// request.
		return request;
	}

	/**
	 * The method used to build the request.
	 */
	public String getMethod()
	{
		return method;
	}

	/**
	 * The url used to build the request.
	 */
	public String getUrl()
	{
		return url;
	}
}
