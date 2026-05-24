# Selenium Java Testing Framework - Complete Setup Guide

## Overview
This guide covers the complete setup and usage of the Selenium Java Cucumber automation framework.

## System Requirements
- Java 11 or higher
- Maven 3.6+
- Git
- IDE: IntelliJ IDEA or Eclipse (recommended)
- Browsers: Chrome, Firefox, or Edge

## Installation Steps

### 1. Clone Repository
```bash
git clone https://github.com/biswajit210698/testEnv.git
cd testEnv
```

### 2. Install Dependencies
```bash
mvn clean install
```

### 3. Verify Installation
```bash
mvn --version
java -version
```

## Project Structure
```
project/
├── src/main/java/com/automation/
│   ├── drivers/
│   │   └── DriverFactory.java          # Browser initialization
│   ├── pages/
│   │   └── BasePage.java               # Base page object
│   └── utils/
│       ├── ConfigManager.java          # Config handling
│       ├── TestDataManager.java        # Test data handling
│       ├── ScreenshotUtil.java         # Screenshot capture
│       └── WaitUtils.java              # Wait handling
├── src/main/resources/
│   ├── config.properties               # Configuration
│   └── log4j2.properties              # Logging config
├── src/test/java/com/automation/
│   ├── hooks/Hooks.java               # Before/After hooks
│   ├── runners/TestRunner.java        # Test execution
│   └── steps/LoginSteps.java          # Step definitions
├── src/test/resources/
│   ├── features/                      # Feature files
│   └── testdata/                      # Test data
├── pom.xml                            # Maven config
└── README.md                          # Documentation
```

## Configuration

### config.properties
Edit `src/main/resources/config.properties`:

```properties
browser=chrome              # chrome, firefox, edge
base.url=https://app.com    # Your application URL
implicit.wait=10            # Implicit wait in seconds
explicit.wait=15           # Explicit wait in seconds
headless=false              # Run in headless mode
environment=dev             # dev, staging, prod
```

### Command Line Overrides
```bash
# Run with specific browser
mvn clean test -Dbrowser=firefox

# Run in headless mode
mvn clean test -Dheadless=true

# Set environment
mvn clean test -Denvironment=staging
```

## Creating Tests

### Step 1: Create Feature File
`src/test/resources/features/MyFeature.feature`
```gherkin
Feature: My Feature Description
  
  Scenario: Scenario 1
    Given User navigates to login page
    When User enters credentials
    Then User should see dashboard
```

### Step 2: Create Page Object
`src/main/java/com/automation/pages/LoginPage.java`
```java
public class LoginPage extends BasePage {
    private By usernameField = By.id("username");
    private By passwordField = By.id("password");
    private By loginButton = By.id("login");
    
    public LoginPage(WebDriver driver) {
        super(driver);
    }
    
    public void enterUsername(String username) {
        sendKeys(usernameField, username);
    }
    
    public void enterPassword(String password) {
        sendKeys(passwordField, password);
    }
    
    public void clickLogin() {
        click(loginButton);
    }
}
```

### Step 3: Create Step Definitions
`src/test/java/com/automation/steps/MySteps.java`
```java
public class MySteps {
    private WebDriver driver = DriverFactory.getDriver();
    private LoginPage loginPage = new LoginPage(driver);
    
    @Given("User navigates to login page")
    public void navigateToLoginPage() {
        driver.navigate().to(ConfigManager.getBaseUrl());
    }
    
    @When("User enters credentials")
    public void enterCredentials() {
        loginPage.enterUsername("test@example.com");
        loginPage.enterPassword("password");
        loginPage.clickLogin();
    }
}
```

## Running Tests

### Run All Tests
```bash
mvn clean test
```

### Run Specific Feature
```bash
mvn clean test -Dtest=TestRunner
```

### Run with Tags
```bash
# Run only @smoke tests
mvn clean test -Dcucumber.filter.tags="@smoke"

# Run @regression but not @skip
mvn clean test -Dcucumber.filter.tags="@regression and not @skip"
```

## Reports

After test execution, reports are available at:
- **HTML Report**: `target/cucumber-reports/index.html`
- **JSON Report**: `target/cucumber-reports/cucumber.json`
- **JUnit Report**: `target/cucumber-reports/junit.xml`

## Best Practices

### Page Object Model
- One class per page/component
- Locators as private variables
- Methods represent user actions
- Extend BasePage for common methods

### Test Data
- Store in testdata.json
- Use TestDataManager to access
- Keep sensitive data out of code

### Logging
- Use appropriate log levels
- Log at start/end of major actions
- Include important values for debugging

### Wait Strategies
- Use explicit waits (preferred)
- Set reasonable timeouts (10-20 seconds)
- Use WebDriverWait for visibility/clickability

### Assertions
- Use meaningful assertion messages
- Assert one thing per test when possible
- Include actual vs expected values

## Troubleshooting

### WebDriver Not Found
```
Solution: WebDriverManager handles downloads automatically.
Ensure internet connectivity and check logs.
```

### Element Not Found
```
Solution: 
- Verify locators are correct
- Increase explicit wait time
- Check if element is on the page
- Look for iframe/shadow DOM issues
```

### Tests Timing Out
```
Solution:
- Increase timeout in config.properties
- Check internet connectivity
- Verify application is responding
- Look for script errors in console
```

## Debugging Tips

### Enable Debug Mode
```bash
mvn clean test -X
```

### View Console Logs
Check console output for:
- WebDriver initialization
- Test data loading
- Navigation steps
- Assert failures

### Take Screenshots
```java
ScreenshotUtil.takeScreenshot(driver, "test_name");
```

## CI/CD Integration

### Jenkins Pipeline
```groovy
stage('Test') {
    steps {
        sh 'mvn clean test'
    }
}

stage('Reports') {
    steps {
        publishHTML([
            reportDir: 'target/cucumber-reports',
            reportFiles: 'index.html',
            reportName: 'Cucumber Report'
        ])
    }
}
```

### GitHub Actions
```yaml
name: Tests
on: [push, pull_request]
jobs:
  test:
    runs-on: ubuntu-latest
    steps:
      - uses: actions/checkout@v2
      - uses: actions/setup-java@v2
        with:
          java-version: '11'
      - run: mvn clean test
```

## Support & Resources

- **Selenium Docs**: https://www.selenium.dev/documentation/
- **Cucumber Docs**: https://cucumber.io/docs/
- **WebDriverManager**: https://github.com/bonigarcia/webdrivermanager

---

**Happy Testing! 🎯**