package com.techflow.openfda.drug.client;

import java.util.ArrayList;
import java.util.List;
import com.techflow.openfda.GatewayException;
import com.techflow.openfda.drugs.DrugEventSummary;
import com.techflow.openfda.drugs.DrugLabel;
import com.techflow.openfda.drugs.Seriousness;

/**
 * Mock implementation of OpenFdaGateway.
 */
public class MockOpenFdaGateway implements OpenFdaGateway
{
	public static final String ASPIRIN_NDC = "63941-440";

	public GatewayException exception;

	private final List<DrugLabel> drugs = new ArrayList<DrugLabel>();

	public MockOpenFdaGateway()
	{
		final DrugLabel drug = new DrugLabel();
		drug.setProductNdc(ASPIRIN_NDC);
		drug.setIndicationsAndUsage("indications");
		drug.setBrandName("brand name");
		drug.setGenericName("Aspirin");
		drug.setPurpose("purpose");
		drug.setActive("active");
		drug.setInactive("inactive");
		drug.setWarnings("warnings");
		drug.setDoNotUse("do not use");
		drug.setAskDoctor("ask doctor");
		drug.setAskDoctorOrPharmacist("ask doctor or pharmacist");
		drug.setDosage("dosage");
		drug.setStopUse("stop use");
		drug.setAdverseReactions("adverse reactions");
		drug.setManufacturerName("manufacturer name");
		drug.setWarningsAndCautions("warnings and cautions");
		drug.addEvent(Seriousness.CONGENITAL_ANOMALI, Seriousness.DEATH, Seriousness.DISABLING, Seriousness.HOSPITALIZATION, Seriousness.LIFE_THREATENING, Seriousness.OTHER);
		drug.addEvent(Seriousness.DEATH, Seriousness.OTHER);

		drugs.add(drug);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public DrugLabel getLabel(String label) throws GatewayException
	{
		if (exception != null) {
			throw exception;
		}

		if (label == null) {
			throw new GatewayException();
		}

		return findByLabel(label);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public DrugEventSummary getEvents(String productNdc, Seriousness s) throws GatewayException
	{
		if (exception != null) {
			throw exception;
		}

		final DrugLabel drug = findByProductNdc(productNdc);
		final DrugEventSummary e = drug.summarize(s);
		e.setSeriousness(s.key());
		e.setCount(e.getCount(s));

		return e;
	}

	private DrugLabel findByProductNdc(String productNdc)
	{
		for (final DrugLabel drug : drugs) {
			if (drug.getProductNdc().equals(productNdc)) {
				return drug;
			}
		}

		return null;
	}

	private DrugLabel findByLabel(String label)
	{
		for (final DrugLabel drug : drugs) {
			if (drug.getGenericName().equalsIgnoreCase(label)) {
				return drug;
			}
		}

		return null;
	}
}
