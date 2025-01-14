package alarmclock;

import java.util.Scanner;

public class AlarmClock {

	public static void main(String[] args) {
		Scanner scnr = new Scanner(System.in);
		
		//asking user for time for alarm
		System.out.print("Please input the time you want the alarm clock (11:29): ");
		String alarmTime = scnr.nextLine(); //storing alarmtime as a string
		
		//displaying users alarm time
		System.out.println("Your alarm will ring at " + alarmTime); 
		
		scnr.close(); //close scanner avoiding resource leak
	}

}
