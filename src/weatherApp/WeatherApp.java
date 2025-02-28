package weatherApp;

import java.util.Scanner;

import static java.util.Objects.nonNull;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URL;
import java.net.UnknownHostException;

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
				case 1 -> handleCurrectLocation();
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
	
	private static void handleCurrectLocation() {
		System.out.println("Fetching currect location...");
		
		String location = getCurrentLocation(); //return city name from GEOLOCATION API
		
		if(nonNull(location) && !location.isEmpty()) { //nonNull checks if location is not null && checks if location empty
			System.out.println("Your current location: " + location); //display city name to user
			fetchAndDisplayWeather(location); //
		}
	}
	
	private static String getCurrentLocation() {
		try {
			String jsonResponse = fetchData(IP_GEOLOCATION_API);
			String[] parts = jsonResponse.split("\"city\":\""); //splits jsonReponse by the city and first quote in los angeles
			
			if(parts.length > 1) {

				return parts[1].split("\"")[0]; //[1] gets index in array startin with Los Angeles, then splits it by single quote
												//[0] gets the first element of the array after spliting by single quotes
			}
		}catch(IOException e) {
			handleNetworkError("Error determining location", e);
		}
		
		return null;
	}
	
	private static String fetchData(String urlString) throws IOException {
		try {
			URL url = URI.create(urlString).toURL(); //initializes URL obj with argument passed
			HttpURLConnection connection = (HttpURLConnection) url.openConnection(); //checks connection to API
			connection.setRequestMethod("GET");
			
			StringBuilder response = new StringBuilder();
			try(BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()))) {
				String line;
				while((line = reader.readLine()) != null) {
					response.append(line).append("\n");
					System.out.println(line);
					System.out.println(response);
				}
			}
			
			return response.toString().trim();
		}catch(UnknownHostException  e) {
			throw new IOException("Network is not connected. Please check your internet connection.", e);
		}
	}
	
	private static void fetchAndDisplayWeather(String location) {
		try {
			String weatherData = fetchWeatherData(location);
			displayWeather(weatherData);
		}catch(IOException e) {
			handleNetworkError("Error fetching weather data", e);	
		}
	}
	
	private static String fetchWeatherData(String location) throws IOException {
		String encodedLocation = encodeURL(location);
		String urlString = WEATHER_API + encodedLocation + WEATHER_FORMAT;
		System.out.println(urlString);
		
		return fetchData(urlString);
	}
	
	private static String encodeURL(String str) {
		StringBuilder result = new StringBuilder();
		
		for(char c : str.toCharArray()) {
			if(Character.isLetterOrDigit(c) || c == '-' || c == '_' || c == '.' || c == '~') {
				result.append(c);
			}else {
				result.append(String.format("%%%02X", (int) c));
			}
		}
		
		return result.toString();
	}
	
	private static void displayWeather(String weatherData) {
		System.out.println("\nWeather Information: ");
		System.out.println(weatherData + "\n");
	}
	
	private static void handleNetworkError(String message, Exception e) {
		System.out.println(message + ": " + e.getMessage());
		
		if(e.getCause() instanceof UnknownHostException) {
			System.out.println("Please check your internet connection and try again");
		}
	}
}
