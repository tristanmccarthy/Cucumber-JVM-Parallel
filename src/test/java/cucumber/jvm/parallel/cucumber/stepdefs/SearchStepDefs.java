package cucumber.jvm.parallel.cucumber.stepdefs;

import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import cucumber.jvm.parallel.cucumber.SharedDriver;
import cucumber.jvm.parallel.pageobjects.SearchPageObject;
import cucumber.runtime.PendingException;
import org.openqa.selenium.WebDriver;

import java.util.List;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertEquals;

public class SearchStepDefs {
    private SearchPageObject searchPage;
    private WebDriver webDriver;

    public SearchStepDefs(SharedDriver webDriver) {
        this.webDriver = webDriver;
        searchPage = new SearchPageObject(webDriver);
    }

    @Given("^I am on the website '(.+)'$")
    public void I_am_on_the_website(String url) throws Throwable {
        webDriver.get(url);
    }

    @When("^I submit the search term '(.+)'$")
    public void I_submit_the_search_term(String searchTerm) throws Throwable {
        searchPage.enterSearchTerm(searchTerm);
        searchPage.submitSearch();
    }

    @When("^accept the first search result$")
    public void accept_the_first_search_result() throws Throwable {
        searchPage.acceptSearchResult(0);

        //wait up to 5 seconds for redirect to complete
        for(int i=0; i<5; i++) {
            if(!webDriver.getCurrentUrl().contains("google")) { break; }
            Thread.sleep(1000);
        }
    }

    @Then("^I should be on the page '(.+)'$")
    public void I_should_be_on_the_page(String url) throws Throwable {
        assertEquals(url, webDriver.getCurrentUrl());
    }
}
