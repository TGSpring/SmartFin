
/**
 * Tyler Spring
 * 1/31/2025
 * 
 * Smart Personal Finance Assistant
 * This is a project to reinforce the concepts of Chapter 3.
 * 
 * 1. Loan calculator with error handling
 * 2. Expense Tracker with Categorization & Sorting
 * 3. Savings goal estimator with adjustable contributions
 * 4. Budget Category Name analyzer
 * 5. Transaction date formatting & validation
 * 6. Encrypted password storage in a file.
 * 7. Report generation.
 */
import java.util.Scanner;


import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;

public class SmartFin {

    private ArrayList<Loan> loans = new ArrayList<>();
    private SavingsGoalEst savingsGoalEst;

    /**
     * validation for doubles
     * Handles input validation from user.
     * 
     */
    public double validation(Scanner sc, String prompt, boolean isInt) {
        while (true) {
            try {
                System.out.print(prompt);
                String inputStr = sc.next().trim();

                if (isInt) {
                    if (!inputStr.matches("\\d+")) {
                        System.out.println("Please enter a valid positive integer.");
                        continue;
                    }
                    return Integer.parseInt(inputStr);
                } else {
                    if (!inputStr.matches("\\d+(\\.\\d+)?")) {
                        System.out.println("Please enter a valid positive number.");
                        continue;
                    }
                    return Double.parseDouble(inputStr);
                }
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please try again.");
            }
        }
    }

    /**
     * Analyzes a budget category name by reversing it, converting to uppercase
     * and counting the vowels and consonants.
     */

    public void analyzeCategoryName(String categoryName) {
        // Reverses string manually, yes I could have used StringBuilder, but this is a
        // better learning experience.
        if (categoryName == null || categoryName.isEmpty()) {
            System.out.println("Category name cannot be null or empty.");
            return;
        }

        String rev = "";
        for (int i = categoryName.length() - 1; i >= 0; i--) {
            rev += categoryName.charAt(i);
        }

        rev = rev.toUpperCase();

        int vowels = 0, consonants = 0;

        for (char c : rev.toCharArray()) {
            if ("AEIOU".indexOf(c) != -1) {
                vowels++;
            } else if (Character.isLetter(c)) {
                consonants++;
            }
        }

        System.out.printf("Reversed and UpperCase: %s\nVowels: %d\nConsonants: %d\n", rev, vowels, consonants);
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        LoanCalculator lc = new LoanCalculator();
        SmartFin sf = new SmartFin(); // For validation method
        DateFormatter df = new DateFormatter(); // For date formatting.
        ExpenseTracker et = new ExpenseTracker();

        boolean exit = false;

        while (!exit) {
            System.out.println("Welcome to SmartFin!");
            System.out.println("1. Loan Calculator");
            System.out.println("2. Expense Tracker");
            System.out.println("3. Savings Goal Estimator");
            System.out.println("4. Budget Category Name Analyzer");
            System.out.println("5. Transaction Date Formatting & Validation");
            System.out.println("6. Encrypted Password Storage");
            System.out.println("7. Report Generation");
            System.out.println("8. Exit");

            int choice = 0;
            boolean isValidChoice = false;

            // Validate menu choice with switch statement.
            while (!isValidChoice) {
                try {
                    System.out.print("Enter your choice: ");
                    choice = sc.nextInt();
                    if (choice < 1 || choice > 8) {
                        System.out.println("Invalid choice. Please try again.");
                        continue;
                    }
                    isValidChoice = true;
                } catch (Exception e) {
                    System.out.println("Invalid input. Please try again.");
                    sc.nextLine();
                }
            }

            // Handle input.
            switch (choice) {
                case 1:
                    // Loan calculator
                    double principal = sf.validation(sc, "Enter the loan amount: ", false);
                    double rate = sf.validation(sc, "Enter the interest rate: ", false);
                    int term = (int) sf.validation(sc, "Enter the term in years: ", true);

                    Loan loan = new Loan(principal, rate, term);
                    sf.loans.add(loan);

                    double monthlyPayment = lc.loanCalc(principal, rate, term);
                    System.out.printf("Your monthly payment is: $%.2f\n", monthlyPayment);
                    System.out.printf("Lowest payment recorded so far: $%.2f\n", lc.getLowestPayment(sf.loans));
                    break;
                case 2:
                    Map<String, String> keywordMappings = new HashMap<>();
                    keywordMappings.put("mcdonald's", "Food");
                    keywordMappings.put("netflix", "Entertainment");
                    keywordMappings.put("uber", "Transport");

                    et.addExpense(new Expense(15.0, "Netflix subscription", ""));
                    et.addExpense(new Expense(1200.0, "Monthly Rent", "Rent"));
                    et.addExpense(new Expense(50.0, "Lunch at McDonald's", ""));

                    try {
                        et.autoCategorizeExpenses(keywordMappings);
                    } catch (Exception e) {
                        System.out.println("An error occurred while categorizing expenses: " + e.getMessage());
                    }

                    System.out.println("\nExpenses sorted by amount (highest to lowest):");
                    System.out.println("------------------------------------------------");
                    for (Expense expense : et.getExpensesSortedByAmountDesc()) {
                        System.out.printf("Amount: $%.2f, Description: %s, Category: %s, Date: %s\n",
                                expense.getAmount(), expense.getDescription(), expense.getCategory(),
                                expense.getDate());
                    }

                    System.out.println("\nTop 3 most expensive expenses:");
                    System.out.println("-------------------------------");
                    for (Expense expense : et.getTopNExpenses(3)) {
                        System.out.printf("Amount: $%.2f, Description: %s, Category: %s, Date: %s\n",
                                expense.getAmount(), expense.getDescription(), expense.getCategory(),
                                expense.getDate());
                    }

                    System.out.printf("\nTotal spent on Food: $%.2f\n", et.getTotalSpentByCategory("Food"));

                    break;

                case 3:
                    double goal = sf.validation(sc, "Enter your savings goal: ", false);
                    double currentSavings = sf.validation(sc, "Enter your current savings: ", false);
                    double monthlyContribution = sf.validation(sc, "Enter your monthly contribution: ", false);
                    double monthlyIncrease = sf.validation(sc, "Enter your monthly increase in contribution: ", false);

                    sf.savingsGoalEst = new SavingsGoalEst(goal, currentSavings, monthlyContribution, monthlyIncrease);
                    sf.savingsGoalEst.printProjections();
                    break;

                case 4:
                    // Budget Category Analyzer
                    System.out.print("Enter a budget category name: ");
                    String categoryName = sc.next();
                    sf.analyzeCategoryName(categoryName);
                    break;

                case 5:
                    sc.nextLine();
                    long unixTimestamp = df.validateAndFormatDate(sc);
                    System.out.println("Unix Timestamp: " + unixTimestamp);
                    break;

                case 6:
                    PWMGUI passwordUI = new PWMGUI(sc);
                    passwordUI.showPasswordMenu();
                    break;

                case 7:
                   ReportGen rg = new ReportGen(lc, et, sf.savingsGoalEst);
                   rg.generateReport("SmartFinReport.txt");
                    break;

                case 8:
                    // Exit
                    exit = true;
                    System.out.println("Exiting the program.");
                    break;

                default:
                    System.out.println("Invalid option. Please try again.");
                    break;
            }
        }
        sc.close();
    }
}
