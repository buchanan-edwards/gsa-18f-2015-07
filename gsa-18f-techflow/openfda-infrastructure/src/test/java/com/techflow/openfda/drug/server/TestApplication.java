package com.techflow.openfda.drug.server;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import com.techflow.openfda.drug.client.DrugRepository;
import com.techflow.openfda.drug.client.MockOpenFdaGateway;
import com.techflow.openfda.drug.client.OpenFdaGateway;
import com.techflow.openfda.drug.usecase.OpenFdaUseCaseFactory;
import com.techflow.openfda.drugs.MockDrugRepository;
import com.techflow.openfda.drugs.SimpleOpenFdaSpringUseCaseFactory;

@Configuration
@ComponentScan
@EnableWebMvc
class TestApplication
{
	/**
	 * Create the use case factory.
	 */
	@Bean
	public OpenFdaUseCaseFactory useCaseFactory(OpenFdaGateway fdaGateway, DrugRepository drugRepository)
	{
		return new SimpleOpenFdaSpringUseCaseFactory(fdaGateway, drugRepository);
	}

	/**
	 * Create the Open FDA gateway for API calls.
	 */
	@Bean
	public OpenFdaGateway openFdaGateway()
	{
		return new MockOpenFdaGateway();
	}

	@Bean
	public DrugRepository drugRepository()
	{
		return new MockDrugRepository();
	}
}
