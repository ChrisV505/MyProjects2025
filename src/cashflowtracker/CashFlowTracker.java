package cashflowtracker;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.*; //import entire util library

public class CashFlowTracker {
	
	//initialize global variable
	final static int MONTHS_Of_YEAR = 12;
	
	public static void main(String[] args) {
		//initialize scanner & arrayList
		Scanner scnr = new Scanner(System.in);
		ArrayList <Double> monthlyIncomes = new ArrayList<>(Collections.nCopies(MONTHS_Of_YEAR, 0.0));
		String months[] = {"Enero", "Frebrero", "Marzo", "Abril",
				   "Mayo", "Junio", "Julio", "Agosto", 
				   "Semptiembre", "Octubre", "Noviembre", "Diciembre"};
		
		System.out.println("¡Bienvenidos a su\n"
				+ "rastreador de flujo de efectivo!");
		
		while(true) {
		//display menu
		mainMenu();
		try {
			int choice = selection(scnr); //get choice for menu
			
			//handles each menu choice
			switch(choice) {
				case 1: 
					addAmount(scnr, monthlyIncomes);
					break;
				case 2: 
					totalIncomePerMonth(monthlyIncomes, months);
					break;
				case 3:
					System.out.printf("Su monto total anual es: $%,.2f\n", annualTotal(monthlyIncomes));
					break;
				case 4:
					fileMenu();
					int response = selection(scnr);
					switch(response) {
						case 1 -> writeToExistingFile(scnr, monthlyIncomes, months);
						case 2 -> writeToNewFile(scnr, monthlyIncomes, months);
						case 3 -> readFile(scnr, monthlyIncomes);
						case 4 -> {
							System.out.println("Regresando...");
							continue;	
						}
					}
					break;
				case 5:
					System.out.println("Gracias por usar esta programa, adios");
					scnr.close();
					return;
				default:
					System.out.println("Numero invalido");
					break;
				}	
			} catch(InputMismatchException e) {
				System.out.println("Por favor ingrese un numero valido.");
				scnr.nextLine(); //clear invalid input from system
			}
		
		}		
	}
	
	//menu for user
	public static void mainMenu() {
		System.out.println("-----------------------");
		System.out.println("1: Agregar un monto a un mes" +
				   "\n2: Ver el monto total de cada mes"+
				   "\n3: Ver el monto total anual" +
				   "\n4: Ir a menu de archivos" +
				   "\n5: Salir del programa");	
		System.out.println("-----------------------");
	}
	
	static void fileMenu() {
		System.out.println("-----------------------");
		System.out.println("1: Guardar en archivo existente");
		System.out.println("2: Crear y guardar en un nuevo archivo");
		System.out.println("3: Comenzar desde archivo");
		System.out.println("4: Regresar a menu pricipal");
		System.out.println("-----------------------");
		
	}
	
	//Get selection from user
	public static int selection(Scanner scnr) {
		//ask & store user choice
		System.out.print("Cual es tu seleccion: ");
		int choice = scnr.nextInt();
		
		//return choice
		return choice;
	}
	
	//handles first choice from menu
	public static void addAmount(Scanner scnr, ArrayList<Double> monthlyIncomes){
		//initialization
		double currentIncome = 0; 
		String userNums = null;
		Double totalAmount = 0.0;
		
		//ask user for month
		System.out.print("Por favor ingrese un mes(1 - 12): ");
		int month = scnr.nextInt() - 1;
		scnr.nextLine(); // consumes leftover newline
		
		//error handling for month number 
		if(month < 0 || month >= MONTHS_Of_YEAR) {
			System.out.println("Numero invalido, por favor intente otra vez\n");
			return;
		}
		
		//ask and add amount to arraylist
		System.out.print("Por favor ingrese las cantidaded que quiere agregar"
					   + "\n(Separe cada cantidad con una coma (Ej. 12.32,32.42): $");
		
		userNums = scnr.nextLine(); //get user input
		totalAmount = convertStringToDouble(userNums); //call converter method
		currentIncome = monthlyIncomes.get(month); //get current amount from user month
		monthlyIncomes.set(month, currentIncome + totalAmount); //add amount to arraylist
		System.out.println("Cantidad/es agregada exitosamente"); //confirmation to user
	}
	
	//handles displaying total income per month to user
	public static void totalIncomePerMonth(ArrayList<Double> monthlyIncomes, String[] months) {

		//loop to display total amount per month
		for(int i = 0; i < months.length; i++) {
			System.out.printf(months[i] + ": $%,.2f\n", monthlyIncomes.get(i));
		}
		System.out.println();
	}
	
