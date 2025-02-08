package petmanager;

import java.util.InputMismatchException;
import java.util.Scanner;

public class PetManager {

	public static void main(String[] args) {
		//PET MANAGER
		
		//Initialization
		Scanner scnr = new Scanner(System.in);
		
		System.out.println("Welcome to PET MANAGER!");
		
		while(true) {
			System.out.print("Please enter your name: ");
			String name = scnr.nextLine();
			Owner owner = new Owner(name);
			
		    MyRunnable myRunnable = new MyRunnable(owner);
			Thread thread = new Thread(myRunnable);
			thread.setDaemon(true);
			thread.start(); //start hunger decreasing timer when Pet is created
			
			while(true) {
				petMenu();
				try {
					int menuChoice = scnr.nextInt();
					scnr.nextLine(); // consume new line
					choiceSwitch(menuChoice, scnr, owner); //call switch to determine user choice
					if(menuChoice == 5) { //handles exit option from inner loop
						break;
					}
				}
				catch(InputMismatchException e) {
					System.out.println("Invalid input!");
					scnr.nextLine(); //consumes invalid input
				} 
			}

			System.out.print("Do you want to start a new owner pet list (yes/no): ");
			String exitChoice = scnr.nextLine().toLowerCase();
			if(exitChoice.equals("yes")) {
				continue;
			} else {
				break;
			}
		}
		System.out.println("Goodbye..."); //exit message
		scnr.close();
	}
	
	static void petMenu() {
		System.out.println("\n----------------");
		System.out.println("1. Add new pet");
		System.out.println("2. Remove pet");
		System.out.println("3. List all pets");
		System.out.println("4. Feed pet");
		System.out.println("5. Exit this owner");
		System.out.println("----------------");
		System.out.print("What your choice: ");
	}
	
	static void choiceSwitch(int menuChoice, Scanner scnr, Owner owner) {		
		Pet pet = null;
		
		switch(menuChoice) {
			case 1:
				System.out.print("Please enter your pet name: ");
				String petName = scnr.nextLine().toUpperCase();
				System.out.print("Please enter your pet type (E.g, dog, cat, bird): ");
				String petType = scnr.nextLine().toUpperCase();
				pet = new Pet(petName, petType);
				owner.addPet(pet);
				break;
			case 2:
				System.out.print("Please enter pet name your want to remove: ");
				String removePetName = scnr.nextLine().toUpperCase();
				owner.removePet(removePetName);
				break;
			case 3:
				owner.listPets();
				break;
			case 4:
				System.out.print("Please enter pet name you want to feed: ");
				String feedPetName = scnr.nextLine().toUpperCase();
				owner.feedingPet(feedPetName);
				break;
		}
	}
	
}
