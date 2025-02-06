package basicbudgettrackerv2;

import java.util.*;

public class BasicBudgetTrackerV2 {
	
	static ArrayList<String> incomeCategories = new ArrayList<>(); //category only for income
	static ArrayList<String> expenseCategories = new ArrayList<>(); //category only for expense
	static ArrayList<Double> incomeAmounts = new ArrayList<>(); //array for amounts per category
	static ArrayList<Double> expenseAmounts = new ArrayList<>(); //array for amounts per category
	
	static double totalIncome = 0; //adds all amounts from income array
	static double totalExpense = 0; //adds all amounts from expense array
	static double finalBalance = 0; //stores balance after subtracting total expense from total income
	
	final static int QUARTERS_OF_YEAR = 4; //quarters of a year
	final static int NUM_TO_CONVERT_PERCENTAGE = 100; //helps convert percentage to decimal
	
	static Scanner scnr = new Scanner(System.in); //scanner for user input
	
	public static void main(String[] args) {	
		while(true) { //loop until user wants to exit program
			//display menu
			showMainMenu();
			int choice = (int) getValidInput(2); //pass 1 for integer input
			
			switch(choice) {
				case 1: 
					handleIncome();
					break;
				case 2:
					handleExpense();
					break;
				case 3:
					handleSavings();
					break;
				case 4:
					System.out.println("Exiting program... Goodbye!");
					scnr.close();
					return;
				default:
					System.out.println("Invalid choice");
			}
		}
    }
	
	//display main menu
	public static void showMainMenu() {
        System.out.println("\n--- Budget Tracker ---");
        System.out.println("1. Income options");
        System.out.println("2. Expense Options");
        System.out.println("3. Saving options");
        System.out.println("4. Exit");
        System.out.print("Enter your choice: ");
	}
	
	public static double getValidInput(int type) {
	    while (true) { // Loop until valid input is provided
	        String input = scnr.nextLine(); // Read user input as a String
	        try {
	            if (type == 1) { // Integer type
	                return Integer.parseInt(input);
	            } else if (type == 2) { // Double type
	                return Double.parseDouble(input);
	            } else {
	                throw new IllegalArgumentException("Invalid type specified.");
	            }
	        } catch (NumberFormatException e) {
	            System.out.print("Invalid input. Please enter a valid number: ");
	        } catch (IllegalArgumentException e) {
	            System.out.println(e.getMessage());
	            return -1; // Return an error value or handle differently if needed
	        }
	    }
	}
	
	//income options
	public static void handleIncome() {
		while(true) { //loop until choice 4 is picked
			//display income menu
			 System.out.println("\n--- Income Options ---");
			 System.out.println("1. Add category");
	         System.out.println("2. Add Income to category");
	         System.out.println("3. View Income per Categories");
	         System.out.println("4. Back to Main Menu");
	         System.out.print("Enter your choice: ");
	         int choice = (int) getValidInput(1); //pass 1 for integer input
	         
	         //handles each menu option
	         switch(choice) {
	         	case 1:
	         		addIncomeCategory();
	         		break;
	         	case 2:
	         		addAmountToIncomeCategory();
	         		break;
	         	case 3:
	         		displayAmountsPerIncomeCategory();
	         		break;
	         	case 4:
	         		System.out.println("Returning to main menu...");
	         		return;
	         	default:
	         		System.out.println("Invalid choice");
	         }
		}
	}
	
	//methods to handle income cases
	//handles adding categories for income
	public static void addIncomeCategory() {
		// Prompt user to input new categories separated by commas.
		System.out.print("Please enter your category (e.g. job1,job2): ");
		String inputCategories = scnr.nextLine();
		
		//store each item split by comma
		String[] categoriesArray = inputCategories.split(","); 
		for(String category : categoriesArray) { //loop through each item
			incomeCategories.add(category.trim()); //trim whitespace if any
			incomeAmounts.add(0.0); //add initial amount for the new category
		}
	}
	
	//handles adding amount to specific income category
	public static void addAmountToIncomeCategory() {
		//initialization
		int categoryIndex = 0;
		
		//get the amount to add from user
		System.out.print("Please enter the income amount to add: $");
		double incomeAmount = getValidInput(2); //pass 2 for double input
		
		//check if category exist
		try {
			//ask for category
			System.out.print("Please enter the category to add income to: ");
			String selectedCategory = scnr.nextLine();
			
			//find index of category selected
			categoryIndex = incomeCategories.indexOf(selectedCategory); //get index using selectedCategory from user
			
			//add income amount to the array using the category index
			incomeAmounts.set(categoryIndex, (incomeAmounts.get(categoryIndex) + incomeAmount));
			System.out.println("Adding amount...Succesful!");
			
		}catch(IndexOutOfBoundsException e) {
			System.out.println("Category not found."); //display if input anything but numbers
		}
		
		totalIncome +=incomeAmount; //add incomeAmount into totalincome
	}
	
