# API Test Automation with Java, TestNG, and Playwright

[![API Test Automation with Java, TestNG, and Playwright - Run Tests](https://github.com/testsmith-io/api-test-automation-java-testng-playwright/actions/workflows/maven.yml/badge.svg)](https://github.com/testsmith-io/api-test-automation-java-testng-playwright/actions/workflows/maven.yml)

This repository contains example scripts for API test automation using:
- **Java**
- **Playwright** for API interaction
- **TestNG** for test execution

## Features
- Examples of GET, POST, and Protected API requests.
- Clear demonstration of test automation practices with assertions and chaining requests.
- Fully automated CI pipeline using GitHub Actions.

## Test API
All examples in this repository are designed to work with the **Practice Software Testing API**, a publicly available API for learning and practicing software testing. You can explore the API documentation and endpoints here:  
👉 **[Practice Software Testing API](https://api.practicesoftwaretesting.com/api/documentation)** 👈

## Examples Included
1. **GET Request**: Fetch a list of brands with `GET /brands`.
2. **Login API**: Authenticate using `POST /login` with an email/password payload.
3. **Protected API Request**: Authenticate, then use a token to fetch data with `GET /invoices`.

## Prerequisites
- **Java 16+**
- **Maven**
- An IDE like **IntelliJ IDEA Community Edition**

## Setup and Run
1. Clone this repository:
   ```bash
   git clone https://github.com/testsmith-io/api-test-automation-java-testng-playwright.git
   ```
2. Navigate to the project directory:
   ```bash
   cd api-test-automation-java-testng-playwright
   ```
3. Run the tests:
   ```bash
   mvn test
   ```

## Automated Workflow
The repository includes a GitHub Actions workflow to automatically build and test the project.
