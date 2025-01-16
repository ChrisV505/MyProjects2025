package todolist;

//Import util library
import java.util.*;

public class TodoList {
	public static void main(String[] args) {
		
		//initialize an ArrayList, Scanner
		//boolean to check if program should keep going
		ArrayList <String> tasks = new ArrayList<String>();
		Scanner input = new Scanner(System.in);
		boolean running = true;
		
		//print statement to user about program
		System.out.println("Welcome to your To-Do-List!");
		
		//while loop utilizing running boolean
		while(running) {
			//main menu for user
			System.out.println("\nSelect one option!");
			System.out.println("1: Add new task");
			System.out.println("2: View your tasks");
			System.out.println("3: Remove task");
			System.out.println("4: Exit program\n");
			
			//ask user for input
			System.out.print("What is your selection: ");
			int response = input.nextInt();
			input.nextLine(); //consume newline char from response
			
			//switch for each selection from user
			switch(response) {
				case 1: 
					System.out.print("\nEnter your task: ");
					String task = input.nextLine();
					tasks.add(task); //add task into arrayList
					System.out.println("Task added!");
					break;
					
				case 2:
					System.out.println("View your tasks: ");
					for(int i = 0; i < tasks.size(); i++) { //loop
						System.out.println((i + 1) + ": " + tasks.get(i));
					}
					break; 
					
				case 3:
					System.out.print("What task do you want to remove: ");
					int taskNum = input.nextInt(); //remove task base on user input
					if(taskNum > 0 && taskNum <= tasks.size()) {
						tasks.remove(taskNum - 1);
					} else {
						System.out.println("Invalid task number");
					}
					break;
					
				case 4:
					running = false; //change to false to exit while loop
					break;
					
				default: 
					System.out.println("Invalid number, try again");
			}
		}
		//display last message to user
		System.out.println("Thank you for using this program :)");
		
		input.close(); //close scanner
	}
}