	//method to display amounts per category for income
	public static void displayAmountsPerIncomeCategory() {
		System.out.println("\n----------------------");
		
		//loop through each category
		for(int i = 0; i < incomeCategories.size(); i++) {
			System.out.printf("%d. %s: $%,.2f\n", (i + 1), incomeCategories.get(i), incomeAmounts.get(i)); //displaying all categories and amounts for each one
		}
		System.out.print("\n----------------------\n");
	}
	
	//expense options
	public static void handleExpense() {
		while(true) { //loop until choice 4 is picked
			//display income menu
			 System.out.println("\n--- Expense Options ---");
			 System.out.println("1. Add category");
	         System.out.println("2. Add Expense to category");
	         System.out.println("3. View Expenses per Categories");
	         System.out.println("4. Back to Main Menu");
	         System.out.print("Enter your choice: ");
	         int choice = (int) getValidInput(1); //pass 1 for integer input
	         
	         //handles each menu option
	         switch(choice) {
	         	case 1:
	         		addExpenseCategory();
	         		break;
	         	case 2:
	         		addAmountToExpenseCategory();
	         		break;
	         	case 3:
	         		displayAmountsPerExpenseCategory();
	         		break;
	         	case 4:
	         		System.out.println("Returning to main menu...");
	         		return;
	         	default:
	         		System.out.println("Invalid choice");
	         }
		}
	}
	
	//methods to handle expense cases
	//handles adding categories for expense
	public static void addExpenseCategory() {
		//ask for category
		System.out.print("Please enter your category (e.g. job1,job2): ");
		String inputCategories = scnr.nextLine();
		
		//store each item split by comma
		String[] categoriesArray = inputCategories.split(","); 
		for(String category : categoriesArray) { //loop through each item
			expenseCategories.add(category.trim()); //trim whitespace if any
			expenseAmounts.add(0.0); //add initial amount for the new category
		}
	}
	
	//handles adding amount to specific income category
	public static void addAmountToExpenseCategory() {
		//initialization
		int categoryIndex = 0;
		
		//get the amount to add from user
		System.out.print("Please enter the expense amount to add: $");
		double expenseAmount = getValidInput(2); //pass 2 for double input
		
		//check if category exist
		try {
			//ask for category
			System.out.print("\nPlease enter the category to add expenses to: ");
			String selectedCategory = scnr.nextLine();
			
			//find index of category selected
			categoryIndex = expenseCategories.indexOf(selectedCategory); //get index using selectedCategory from user
			
			//add income amount to the array using the category index
			expenseAmounts.set(categoryIndex, (expenseAmounts.get(categoryIndex) + expenseAmount));
			System.out.println("Adding amount...Succesful!");
			
		}catch(IndexOutOfBoundsException e) {
			System.out.println("Category not found."); //display if input anything but numbers
		}
		totalExpense +=expenseAmount; //add expenseAmount into totalexpense
	}
	
	//method to display amounts per category for income
	public static void displayAmountsPerExpenseCategory() {
		System.out.println("\n----------------------");
		
		//loop through each category
		for(int i = 0; i < expenseCategories.size(); i++) {
			System.out.printf("%d. %s: $%,.2f\n", (i + 1), expenseCategories.get(i), expenseAmounts.get(i)); //displaying all categories and amounts for each one
		}
		System.out.print("\n----------------------\n");
	}
	
	//saving options
	public static void handleSavings() {
		while(true) { //loop until choice 4 is picked
			//display income menu
			 System.out.println("\n--- Saving Options ---");
			 System.out.println("1. See final balance");
	         System.out.println("2. View balance after a year/s");
	         System.out.println("3. Back to Main Menu");
	         System.out.print("Enter your choice: ");
			 int choice = (int) getValidInput(1); //pass 1 for integer input
	         
	         //handles each menu option
	         switch(choice) {
	         	case 1:
	         		calculateFinalBalance();
	         		break;
	         	case 2:
	         		calculateFutureSavings();
	         		break;
	         	case 3:
	         		System.out.println("Returning to main menu...");
	         		return;
	         	default:
	         		System.out.println("Invalid choice");
	         }
		}
	}
	
	//methods to handle saving cases
	//method to handle calculation and display finalBalance 
	public static void calculateFinalBalance() {
		finalBalance = totalIncome - totalExpense; //+= to 
		System.out.printf("\nYour savings are: $%,.2f\n", finalBalance);
	}
	
	//method to handle calculation and display 
	public static void calculateFutureSavings() {
		double futureSavings = 0; //stores balance after years 
		
		//ask for years
		System.out.print("How many years do you want to project your savings: ");
		int yearToProject = (int) getValidInput(1); //pass 1 for integer input
		
		//ask for interest rate 
		System.out.print("What is the interest rate (APY): %");
		double interestRate = (double) getValidInput(2); //pass 2 for double input
		interestRate /= NUM_TO_CONVERT_PERCENTAGE; //convert percentage to decimal 
		System.out.println("Your savings will be compounded quarterly");
		
		//computation for future savings
		futureSavings = finalBalance * (Math.pow(1 + (interestRate / QUARTERS_OF_YEAR), (QUARTERS_OF_YEAR * yearToProject)));
		System.out.printf("Your balance after %d years is: $%,.2f\n", yearToProject, futureSavings);
	}
}