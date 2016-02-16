package com.techflow.openfda.drug.server;

import io.swagger.annotations.ApiModelProperty;
import java.util.Map;
import com.techflow.openfda.drug.usecase.ListDrugEventsResponse;
import com.techflow.openfda.drugs.Seriousness;

public class DrugEventResponse implements ListDrugEventsResponse
{
	private int congenitalAnomali;

	private int death;

	private int disabling;

	private int hospitalization;

	private int lifeThreatening;

	private int other;

	public DrugEventResponse() {
	}

	public DrugEventResponse(Map<String, Integer> drugEvents) {
		congenitalAnomali = get(drugEvents, Seriousness.CONGENITAL_ANOMALI);
		death = get(drugEvents, Seriousness.DEATH);
		disabling = get(drugEvents, Seriousness.DISABLING);
		hospitalization = get(drugEvents, Seriousness.HOSPITALIZATION);
		lifeThreatening = get(drugEvents, Seriousness.LIFE_THREATENING);
		other = get(drugEvents, Seriousness.OTHER);
	}

	@ApiModelProperty(value = "Count of congenital anomalis")
	public int getCongenitalAnomali()
	{
		return congenitalAnomali;
	}

	public void setCongenitalAnomali(int congenitalAnomali)
	{
		this.congenitalAnomali = congenitalAnomali;
	}

	@ApiModelProperty(value = "Count of deaths")
	public int getDeath()
	{
		return death;
	}

	public void setDeath(int death)
	{
		this.death = death;
	}

	@ApiModelProperty(value = "Count of disabling events")
	public int getDisabling()
	{
		return disabling;
	}

	public void setDisabling(int disabling)
	{
		this.disabling = disabling;
	}

	@ApiModelProperty(value = "Count of hospitalizations")
	public int getHospitalization()
	{
		return hospitalization;
	}

	public void setHospitalization(int hospitalization)
	{
		this.hospitalization = hospitalization;
	}

	@ApiModelProperty(value = "Count of life threatening events")
	public int getLifeThreatening()
	{
		return lifeThreatening;
	}

	public void setLifeThreatening(int lifeThreatening)
	{
		this.lifeThreatening = lifeThreatening;
	}

	@ApiModelProperty(value = "Count of other events")
	public int getOther()
	{
		return other;
	}

	public void setOther(int other)
	{
		this.other = other;
	}

	public int getTotal()
	{
		return congenitalAnomali + death + disabling + hospitalization + lifeThreatening + other;
	}

	public void setTotal(int total)
	{
	}

	@Override
	public void setEvents(Map<String, Integer> drugEvents)
	{
		congenitalAnomali = get(drugEvents, Seriousness.CONGENITAL_ANOMALI);
		death = get(drugEvents, Seriousness.DEATH);
		disabling = get(drugEvents, Seriousness.DISABLING);
		hospitalization = get(drugEvents, Seriousness.HOSPITALIZATION);
		lifeThreatening = get(drugEvents, Seriousness.LIFE_THREATENING);
		other = get(drugEvents, Seriousness.OTHER);
	}

	private int get(Map<String, Integer> drugEffects, Seriousness seriousness)
	{
		final Integer integer = drugEffects.get(seriousness.key());
		if (integer == null) {
			return 0;
		}

		return integer;
	}

}
