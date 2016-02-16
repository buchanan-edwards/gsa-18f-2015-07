package com.techflow.openfda.drugs;

public class MockDrugRepository extends NaiveInMemoryDrugRepository
{
	public MockDrugRepository() {
		add("Aspirin");
		add("Tylenol");
		add("Tylenol PM");

		add("Xalatan");
		add("Xalkori");
		add("Xanax");
		add("Xanax XR");
		add("Xarelto");
		add("Xeljanz");
		add("Xeloda");
		add("Xenazine");
	}
}
