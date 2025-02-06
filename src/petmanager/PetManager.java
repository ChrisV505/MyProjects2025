package petmanager;

import java.util.Scanner;

public class PetManager {

	public static void main(String[] args) {
		
		//PET MANAGER
		
		//Initialization
		Pet pet = new Pet();
		Scanner scnr = new Scanner(System.in);
		
		System.out.println("Welcome to PET MANAGER!");
		menu();
	}

	static void menu() {
		System.out.println("1. Add new pet");
		System.out.println("2. Remove pet");
		System.out.println("3. List all pets");
		System.out.println("4. Update pet details");
		System.out.println("5. Feed pet");
		System.out.println("6. Track health");
		System.out.println("7. Exit");
	}
	
}
