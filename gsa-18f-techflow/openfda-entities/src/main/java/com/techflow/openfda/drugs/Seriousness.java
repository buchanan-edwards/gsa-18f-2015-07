package com.techflow.openfda.drugs;

public enum Seriousness
{
	OTHER("seriousnessother"),

	LIFE_THREATENING("seriousnesslifethreatening"),

	HOSPITALIZATION("seriousnesshospitalization"),

	DISABLING("seriousnessdisabling"),

	DEATH("seriousnessdeath"),

	CONGENITAL_ANOMALI("seriousnesscongenitalanomali");

	private final String key;

	Seriousness(String key) {
		this.key = key;
	}

	public String key()
	{
		return key;
	}

	public static Seriousness valueFromKey(String key)
	{
		for (final Seriousness s : values()) {
			if (s.key.equals(key)) {
				return s;
			}
		}

		return null;
	}
}