	//calculates annual total
	public static double annualTotal(ArrayList<Double> monthlyIncomes) {
		//initialize sum with 0
		double sum = 0;
		
		//add each element in arrayList
		for(int i = 0; i < monthlyIncomes.size(); i++) {
			sum += monthlyIncomes.get(i);
		}
		return sum;
	}
	
	//converts a string to double
	public static double convertStringToDouble(String nums) {
		Double sum = 0.0; //variable for sum of amounts
		
		//fill array with user String
		String[] convertedNums = nums.split(",");
		
		//add each element from String array using parse
		for(int i = 0; i < convertedNums.length; i++) {
			sum += Double.parseDouble(convertedNums[i]);
		}
		//return total amount
		return sum;
	}
	
	//read files
	static void readFile(Scanner scnr, ArrayList<Double> monthlyIncomes) {
		scnr.nextLine(); //consume new line still in the system 
		System.out.print("Por favor ingrese el directorio de su archivo: ");
		String filePath = scnr.nextLine().trim();
		filePath = filePath.replace("\"", "\\");
		
		try(BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
			String line;
			int i = 0;
			while((line = reader.readLine()) != null) {
				if(i < 12) {
					storeData(line, monthlyIncomes, i);
				}else {
					break;
				}
				i++;
			}
			System.out.println("Datos copiados exitosamente");
			
		}catch(FileNotFoundException e) {
			 System.out.println("Archivo no encontrado");
		} catch (IOException e1) {
			System.out.println("Algo salio mal");
		} 
	}
	
	//stores data from file to arrayList
	static void	storeData(String line, ArrayList<Double> monthlyIncomes, int i) {
		int startReading = (line.indexOf("$") + 1); //find index of $ plus 1 to start reading amounts in file
		line = line.substring(startReading).replace(",", "");
		double incomeAmount = Double.parseDouble(line);
		
		monthlyIncomes.set(i, incomeAmount);
	}
	
	//write to new file
	static void writeToNewFile(Scanner scnr, ArrayList<Double> monthlyIncomes, String[] months) {
		scnr.nextLine(); //consume new line in system
		System.out.print("Por favor ingrese el nombre para el archivo: ");
		String fileName = scnr.nextLine().trim();
		
		if(!fileName.endsWith(".txt")) {
			fileName += ".txt";
		}
		
		try {
			File myFile = new File(fileName);
			if(myFile.createNewFile()) {
				System.out.println("Archivo creado en " + myFile.getAbsolutePath());
				PrintWriter writer = new PrintWriter(myFile);
				
				for(int i = 0; i < months.length; i++) {
					writer.printf(months[i] + ": $%,.2f\n", monthlyIncomes.get(i));
				}
				writer.printf("\n%s\n$%,.2f", "Monto Total", annualTotal(monthlyIncomes));
				
				writer.close();
				System.out.println("Datos escritos en " + fileName);
			}else {
				System.out.println(fileName + " ya existe");
			}
		}catch(FileNotFoundException e) {
			System.out.println("Archivo no encontrado");
		} catch (IOException e) {
			System.out.println("Algo salio mal");
		}
	}
	
	//write to existing file
	static void writeToExistingFile(Scanner scnr, ArrayList<Double> monthlyIncomes, String[] months) {
		scnr.nextLine(); //consume new line from choice
		System.out.print("Por favor ingrese el directorio donde esta su archivo: ");
		String directory = scnr.nextLine().trim();
		directory = directory.replace("\"", "\\");
//		System.out.print("Por favor ingrese el nombre de su archivo con extension (Ej, .txt): ");
//		String fileName = scnr.nextLine().trim();
//			
//		if(!fileName.endsWith(".txt")) {
//			fileName += ".txt";
//		}

		try {
			File myFile = new File(directory);
			if(myFile.exists()) {
				System.out.println("Archivo encontrado " + myFile.getAbsolutePath());
				PrintWriter writer = new PrintWriter(myFile);
				
				for(int i = 0; i < months.length; i++) {
					writer.printf(months[i] + ": $%,.2f\n", monthlyIncomes.get(i));
				}
				writer.printf("\n%s\n$%,.2f", "Monto Total", annualTotal(monthlyIncomes));
				
				writer.close();
				System.out.println("Datos escritos en " + directory);
			}
			else { 
				System.out.println("Archivo no encontrado");
			}
		}
		catch(FileNotFoundException e) {
			System.out.println("Archivo no a sido encontrado");
		}
	}
}