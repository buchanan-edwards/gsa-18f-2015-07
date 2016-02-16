package com.techflow.openfda.drug.server;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import org.springframework.mock.web.MockHttpServletResponse;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class SimpleObjectMapper
{

	static <T> T map(final String json, Class<T> clazz) throws IOException, JsonParseException, JsonMappingException
	{
		final ObjectMapper mapper = new ObjectMapper();
		final T object = mapper.readValue(json, clazz);
		return object;
	}

	static <T> T mapResponse(final MockHttpServletResponse response, Class<T> clazz) throws UnsupportedEncodingException, IOException, JsonParseException, JsonMappingException
	{
		final String json = response.getContentAsString();
		final T object = map(json, clazz);
		return object;
	}

}
