# SmartFin Financial Management Project

Welcome to the SmartFin project repository! This project is a comprehensive financial management tool built in Java. It covers features such as loan calculations, expense tracking, savings goal estimation, and report generation. In addition, the project includes machine learning functionality for category prediction using Naive Bayes. The code is organized into several modules, each with its own unit tests.

## Features

- **Loan Calculator:**  
  Calculates monthly payments using an annual interest rate (converted correctly to a monthly rate) without the previous double-conversion issue.

- **Expense Tracker:**  
  Records and categorizes expenses. Supports sorting and detailed report generation.

- **Savings Goal Estimator:**  
  Estimates future savings based on initial deposits, monthly contributions, and growth over time and generates projections for different time frames (6 months, 1 year, and 5 years).

- **Report Generator (ReportGen):**  
  Combines output from the Loan Calculator, Expense Tracker, and Savings Goal Estimator to produce a comprehensive financial summary report. The report includes:
  - A header ("SmartFin Financial Summary Report")
  - Loan Summary
  - Expense Breakdown
  - Savings Projections
  - Transaction History

- **Category Prediction with Naive Bayes:**  
  This module includes:
  - **PredictCat Files:**  
    These files implement a Naive Bayes classifier to predict expense categories. They process input data and classify transactions based on learned patterns.
  - **nbTraining File:**  
    This file trains the Naive Bayes model using historical data. The trained model is then used by the PredictCat module to categorize new expenses.
    
- **Main SmartFin File:**  
  The central entry point of the application that ties together all components – from data entry and processing to report generation and category prediction.

## Testing

The project includes extensive unit tests using JUnit:
- **LoanCalculatorTest:** Validates the monthly payment calculations.
- **ReportGenTest:** Verifies that the generated reports include all required sections and handle both data-populated and empty cases.
- Additional tests cover the Naive Bayes-based category prediction and model training to ensure correct classification.

## Getting Started

### Prerequisites
- JDK 11 or higher
- Maven

### Running the Application

1. Clone the repository:
   ```bash
   git clone https://github.com/yourusername/SmartFin.git

2. Build the project:
   ```bash
   mvn clean install

3. Run the project
   ```bash
   mvn exec:java -Dexec.mainClass="your.main.SmartFin"
4. Running tests
   ```bash
   mvn test
