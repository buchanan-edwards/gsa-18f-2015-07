package com.techflow.openfda.drug.server;

import io.swagger.annotations.ApiParam;
import com.techflow.openfda.drug.usecase.FindDrugRequest;

public class DescribeDrugRequest implements FindDrugRequest
{
	private String name;

	@Override
	@ApiParam(value = "The name of a drug")
	public String getName()
	{
		return name;
	}

	public void setName(String name)
	{
		this.name = name;
	}
}
