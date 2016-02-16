package com.techflow.openfda.drug.client;

import com.google.api.client.util.Key;

public class OpenFdaDrugEvent
{
	@Key
	public OpenFdaDrugEventMeta meta;

	public static final class OpenFdaDrugEventMeta
	{
		@Key
		public OpenFdaDrugEventMetaResults results;

		public static final class OpenFdaDrugEventMetaResults
		{
			@Key
			public int total;
		}
	}
}
