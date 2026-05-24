package com.automation.utils;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Screenshot Utility for capturing and saving screenshots
 */
public class ScreenshotUtil {
    private static final String SCREENSHOT_PATH = "./screenshots/";
    private static final DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("yyyy-MM-dd_HH-mm-ss");

    static {
        // Create screenshots directory if it doesn't exist
        new File(SCREENSHOT_PATH).mkdirs();
    }

    /**
     * Take screenshot and save to file
     */
    public static String takeScreenshot(WebDriver driver, String screenshotName) {
        try {
            String timestamp = LocalDateTime.now().format(dateFormat);
            String fileName = screenshotName + "_" + timestamp + ".png";
            String filePath = SCREENSHOT_PATH + fileName;

            File srcFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
            File destFile = new File(filePath);
            FileUtils.copyFile(srcFile, destFile);

            System.out.println("Screenshot saved: " + filePath);
            return filePath;
        } catch (IOException e) {
            System.err.println("Failed to capture screenshot: " + e.getMessage());
            return null;
        }
    }

    /**
     * Take screenshot on test failure
     */
    public static String captureScreenshotOnFailure(WebDriver driver, String testName) {
        return takeScreenshot(driver, "FAILURE_" + testName);
    }
}