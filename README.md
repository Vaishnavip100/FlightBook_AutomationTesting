# 🧪 FlightBook – Selenium Java Automation Framework

## 📌 Project Overview

FlightBook is a Selenium-based automation framework developed using Java to test a demo flight booking application (**BlazeDemo**).

The framework is designed using the Page Object Model (POM) to ensure scalability, maintainability, and reusability.

---

## 🚀 Features

* Page Object Model (POM)
* Data-driven testing using TestNG DataProvider
* Parallel execution using ThreadLocal WebDriver
* Retry mechanism for failed tests
* Screenshot capture on failure
* Extent Reports for execution results
* Config-driven setup (browser, URL, timeout)

---

## ⚙️ Tech Stack

* Java
* Selenium WebDriver
* TestNG
* Maven
* WebDriverManager
* Extent Reports

---

🔄 Project Workflow

<img width="1367" height="3439" alt="Project16Workflow drawio" src="https://github.com/user-attachments/assets/f1b38aee-0260-402a-8bed-e94d4b7ab3f0" />

---

## 📁 Project Structure

```plaintext
FlightBook
│
├── src
│   ├── main
│   │   └── java
│   │       ├── base
│   │       │   └── BasePage.java              → Base class providing common Selenium actions (waits, click, type, etc.)
│   │       │
│   │       ├── pages
│   │       │   ├── HomePage.java              → Handles flight search (select cities, trigger search)
│   │       │   ├── FlightListPage.java        → Handles flight results (verify list, select flight dynamically)
│   │       │   ├── PurchasePage.java          → Handles passenger form (enter details, submit booking)
│   │       │   └── ConfirmationPage.java      → Handles confirmation page (verify success message & booking ID)
│   │       │
│   │       └── utils
│   │           ├── ConfigReader.java          → Reads config.properties (browser, base URL, timeout)
│   │           ├── ExtentManager.java         → Configures and initializes Extent Report
│   │           └── ScreenshotUtil.java        → Captures screenshots on test failure
│   │
│   └── test
│       ├── java
│       │   ├── base
│       │   │   └── BaseTest.java              → Test setup & teardown (WebDriver initialization, config usage)
│       │   │
│       │   ├── listeners
│       │   │   ├── TestListener.java          → Handles reporting & screenshot capture using TestNG listener
│       │   │   └── RetryListener.java         → Applies retry logic globally using TestNG annotation transformer
│       │   │
│       │   ├── tests
│       │   │   ├── FlightSearchTest.java      → Flight search validation tests
│       │   │   ├── FlightSelectionTest.java   → Flight selection & navigation tests
│       │   │   ├── PurchaseTest.java          → Purchase flow & confirmation validation
│       │   │   ├── DifferentRouteSearchTest.java → Multiple route testing using DataProvider
│       │   │   └── FormValidationTest.java    → Form validation & edge case handling
│       │   │
│       │   └── utils
│       │       └── RetryAnalyzer.java         → Implements retry mechanism for failed tests
│       │
│       └── resources
│           └── config.properties              → External configuration (browser, base URL, timeout)
│
├── reports
│   └── ExtentReport.html                     → Generated HTML report after execution
│
├── screenshots
│   └── *.png                                → Screenshots captured automatically on failures
│
├── test-output                              → Default TestNG reports folder
├── target                                   → Maven build output directory
├── pom.xml                                  → Maven dependencies and build configuration
└── testng.xml                               → TestNG suite configuration (parallel execution, listeners)
```

---

# 🧪 Test Modules

---

## 1. Flight Search

* Select valid departure and destination cities
* Verify flight listing page loads successfully
* Validate each row contains:
  
  * Airline
  * Flight number
  * Departure
  * Arrival
* Verify selecting the same city still loads results

---

## 2. Flight Selection

* Click "Choose This Flight" for a listed flight
* Verify navigation to purchase page
* Validate flight details are displayed
* Verify selected flight price is shown on purchase page

---

## 3. Purchase Form

* Fill passenger and payment details
* Submit purchase form
* Verify confirmation page is displayed
* Validate booking ID is generated
* Verify thank-you message is present

---

## 4. Different Route Search

* Execute multiple routes using DataProvider
* Verify results load for each route
* Validate flight count behavior across routes
* Verify page title correctness for each search

---

## 5. Form Validations

* Submit form with empty fields
* Submit with invalid credit card format
* Verify validation behavior (as per application)

---

# ⚠️ Observations (Important Findings)

The following expected validations are **NOT implemented in BlazeDemo**:

* No validation when Name field is empty
* No rejection for invalid credit card input
* No visual highlighting of mandatory fields
* Same number of flights returned for all routes
* Flight price differs between listing page and purchase page
* No common cities exist between departure and destination dropdowns, hence same-city selection scenario is not applicable in the application.

👉 Therefore, tests are written based on **actual application behavior**, not assumed real-world validations.

---

# ⚙️ Configuration

## 📄 config.properties

```properties
browser=chrome
baseUrl=https://blazedemo.com/
timeout=10
```

---

# ▶️ How to Run Tests

### Using Maven

```bash
mvn test
```

### Using TestNG

Right-click `testng.xml` → Run As → TestNG Suite



---

# 📊 Reports & Screenshots

* Extent Report:

```
/reports/ExtentReport.html
```

* Screenshots:

```
/screenshots/
```

---

## 👩‍💻 Author

Vaishnavi

---

## 🏁 Conclusion

This project demonstrates a scalable Selenium automation framework using POM, TestNG, and modern practices like parallel execution, retry, and reporting.
