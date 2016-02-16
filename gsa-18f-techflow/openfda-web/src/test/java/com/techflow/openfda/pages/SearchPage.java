package com.techflow.openfda.pages;

import net.serenitybdd.core.annotations.findby.FindBy;
import net.serenitybdd.core.pages.PageObject;
import net.thucydides.core.annotations.DefaultUrl;
import org.openqa.selenium.WebElement;

@DefaultUrl("http://localhost:8080/index.html")
public class SearchPage extends PageObject
{
	@FindBy(id = "input-drug_value")
	WebElement termInput;

	@FindBy(id = "button-search")
	WebElement searchButton;

	public void searchFor(String term)
	{
		openAt("http://localhost:8080/index.html");
		termInput.sendKeys(term);
		searchButton.click();
	}
	public void typeIn(String term)
	{
		openAt("http://localhost:8080/index.html");
		termInput.sendKeys(term);
	}
}
