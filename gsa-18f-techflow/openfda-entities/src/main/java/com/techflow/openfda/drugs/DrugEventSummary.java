package com.techflow.openfda.drugs;

import java.util.HashMap;
import java.util.Map;

public class DrugEventSummary
{
	private int count;

	private String seriousness;

	private final Map<Seriousness, Integer> ser = new HashMap<Seriousness, Integer>();

	public DrugEventSummary() {
	}

	public DrugEventSummary(int count, String seriousness) {
		this.count = count;
		this.seriousness = seriousness;
	}

	public int getCount()
	{
		return count;
	}

	public void setCount(int count)
	{
		this.count = count;
	}

	public String getSeriousness()
	{
		return seriousness;
	}

	public void setSeriousness(String seriousness)
	{
		this.seriousness = seriousness;
	}

	public void increment(Seriousness valueOf)
	{
		int x = 0;
		if (ser.containsKey(valueOf)) {
			x = ser.get(valueOf);
		}
		x++;
		ser.put(valueOf, x);
	}

	public int getCount(Seriousness s)
	{
		return ser.get(s);
	}
}
