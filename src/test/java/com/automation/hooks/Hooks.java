package com.automation.hooks;

import io.cucumber.java.Before;
import io.cucumber.java.After;
import org.openqa.selenium.WebDriver;
import com.automation.drivers.DriverFactory;
import com.automation.utils.ConfigManager;

/**
 * Cucumber Hooks for setup and teardown
 */
public class Hooks {

    @Before
    public void setUp() {
        System.out.println("========== Test Execution Started ==========");
        System.out.println("Browser: " + ConfigManager.getBrowser());
        System.out.println("Base URL: " + ConfigManager.getBaseUrl());
        
        WebDriver driver = DriverFactory.initializeDriver();
        driver.manage().timeouts().implicitlyWait(
                java.time.Duration.ofSeconds(ConfigManager.getImplicitWait()));
        
        System.out.println("WebDriver initialized successfully");
    }

    @After
    public void tearDown() {
        WebDriver driver = DriverFactory.getDriver();
        if (driver != null) {
            DriverFactory.quitDriver();
            System.out.println("WebDriver closed successfully");
        }
        System.out.println("========== Test Execution Completed ==========");
    }
}
