package toDoListAppV2;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class ToDoListApp {
	public static void main(String[] args) {
		
		Scanner scnr = new Scanner(System.in);
		ArrayList<Task> tasks = new ArrayList<>();

		System.out.println("--- Welcome to ToDoListApp ---");
		while(true) {
			taskMenu();
			try {
				int taskMenuChoice = scnr.nextInt();
				scnr.nextLine(); //consume new line
				switch(taskMenuChoice) {
					case 1 -> {
						System.out.print("Enter new task name: ");
						String taskName = scnr.nextLine();
						System.out.print("Enter description for task: ");
						String description = scnr.nextLine();
						Task t = new Task(taskName, description);
						tasks.add(t);
					}
					case 2 -> {
						System.out.print("Enter name of task to remove: ");
						String removeName = scnr.nextLine();
						tasks.removeIf(t -> t.getName().equalsIgnoreCase(removeName)); //delete task obj when match is found
						System.out.println("Task removed");
					}
					case 3 -> {
						if(tasks.isEmpty()) {
							System.out.println("No tasks have been added");
						}else {
							for(int i = 0; i < tasks.size(); i++) {
								Task t = tasks.get(i);
								System.out.println((i + 1) + ": " + t.getName() + " - " + t.getDescription() + 
												  " - " + (t.isDone() ? "Done" : "Not Done"));					  
							}	
						}	
					}
					case 4 -> {
						System.out.print("Enter number of task to mark as done: ");
						int taskNum = scnr.nextInt() - 1;
						if(taskNum <= tasks.size() && taskNum >= 0) {
							Task t = tasks.get(taskNum);
							t.setDone(true); // set task as done
							System.out.println("Task marked as done");
						}else {
							System.out.println("Invalid task number");
						}
						
					}
						
					case 5 -> {
						fileMenu();
						int fileMenuChoice = scnr.nextInt();
						fileChoice(fileMenuChoice);
					}
					case 6 -> {
						System.out.println("exiting program...");
						scnr.close();
						System.exit(0);
					}
					default -> System.out.println("invalid input. Enter a valid choice");
				}	
			}
			catch(InputMismatchException e) {
				System.out.println("Invalid input. Please enter a number");
				scnr.nextLine(); //consume invalid input
			} 
			catch(NoSuchElementException e) {
				System.out.println("No input found.");
			}
		}
	}
	
	static void taskMenu() {
		System.out.println("-------------------");
		System.out.println("1. Add new task");
		System.out.println("2. Remove task");
		System.out.println("3. List all tasks");
		System.out.println("4. Mark task as done");
		System.out.println("5. Save to file");
		System.out.println("6. Exit");
		System.out.println("-------------------");
		System.out.print("Enter your choice: ");
	}
	
	static void fileMenu() {
		System.out.println("-------------------");
		System.out.println("1. Save to existing file");
		System.out.println("2. Create and save to new file");
		System.out.println("3. Go back to task menu");
		System.out.println("-------------------");
		System.out.print("Enter your choice: ");
	}
	
	static void fileChoice(int menuChoice) {
		switch(menuChoice) {
			case 1 -> System.out.println("Save to existing file");
			case 2 -> System.out.println("Create and save new file");
			case 3 -> {
				break;
			}
			default -> System.out.println("invalid input. Enter number (1 - 6)"); 
		}
	}
	
	
	
}
