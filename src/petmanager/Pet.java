package petmanager;

public class Pet {

	private String name;
	private String type;
	int hungerLevel = 3; //always set hunger to 3
	
	public Pet(String name, String type) {
		this.name = name;
		this.type = type;
	}
	
	public String getName() {
		return name;
	}
	
	public String getType() {
		return type;
	}
	
	public int getHunger() {
		return hungerLevel;
	}
	
	public void feed() {
	    if (hungerLevel < 3) {
	        hungerLevel++;
	        System.out.println(this.name + " is now less hungry. Hunger level: " + hungerLevel);
	    } else {
	        System.out.println(this.name + " is not hungry. Hunger level: " + hungerLevel);
	    }
	}
	
	public void displayPetDetails() {
		if(hungerLevel <= 1) {
			System.out.println("Your " + type + " " + name + " is hungry. Hunger level of " + hungerLevel + "/3"); //hard code three because hunger is always out of 3
		} else {
			System.out.println("Your " + type + " " + name + " is not hungry. Hunger level of " + hungerLevel + "/3");
		}
	}
}
