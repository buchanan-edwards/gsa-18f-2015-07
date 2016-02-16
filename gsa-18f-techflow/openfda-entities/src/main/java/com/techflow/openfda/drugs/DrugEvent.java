package com.techflow.openfda.drugs;

import java.util.Arrays;
import java.util.List;

public class DrugEvent
{
	private final List<Seriousness> seriousnessList;

	public DrugEvent(Seriousness[] seriousnessArray) {
		this.seriousnessList = Arrays.asList(seriousnessArray);
	}

	public boolean has(Seriousness seriousness)
	{
		return seriousnessList.contains(seriousness);
	}
}
