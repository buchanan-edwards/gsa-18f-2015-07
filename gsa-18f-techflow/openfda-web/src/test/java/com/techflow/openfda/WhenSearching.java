package com.techflow.openfda;

import net.serenitybdd.junit.runners.SerenityRunner;
import net.thucydides.core.annotations.Managed;
import net.thucydides.core.annotations.ManagedPages;
import net.thucydides.core.annotations.Steps;
import net.thucydides.core.pages.Pages;
import org.junit.Test;
import org.junit.After;
import org.junit.runner.RunWith;
import org.openqa.selenium.WebDriver;
import com.techflow.openfda.steps.SearchSteps;

@RunWith(SerenityRunner.class)
public class WhenSearching
{
	@Managed(driver = "firefox")
	public WebDriver driver;

	@ManagedPages(defaultUrl = "http://localhost:8080/index.html")
	public Pages pages;

	@Steps
	public SearchSteps user;

	@Test
	public void shouldCalculatePointsBasedOnDistance() throws Exception
	{
		user.searchesFor("aspirin");
		user.shouldSeeGraph("aspirin", driver);
		user.shouldSeeLabelFor("aspirin", driver);
		user.shouldSeeEventFor("aspirin", driver);		
		user.typeIn("aspirin");
		user.shouldSeeDropdownFor("aspirin", driver);
		
	}

	/**
	 * Pause before checking if driver completed.
	 */
	@After
	public void Ending()
	{
		try {
			Thread.sleep(1000);
			driver.close();
			Thread.sleep(3000);
		} catch (Exception b) {
			b.getMessage();
		}
	}
}
