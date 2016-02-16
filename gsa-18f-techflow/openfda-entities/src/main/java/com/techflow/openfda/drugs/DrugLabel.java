package com.techflow.openfda.drugs;

import java.util.ArrayList;
import java.util.List;

public class DrugLabel
{
	private String purpose;

	private String dosage;

	private String active;

	private String inactive;

	private String warnings;

	private String doNotUse;

	private String stopUse;

	private String askDoctor;

	private String indicationsAndUsage;

	private String askDoctorOrPharmacist;

	private String brandName;

	private String genericName;

	private String manufacturerName;

	private String adverseReactions;

	private String productNdc;

	private final List<DrugEvent> events2 = new ArrayList<DrugEvent>();

	private String warningsAndCautions;

	public String getPurpose()
	{
		return purpose;
	}

	public void setPurpose(String purpose)
	{
		this.purpose = purpose;
	}

	public String getDosage()
	{
		return dosage;
	}

	public void setDosage(String dosage)
	{
		this.dosage = dosage;
	}

	public String getActive()
	{
		return active;
	}

	public void setActive(String active)
	{
		this.active = active;
	}

	public String getInactive()
	{
		return inactive;
	}

	public void setInactive(String inactive)
	{
		this.inactive = inactive;
	}

	public String getWarnings()
	{
		return warnings;
	}

	public void setWarnings(String warnings)
	{
		this.warnings = warnings;
	}

	public String getDoNotUse()
	{
		return doNotUse;
	}

	public void setDoNotUse(String doNotUse)
	{
		this.doNotUse = doNotUse;
	}

	public String getStopUse()
	{
		return stopUse;
	}

	public void setStopUse(String stopUse)
	{
		this.stopUse = stopUse;
	}

	public String getAskDoctor()
	{
		return askDoctor;
	}

	public void setAskDoctor(String askDoctor)
	{
		this.askDoctor = askDoctor;
	}

	public String getIndicationsAndUsage()
	{
		return indicationsAndUsage;
	}

	public void setIndicationsAndUsage(String indicationsAndUsage)
	{
		this.indicationsAndUsage = indicationsAndUsage;
	}

	public String getAskDoctorOrPharmacist()
	{
		return askDoctorOrPharmacist;
	}

	public void setAskDoctorOrPharmacist(String askDoctorOrPharmacist)
	{
		this.askDoctorOrPharmacist = askDoctorOrPharmacist;
	}

	public String getBrandName()
	{
		return brandName;
	}

	public void setBrandName(String brandName)
	{
		this.brandName = brandName;
	}

	public String getGenericName()
	{
		return genericName;
	}

	public void setGenericName(String genericName)
	{
		this.genericName = genericName;
	}

	public String getManufacturerName()
	{
		return manufacturerName;
	}

	public void setManufacturerName(String manufacturerName)
	{
		this.manufacturerName = manufacturerName;
	}

	public String getAdverseReactions()
	{
		return adverseReactions;
	}

	public void setAdverseReactions(String adverseReactions)
	{
		this.adverseReactions = adverseReactions;
	}

	public String getProductNdc()
	{
		return productNdc;
	}

	public void setProductNdc(String productNdc)
	{
		this.productNdc = productNdc;
	}

	// public void addEvent(Seriousness seriousness, int count)
	// {
	// final DrugEventSummary drugEvent = new DrugEventSummary(count, seriousness.key());
	// events.add(drugEvent);
	// }

	public DrugEventSummary summarize(Seriousness key)
	{
		final DrugEventSummary summary = new DrugEventSummary();

		for (final DrugEvent e : events2) {
			if (e.has(key)) {
				summary.increment(key);
			}
		}

		return summary;
	}

	public void addEvent(Seriousness... seriousness)
	{
		events2.add(new DrugEvent(seriousness));
	}

	public String getWarningsAndCautions()
	{
		return warningsAndCautions;
	}

	public void setWarningsAndCautions(String warningsAndCautions)
	{
		this.warningsAndCautions = warningsAndCautions;
	}
}
