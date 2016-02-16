package com.techflow.openfda;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.web.WebMvcAutoConfiguration.WebMvcAutoConfigurationAdapter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import com.techflow.openfda.drug.client.DrugRepository;
import com.techflow.openfda.drug.client.DrugRepositoryImpl;
import com.techflow.openfda.drug.client.OpenFdaGateway;
import com.techflow.openfda.drug.client.OpenFdaGatewayImpl;
import com.techflow.openfda.drug.usecase.OpenFdaUseCaseFactory;
import com.techflow.openfda.drugs.SimpleOpenFdaSpringUseCaseFactory;

/**
 * Application entry point. Run {@link OpenFdaApplication#main(String[])} to start the server.
 */
@Configuration
@ComponentScan
@EnableAutoConfiguration
public class OpenFdaApplication extends WebMvcAutoConfigurationAdapter
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
		return new OpenFdaGatewayImpl();
	}

	/**
	 * Create the DrugRepository.
	 */
	@Bean
	public DrugRepository drugRepository()
	{
		return new DrugRepositoryImpl();
	}

	@Override
	public void addViewControllers(ViewControllerRegistry registry)
	{
		super.addViewControllers(registry);
		// send users to index.html as a convenience
		registry.addViewController("/swagger/").setViewName("redirect:/swagger/index.html");
		registry.addViewController("/swagger").setViewName("redirect:/swagger/index.html");
	}

	/**
	 * Starts the server on port 8080.
	 */
	public static void main(String[] args) throws Exception
	{
		SpringApplication.run(OpenFdaApplication.class, args);
	}
}
