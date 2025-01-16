/* This coded is for a calculator that uses various arrays and asks for initially stores input in a string 
 * then it converts it into an array(which is not the most efficient) along with that with various methods that I came up with
 * I gather the index of the operator in the string array, then with this index I'm able to make another array with double values
 * that will be the final one, with this new array I go on and handle each operation based on the operator inputed by the user
 * with a switch. 
 * Limitations: It only accepts input from this specific format (e.g.. 2 + 2, 24 / 56) 
 */
package simplecalculatorv2;
import java.util.*;

public class SimpleCalculatorV2 {
	public static void main(String[] args) {
		//initialization
		boolean choice = true;
		String userInput;
		Scanner scnr = new Scanner(System.in);
		
		//display description to user
		description();
		
		//do while loop iterates before variable choice is updated//close scanner//close scanner
		do {
			//ask user for their input
			System.out.print("\nPlease enter your calculation in the following format (2 + 2): ");
			userInput = scnr.nextLine();
			String[] convertedInput = stringConverter(userInput); //fill array with user input
			
			//find index of operator
			int opIndex = findOperatorIndex(userInput);
			//display error message if operator not found
			if(opIndex == -1) {
				System.out.println("Error: No operator found" +
							       "\nPlease try again");
				continue; //use continue to restart code
			}
		
			//get specific operator
			char op = userInput.charAt(opIndex);
			displayOperator(op, opIndex); //display operator to user or ask to try again
			
			//get numbers from user input to do calculation
			double[] userNums = numberFinder(convertedInput, op);
			
			//calculate base on operator
			double answer = calculation(userNums, op);
			System.out.println("The results is: " + answer); //display final answer
 
			//ask if user wants to do it again
			System.out.print("\nWould you like to do another calculation (y or n): ");
			char userChoice = scnr.next().charAt(0); //get user choice
			
			//handles if program should repeat or not
			if(userChoice != 'y') {
				choice = false;
			}
			scnr.nextLine(); //consume new line
		}while(choice);
		
		System.out.println("GoodBye!");
		scnr.close(); //close scanner
	}
	
	//method to display description to user
	public static void description() {
		//greeting to user
		System.out.println("Welcome to the calculator 2.0");
		
		//description to user
		System.out.println("This program will ask you for two numbers and an operator" +
						   "\nthen the program will do a calculation base on the operator you chose" +
						   "\nAfter the program will display your answer and ask you if you want to do it again");
	}
	
	//input user string to array
	public static String[] stringConverter(String userInput) {
		//fill array with user input
		String[] convertedInput = userInput.split(" ");		
		return convertedInput; //return array
	}
	
	//method to find index of operator in user String
	public static int findOperatorIndex(String userInput) {
		//make char array with operators
		char[] operators = {'+', '/', '-', '*'};
		
		//check the index of the operator being used
		for(char op : operators) {
			int index = userInput.indexOf(op);
			//check if operator found
			if(index != -1) {
				return index;
			}
		}
		return -1;
	}
	
	//method to display operator to user
	public static void displayOperator(char op, int opIndex) {
		//check if valid operator
		if(opIndex != -1) {
			System.out.println("You chose the following operator: " + op);
		} else {
			System.out.println("Invalid operator"); //display when operator invalid
		}
	}
	
	//method to find all the numbers in user input
	public static double[] numberFinder(String[] convertedInput, char op) {
		//initialize double array and index
		double[] userNums = new double[convertedInput.length - 1];
		int index = 0;
				
		//for loop to find numbers in string array
		for(String notNum : convertedInput) {
			//if statement to prevent operator being added into array
			if(notNum.equals("" + op)) { //concatenate op to use equals method
				continue;
			}
			//add valid inputs into numbers array
			userNums[index] = Double.parseDouble(notNum);
			index++;
		}
		return userNums;
	}
	
	//method for each operation
	public static double calculation(double[] userNums, char op) {
		double answer = 0.0;

		//switch to handle each operation
		switch(op) {
			case '+' -> answer = userNums[0] + userNums[1];
			case '-' -> answer = userNums[0] - userNums[1];
			case '/' -> {
				if(userNums[1] != 0) {
					answer = userNums[0] / userNums[1];
				}else {
					System.out.println("\nError: Division by Zero");
					return Double.NaN; //return NaN for division by zero
				}
			}
			case '*' -> answer = userNums[0] * userNums[1];
			default -> {
				System.out.println("\nInvalid operator. Cannot perform calculation");
				return Double.NaN; //return NaN if op contains invalid operator
			}
		}
		return answer;
	}
}