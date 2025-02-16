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
		
		String location = getCurrentLocation();
		
		if(nonNull(location) && !location.isEmpty()) {
			System.out.println("Your current location: " + location);
			fetchAndDisplayWeather(location);
		}
		
		
	}
	
	private static void fetchAndDisplayWeather(String location) {
		try {
			String weatherData = fetchWeatherData(location);
			displayWeather(weatherData);
		}catch(IOExecption e) {
			handleNetworkError("Error fetching weather data", e);	
		}
	}
	
	private static String fetchWeatherData(String location) {
		String encodedLocation = encodeURL(location);
		String urlString = WEATHER_API + encodedLocation + WEATHER_FORMAT;
		
		return "";
	}
	
	private static String encodeURL(String str) {
		
	}
	
	private static void displayWeather() {
		
	}
	
	private static void handleNetworkError() {
		
	}
	
	private static String getCurrentLocation() {
		try {
			String jsonResponse = fetchData(IP_GEOLOCATION_API);
			String[] parts = jsonResponse.split("\"city\":\"");
			
			if(parts.length > 1) {
				return parts[1].split("\"")[0];
			}
		}catch(IOException e) {
			System.out.println("Errrr testing");
		}
		
		return null;
	}
	
	private static String fetchData(String urlString) throws IOException {
		try {
			URL url = URI.create(urlString).toURL();
			HttpURLConnection connection = (HttpURLConnection) url.openConnection();
			connection.setRequestMethod("GET");
			
			StringBuilder response = new StringBuilder();
			try(BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()))) {
				String line;
				while((line = reader.readLine()) != null) {
					response.append(line).append("\n");
				}
			}
			
			return response.toString().trim();
		}catch(UnknownHostException  e) {
			throw new IOException("Network is not connected. Please check your internet connection.", e);
		}
	}
	
	
}
