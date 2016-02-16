package com.techflow.openfda.drug.client;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.lessThanOrEqualTo;
import static org.junit.Assert.assertThat;
import java.io.IOException;
import java.util.ArrayList;
import org.junit.Test;

public class DrugRepositoryImplTest
{
	@Test
	public void findBrandNames() throws IOException
	{
		final DrugRepository drugRepo = new DrugRepositoryImpl();
		final ArrayList<String> drugNames = (ArrayList<String>)drugRepo.startsWith("multi*");

		assertThat(drugNames.size(), lessThanOrEqualTo(5));
		assertThat(drugNames.get(0), equalTo("Multitrace-4"));
	}
}
