package toDoListAppV2;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
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
			handleTaskChoice(scnr, tasks);
		}
	}
	
	static void taskMenu() {
		System.out.println("-------------------");
		System.out.println("1. Add new task");
		System.out.println("2. Remove task");
		System.out.println("3. List all tasks");
		System.out.println("4. Mark task as done");
		System.out.println("5. Go to file menu");
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
	
	static void handleFileChoice(int menuChoice, Scanner scnr, ArrayList<Task> tasks) {
		switch(menuChoice) {
			case 1 -> writeToExistFile(scnr, tasks);
			case 2 -> writeToNewFile(scnr, tasks);
			case 3 -> {
			}
			default -> System.out.println("invalid input. Enter number (1 - 3)"); 
		}
	}
	
	static void writeToNewFile(Scanner scnr, ArrayList<Task> tasks) {
		scnr.nextLine(); //consume new line
		System.out.print("Enter name for new file: ");
		String filePath = scnr.nextLine();
		
		if(!filePath.endsWith(".txt")) { //add .txt if not there yet
			filePath += ".txt";
		}
		
		//create file
		try {
			File myFile = new File(filePath); //create file using fileName from user
			if(myFile.createNewFile()) { //create unique file path
				System.out.println("\nFile created: " + myFile.getAbsolutePath()); 
				FileWriter writer = new FileWriter(myFile); //initialize writer for specified filePath
				for(int i = 0; i < tasks.size(); i++) {
						Task t = tasks.get(i); //loop to write every task in new line
						writer.write((i + 1) + ": " + t.getName() + " - " + t.getDescription() + 
										  " - " + (t.isDone() ? "Done" : "Not Done") + "\n");					  
				}
				
				writer.close(); //close after writing to file
				System.out.println("Data written to " + filePath);	
			}
			else {
				System.out.println("File already exist");
			}
		}
		catch(FileNotFoundException e) {
			System.out.println("Could not locate file location");
		}
		catch(IOException e) {
			System.out.println("Could not write file");
		}
	}
	
	static void writeToExistFile(Scanner scnr, ArrayList<Task> tasks) {
		scnr.nextLine(); //consume new line
		String directory = "D:\\Eclipse Workspace\\Projects2025";
		System.out.print("Enter name of pre-existing file with extension (E.g, .txt): ");
		String filePath = scnr.nextLine();
		
		try {
			File myFile = new File(directory, filePath);
			if(myFile.exists()) {
				System.out.println("File found " + myFile.getAbsolutePath());
				FileWriter writer = new FileWriter(myFile); //initialize writer for specified filePath
				for(int i = 0; i < tasks.size(); i++) {
					Task t = tasks.get(i); //loop to write every task in new line
					writer.write((i + 1) + ": " + t.getName() + " - " + t.getDescription() + 
									  " - " + (t.isDone() ? "Done" : "Not Done") + "\n");					  
			}
				
			writer.close(); //close after writing to file
			System.out.println("Data written to " + filePath);	
			}
			else {
				System.out.println("File not found");
			}				
		} 
		catch(FileNotFoundException e) {
			System.out.println("Could not locate fikle");
		} 
		catch(IOException e) {
			System.out.println("Could not write file");
		}
	}
	
	static void handleTaskChoice(Scanner scnr, ArrayList<Task> tasks) {
		try {
			int taskMenuChoice = scnr.nextInt();
			scnr.nextLine(); //consume new line
			
			switch(taskMenuChoice) {
				case 1 -> addTask(scnr, tasks);
				case 2 -> removeTask(scnr, tasks);
				case 3 -> listAllTask(scnr, tasks);
				case 4 -> markTaskDone(scnr, tasks);
				case 5 -> {
					fileMenu();
					int fileMenuChoice = scnr.nextInt();
					handleFileChoice(fileMenuChoice, scnr, tasks);
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
		catch(IndexOutOfBoundsException e) {
			System.out.println("Task doesn't exist. Try again");
		}	
	}
	
	static void addTask(Scanner scnr, ArrayList<Task> tasks) {
		System.out.print("Enter new task name: ");
		String taskName = scnr.nextLine();
		System.out.print("Enter description for task: ");
		String description = scnr.nextLine();
		Task t = new Task(taskName, description);
		tasks.add(t);	
	}
	
	static void removeTask(Scanner scnr, ArrayList<Task> tasks) {
		System.out.print("Enter name of task to remove: ");
		String removeName = scnr.nextLine();
		tasks.removeIf(t -> t.getName().equalsIgnoreCase(removeName)); //delete task obj when match is found
		System.out.println("Task removed");	
	}
	
	static void listAllTask(Scanner scnr, ArrayList<Task> tasks) {
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
	
	static void markTaskDone(Scanner scnr, ArrayList<Task> tasks) {
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
	
}