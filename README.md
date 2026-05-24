# Selenium Java Cucumber Automation Framework

This is a comprehensive automation testing framework using **Selenium WebDriver** with **Cucumber BDD** (Behavior Driven Development), including configuration management and test data setup.

## Project Structure

```
selenium-cucumber-framework/
├── src/
│   ├── main/
│   │   ├── java/com/automation/
│   │   │   ├── drivers/
│   │   │   │   └── DriverFactory.java          # WebDriver initialization
│   │   │   ├── pages/
│   │   │   │   └── BasePage.java               # Base class for page objects
│   │   │   └── utils/
│   │   │       ├── ConfigManager.java          # Configuration management
│   │   │       └── TestDataManager.java        # Test data management
│   │   └── resources/
│   │       └── config.properties               # Application configuration
│   ├── test/
│   │   ├── java/com/automation/
│   │   │   ├── hooks/
│   │   │   │   └── Hooks.java                  # Cucumber before/after hooks
│   │   │   ├── runners/
│   │   │   │   └── TestRunner.java             # Test execution runner
│   │   │   └── steps/
│   │   │       └── LoginSteps.java             # Step definitions
│   │   └── resources/
│   │       ├── features/
│   │       │   └── Login.feature               # Cucumber feature files
│   │       └── testdata/
│   │           ├── testdata.json               # JSON test data
│   │           └── testdata.xlsx               # Excel test data
├── pom.xml                                      # Maven configuration
└── README.md                                    # This file
```

## Features

✅ **Selenium WebDriver** - Browser automation  
✅ **Cucumber BDD** - Behavior-driven development with Gherkin syntax  
✅ **Configuration Management** - External properties file for easy configuration  
✅ **Test Data Management** - JSON-based test data handling  
✅ **Page Object Model** - Better code organization and maintainability  
✅ **Multi-browser Support** - Chrome, Firefox, Edge  
✅ **WebDriverManager** - Automatic driver management  
✅ **Logging** - Log4j integration  
✅ **Reporting** - HTML and JSON reports  

## Prerequisites

- Java 11 or higher
- Maven 3.6+
- Git
- IDE (IntelliJ IDEA or Eclipse)

## Installation

1. **Clone the repository**
   ```bash
   git clone https://github.com/biswajit210698/testEnv.git
   cd testEnv
   ```

2. **Install dependencies**
   ```bash
   mvn clean install
   ```

3. **Update Configuration**
   Edit `src/main/resources/config.properties`:
   ```properties
   browser=chrome
   base.url=https://www.your-app.com
   implicit.wait=10
   explicit.wait=15
   ```

4. **Add Test Data**
   Update `src/test/resources/testdata/testdata.json` with your test scenarios

## Running Tests

### Run all tests
```bash
mvn clean test
```

### Run specific feature file
```bash
mvn clean test -Dtest=TestRunner -Dcucumber.filter.tags="@smoke"
```

### Run with specific browser
```bash
mvn clean test -Dbrowser=firefox
```

### Run in headless mode
```bash
mvn clean test -Dheadless=true
```

## Configuration

### config.properties

| Property | Description | Default |
|----------|-------------|---------|
| `browser` | Browser type (chrome, firefox, edge) | chrome |
| `base.url` | Application base URL | - |
| `implicit.wait` | Implicit wait time in seconds | 10 |
| `explicit.wait` | Explicit wait time in seconds | 15 |
| `headless` | Run browser in headless mode | false |
| `screenshot.on.failure` | Capture screenshot on test failure | true |

## Test Data Management

### JSON Format
Test data is stored in `testdata.json`:
```json
{
  "login": {
    "valid_user": {
      "username": "user@example.com",
      "password": "Password@123"
    }
  }
}
```

### Accessing Test Data
```java
String username = TestDataManager.getTestDataValue("login", "valid_user", "username");
```

## Writing Tests

### 1. Create Feature File
`src/test/resources/features/MyFeature.feature`
```gherkin
Feature: My Feature
  Scenario: Test scenario
    Given User navigates to login page
    When User enters valid credentials
    Then User should see dashboard
```

### 2. Create Step Definitions
`src/test/java/com/automation/steps/MySteps.java`
```java
@Given("User navigates to login page")
public void userNavigatesToLoginPage() {
    // Step implementation
}
```

### 3. Create Page Object
`src/main/java/com/automation/pages/LoginPage.java`
```java
public class LoginPage extends BasePage {
    private By usernameField = By.id("username");
    
    public LoginPage(WebDriver driver) {
        super(driver);
    }
    
    public void enterUsername(String username) {
        sendKeys(usernameField, username);
    }
}
```

## Project Structure Best Practices

- **Pages**: Store page object models here
- **Steps**: Store step definitions
- **Hooks**: Setup and teardown logic
- **Utils**: Utility classes for configuration, test data, logging
- **Drivers**: WebDriver factory and management
- **Resources**: Feature files and test data

## Reporting

After test execution, reports are generated:
- **HTML Report**: `target/cucumber-reports/index.html`
- **JSON Report**: `target/cucumber-reports/cucumber.json`
- **JUnit Report**: `target/cucumber-reports/junit.xml`

## Logging

Logs are generated at: `./logs/automation.log`

## Maven Plugins

- **maven-compiler-plugin**: Java compilation
- **maven-surefire-plugin**: Test execution
- **maven-shade-plugin**: Dependency management

## Dependencies

- Selenium WebDriver 4.15.0
- Cucumber Java 7.14.0
- JUnit 4.13.2
- Log4j 2.20.0
- WebDriverManager 5.6.3
- GSON 2.10.1

## Troubleshooting

### Issue: WebDriver not found
**Solution**: WebDriverManager automatically handles driver downloads. Ensure internet connectivity.

### Issue: Element not found
**Solution**: Check locators and increase wait times in config.properties.

### Issue: Tests not running
**Solution**: Verify TestRunner is in src/test/java and features are in src/test/resources/features.

## Contributing

1. Create a feature branch
2. Make your changes
3. Commit and push
4. Create a pull request

## License

MIT License

## Support

For issues and questions, please create an issue in the repository.

---

**Happy Testing! 🎯**
