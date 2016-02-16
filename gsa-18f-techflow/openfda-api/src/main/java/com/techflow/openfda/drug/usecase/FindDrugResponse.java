package com.techflow.openfda.drug.usecase;

public interface FindDrugResponse
{
	void setBrandName(String brandName);

	void setGenericName(String genericName);

	void setPurpose(String purpose);

	void setDosage(String dosage);

	void setActive(String active);

	void setInactive(String inactive);

	void setWarnings(String warnings);

	void setDoNotUse(String doNotUse);

	void setStopUse(String stopUse);

	void setAskDoctor(String askDoctor);

	void setIndicationsAndUsage(String indicationsAndUsage);

	void setNotFound(boolean notFound);

	void setAskDoctorOrPharmacist(String askDoctorOrPharmacist);

	void setAdverseReactions(String adverseReactions);

	void setManufacturerName(String manufacturerName);

	void setProductNdc(String productNdc);

	void setWarningsAndCautions(String warningsAndCautions);
}
