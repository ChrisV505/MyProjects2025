package alarmclock;

import java.util.Scanner;
import java.time.Clock;

public class AlarmClock {

	public static void main(String[] args) {
		Scanner scnr = new Scanner(System.in);
		Clock clock = Clock.systemUTC(); //get current system time
		
		java.time.Instant instant = clock.instant();
		
		System.out.println(instant);
		
		//asking user for time for alarm
		System.out.print("Please input the time you want the alarm clock (11:29): ");
		String alarmTime = scnr.nextLine(); //storing alarmtime as a string
		
		//displaying users alarm time
		System.out.println("Your alarm will ring at " + alarmTime);
		
		System.out.println("Hello from chris laptop");
		
		
		scnr.close(); //close scanner avoiding resource leak
	}

}
