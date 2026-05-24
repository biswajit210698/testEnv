package com.automation.drivers;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import com.automation.utils.ConfigManager;

/**
 * Driver Factory to initialize WebDriver instances
 */
public class DriverFactory {
    private static ThreadLocal<WebDriver> driver = new ThreadLocal<>();

    /**
     * Initialize WebDriver based on browser type from config
     */
    public static WebDriver initializeDriver() {
        String browserName = ConfigManager.getBrowser().toLowerCase();
        WebDriver webDriver = null;

        switch (browserName) {
            case "chrome":
                webDriver = initializeChromeDriver();
                break;
            case "firefox":
                webDriver = initializeFirefoxDriver();
                break;
            case "edge":
                webDriver = initializeEdgeDriver();
                break;
            default:
                throw new IllegalArgumentException("Browser: " + browserName + " is not supported");
        }

        driver.set(webDriver);
        return webDriver;
    }

    /**
     * Initialize Chrome Driver
     */
    private static WebDriver initializeChromeDriver() {
        WebDriverManager.chromedriver().setup();
        ChromeOptions options = new ChromeOptions();
        
        if (ConfigManager.isHeadless()) {
            options.addArguments("--headless=new");
        }
        
        options.addArguments("--start-maximized");
        options.addArguments("--disable-notifications");
        options.addArguments("--disable-popup-blocking");
        options.addArguments("--disable-blink-features=AutomationControlled");
        options.setAcceptInsecureCerts(true);

        return new ChromeDriver(options);
    }

    /**
     * Initialize Firefox Driver
     */
    private static WebDriver initializeFirefoxDriver() {
        WebDriverManager.firefoxdriver().setup();
        FirefoxOptions options = new FirefoxOptions();
        
        if (ConfigManager.isHeadless()) {
            options.addArguments("--headless");
        }
        
        options.setAcceptInsecureCerts(true);
        return new FirefoxDriver(options);
    }

    /**
     * Initialize Edge Driver
     */
    private static WebDriver initializeEdgeDriver() {
        WebDriverManager.edgedriver().setup();
        EdgeOptions options = new EdgeOptions();
        
        if (ConfigManager.isHeadless()) {
            options.addArguments("--headless");
        }
        
        options.addArguments("--start-maximized");
        options.setAcceptInsecureCerts(true);
        return new EdgeDriver(options);
    }

    /**
     * Get current driver instance
     */
    public static WebDriver getDriver() {
        return driver.get();
    }

    /**
     * Quit driver and remove from ThreadLocal
     */
    public static void quitDriver() {
        WebDriver webDriver = driver.get();
        if (webDriver != null) {
            webDriver.quit();
            driver.remove();
        }
    }
}