package weatherApp;

import java.util.Scanner;

public class WeatherApp {
	
	private static final Scanner scnr = new Scanner(System.in);
	private static final String IP_GEOLOCATION_API = "http://ip-api.com/json";
	private  static final String WEATHER_API = "https://wttr.in/";
	private static final String WEATHER_FORMAT = "?format=3";
	
	public static void main(String[] args) {
		while(true) {
			displayMenu();
			int choice = getMenuChoice();
			
			switch(choice) {
				case 1 -> System.out.println("option 1");
				case 2 -> {
					System.out.println("Exiting program....");
					scnr.close();
					return;
				}
				default -> System.out.println("Invalid choice. Please try again.");
			}
			
			
			
		}
		

	}
	
	private static void displayMenu() {
		System.out.println("------WEATHER APP------");
		System.out.println("1. Get weather from currect location");
		System.out.println("2. Exit program");
		System.out.print("What's your choice: ");
		
	}
	
	private static int getMenuChoice() {
		while(true) {
			try {
				return Integer.parseInt(scnr.nextLine());
			}
			catch(NumberFormatException e) {
				System.out.print("Invalid input. Please enter a number (1, 2): ");
			}	
		}
	}
	
	private static void something() {
		
	}
	
	
	
}
