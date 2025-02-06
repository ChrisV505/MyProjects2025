package petmanager;

public class Pet {

	private String name;
	private String type;
	int hunger = 3; //always set hunger to 3
	
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
	
	public void displayPetDetails() {
		System.out.println(type + " " + name + " is at " + hunger + "hunger");
	}
	
	
}
