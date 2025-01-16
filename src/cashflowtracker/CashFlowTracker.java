package cashflowtracker;
import java.util.*; //import entire util library

public class CashFlowTracker {

	//initialize global variable
	final static int MONTHS_Of_YEAR = 12;
	
	public static void main(String[] args) {
		//initialize scanner & arrayList
		Scanner scnr = new Scanner(System.in);
		ArrayList <Double> monthlyIncomes = new ArrayList<>(Collections.nCopies(MONTHS_Of_YEAR, 0.0));
		
		while(true) {
		//display menu
		menu();
		
		//get user choice
		int choice = selection(scnr);
		
		//handles each menu choice
		switch(choice) {
			case 1: 
				addAmount(scnr, monthlyIncomes);
				break;
			case 2: 
				totalIncomePerMonth(monthlyIncomes);
				break;
			case 3:
				System.out.printf("Su monto total anual es: $%,.2f\n\n", annualTotal(monthlyIncomes));
				break;
			case 4:
				System.out.println("Gracias por usar esta programa, adios");
				scnr.close();
				return;
			default:
				System.out.println("Numero invalido\n");		
			}		
		}		
	}
	
	//menu for user
	public static void menu() {
		System.out.println("1: Agregar un monto a un mes" +
				   "\n2: Ver el monto total de cada mes"+
				   "\n3: Ver el monto total anual" +
				   "\n4: Salir del programa");	
	}
	
	//Get selection from user
	public static int selection(Scanner scnr) {
		//ask & store user choice
		System.out.print("\nCual es tu seleccion: ");
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
		System.out.print("Por favor ingrese un mes(1- 12): ");
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
		System.out.println("Cantidad agregada exitosamente\n"); //confirmation to user
	}
	
	//handles displaying total income per month to user
	public static void totalIncomePerMonth(ArrayList<Double> monthlyIncomes) {
		String months[] = {"Enero", "Frebrero", "Marzo", "Abril",
						   "Mayo", "Junio", "Julio", "Agosto", 
						   "Semptiembre", "Octubre", "Noviembre", "Diciembre"};
		
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
}
