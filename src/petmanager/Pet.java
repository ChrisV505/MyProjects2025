package petmanager;

public class Pet {

	private String name;
	private String type;
	int hunger = 3; //always set hunger to 3
	
	//getter and setter methods
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public String getType() {
		return type;
	}
	
	public void setType(String type) {
		this.type = type;
	}
	
	void feed() {
		if(hunger >= 0) {
			hunger--;
		}
	}
	
	
	
	
}
