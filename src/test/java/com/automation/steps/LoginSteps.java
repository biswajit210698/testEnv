package com.automation.steps;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import io.cucumber.java.en.Then;
import org.openqa.selenium.WebDriver;
import com.automation.drivers.DriverFactory;
import com.automation.utils.ConfigManager;
import com.automation.utils.TestDataManager;

/**
 * Step definitions for Login feature
 */
public class LoginSteps {
    private WebDriver driver;

    public LoginSteps() {
        this.driver = DriverFactory.getDriver();
    }

    @Given("User navigates to login page")
    public void userNavigatesToLoginPage() {
        String loginUrl = ConfigManager.getProperty("login.url");
        driver.navigate().to(loginUrl);
        System.out.println("Navigated to login page: " + loginUrl);
    }

    @When("User enters {string} and {string}")
    public void userEntersCredentials(String username, String password) {
        System.out.println("Entering username: " + username + " and password");
        // Add your element interactions here
    }

    @When("User clicks login button")
    public void userClicksLoginButton() {
        System.out.println("Clicking login button");
        // Add click action here
    }

    @Then("User should see dashboard")
    public void userShouldSeeDashboard() {
        String dashboardUrl = ConfigManager.getProperty("dashboard.url");
        System.out.println("Verifying dashboard page");
        // Add assertion here
    }

    @When("User enters valid credentials from test data")
    public void userEntersValidCredentialsFromTestData() {
        String username = TestDataManager.getTestDataValue("login", "valid_user", "username");
        String password = TestDataManager.getTestDataValue("login", "valid_user", "password");
        System.out.println("Using test data - Username: " + username);
    }

    @Then("User should see error message")
    public void userShouldSeeErrorMessage() {
        System.out.println("Verifying error message displayed");
        // Add assertion here
    }
}