package com.techflow.openfda.drug.client;

import com.google.api.client.util.Key;

public class OpenFdaDrugLabel
{
	@Key
	public OpenFdaDrugLabelResult[] results;

	public static final class OpenFdaDrugLabelResult
	{
		@Key
		public String[] ask_doctor;

		@Key
		public String[] ask_doctor_or_pharmacist;

		@Key
		public String[] dosage_and_administration;

		@Key
		public String[] do_not_use;

		@Key
		public String[] stop_use;

		@Key
		public String[] active_ingredient;

		@Key
		public String[] warnings;

		@Key
		public String[] indications_and_usage;

		@Key
		public String[] inactive_ingredient;

		@Key
		public OpenFdaLabelOpenFda openfda;

		@Key
		public String[] adverse_reactions;

		@Key
		public String[] purpose;

		@Key
		public String[] warnings_and_cautions;

		public static final class OpenFdaLabelOpenFda
		{
			@Key
			public String[] brand_name;

			@Key
			public String[] generic_name;

			@Key
			public String[] manufacturer_name;

			@Key
			public String[] product_ndc;
		}
	}
}
