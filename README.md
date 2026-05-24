# Selenium Java Cucumber Automation Framework

A comprehensive automation testing framework using **Selenium WebDriver** with **Cucumber BDD** (Behavior Driven Development), including configuration management and test data setup.

## ✨ Features

✅ **Selenium WebDriver 4.15.0** - Modern browser automation  
✅ **Cucumber BDD** - Behavior-driven development with Gherkin syntax  
✅ **Multi-browser Support** - Chrome, Firefox, Edge  
✅ **Configuration Management** - External properties file  
✅ **Test Data Management** - JSON-based test data  
✅ **Page Object Model** - Better code organization  
✅ **WebDriverManager** - Automatic driver management  
✅ **ExtentReports** - Beautiful HTML reports  
✅ **Logging** - Log4j integration  
✅ **Screenshot Capture** - On failure support  

## 🚀 Quick Start

### Prerequisites
- Java 11+
- Maven 3.6+
- Git
- Chrome/Firefox/Edge browser

### Installation

```bash
# Clone repository
git clone https://github.com/biswajit210698/testEnv.git
cd testEnv

# Install dependencies
mvn clean install

# Run tests
mvn clean test
```

## 📁 Project Structure

```
src/
├── main/
│   ├── java/com/automation/
│   │   ├── drivers/
│   │   │   └── DriverFactory.java
│   │   ├── pages/
│   │   │   └── BasePage.java
│   │   └── utils/
│   │       ├── ConfigManager.java
│   │       ├── TestDataManager.java
│   │       ├── ScreenshotUtil.java
│   │       └── WaitUtils.java
│   └── resources/
│       ├── config.properties
│       └── log4j2.properties
├── test/
│   ├── java/com/automation/
│   │   ├── hooks/Hooks.java
│   │   ├── runners/TestRunner.java
│   │   └── steps/LoginSteps.java
│   └── resources/
│       ├── features/Login.feature
│       └── testdata/testdata.json
├── pom.xml
└── README.md
```

## ⚙️ Configuration

Edit `src/main/resources/config.properties`:

```properties
browser=chrome
base.url=https://www.example.com
implicit.wait=10
explicit.wait=15
headless=false
environment=dev
```

### Command Line Override

```bash
# Specific browser
mvn clean test -Dbrowser=firefox

# Headless mode
mvn clean test -Dheadless=true

# Specific environment
mvn clean test -Denvironment=staging
```

## 🧪 Writing Tests

### 1. Feature File

```gherkin
Feature: Login Functionality
  
  Scenario: Successful login
    Given User navigates to login page
    When User enters valid credentials
    And User clicks login button
    Then User should see dashboard
```

### 2. Page Object

```java
public class LoginPage extends BasePage {
    private By username = By.id("username");
    private By password = By.id("password");
    private By loginBtn = By.id("login");
    
    public LoginPage(WebDriver driver) {
        super(driver);
    }
    
    public void enterUsername(String user) {
        sendKeys(username, user);
    }
    
    public void clickLogin() {
        click(loginBtn);
    }
}
```

### 3. Step Definitions

```java
public class LoginSteps {
    private WebDriver driver = DriverFactory.getDriver();
    private LoginPage login = new LoginPage(driver);
    
    @Given("User navigates to login page")
    public void navigateToLogin() {
        driver.navigate().to(ConfigManager.getBaseUrl());
    }
    
    @When("User enters valid credentials")
    public void enterCredentials() {
        login.enterUsername(TestDataManager
            .getTestDataValue("login", "valid_user", "username"));
    }
}
```

## 🏃 Running Tests

```bash
# All tests
mvn clean test

# Specific feature
mvn clean test -Dtest=TestRunner

# With tags
mvn clean test -Dcucumber.filter.tags="@smoke"

# Parallel execution
mvn clean test -DparallelTests=5
```

## 📊 Reports

After execution, reports are available at:
- **HTML**: `target/cucumber-reports/index.html`
- **JSON**: `target/cucumber-reports/cucumber.json`
- **JUnit**: `target/cucumber-reports/junit.xml`

## 📝 Test Data

Manage test data in `src/test/resources/testdata/testdata.json`:

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

Access in code:

```java
String username = TestDataManager
    .getTestDataValue("login", "valid_user", "username");
```

## 🔍 Debugging

### Enable Debug Logging

```bash
mvn clean test -X
```

### Capture Screenshot

```java
ScreenshotUtil.takeScreenshot(driver, "test_name");
```

### View Logs

Check `logs/automation.log`

## 📚 Best Practices

- **Page Objects**: One class per page, extend BasePage
- **Test Data**: Store in JSON, never hardcode
- **Waits**: Use explicit waits, avoid Thread.sleep()
- **Assertions**: One per test when possible
- **Logging**: Log start/end of major actions
- **Naming**: Use descriptive names for classes and methods

## 🤝 Contributing

1. Create feature branch: `git checkout -b feature/xyz`
2. Make changes and test: `mvn clean test`
3. Commit: `git commit -am 'Add feature'`
4. Push: `git push origin feature/xyz`
5. Create Pull Request

## 📋 Dependencies

- Selenium WebDriver 4.15.0
- Cucumber Java 7.14.0
- JUnit 4.13.2
- Log4j 2.20.0
- WebDriverManager 5.6.3
- ExtentReports 5.1.1
- GSON 2.10.1

## ❓ Troubleshooting

| Issue | Solution |
|-------|----------|
| WebDriver not found | Check internet, WebDriverManager downloads automatically |
| Element not found | Verify locators, increase wait time |
| Tests timeout | Increase timeout in config.properties |
| Port already in use | Change port or kill process |

## 📖 Documentation

- [Branch Protection Guide](docs/BRANCH_PROTECTION.md)
- [Setup Guide](docs/SELENIUM_SETUP_GUIDE.md)
- [Selenium Docs](https://www.selenium.dev/documentation/)
- [Cucumber Docs](https://cucumber.io/docs/)

## 📧 Support

For issues and questions:
1. Check existing issues
2. Create new issue with details
3. Contact the team

## 📄 License

MIT License - See LICENSE file

---

**Happy Testing! 🎯**