package petmanager;

import java.util.ArrayList;
import java.util.List;

public class Owner {

	private String name;
	private List<Pet> pets; //create individual list per owner
	
	public Owner(String name) { //constructor for name and initialiaze arraylist per owner
		this.name = name;
		this.pets = new ArrayList<>();
	}

	public void addPet(Pet pet) {
		pets.add(pet);
		System.out.println(pet.getName() + " successfully added");
	}
	
	public void removePet(String petName) {
		pets.removeIf(pet -> pet.getName().equalsIgnoreCase(petName));
	}
	
	public void listPets() {
		if(pets.isEmpty()) {
			System.out.println(name + " has no pets");
		}
		else {
			System.out.println(name + " 's pets: ");
			for(Pet pet : pets) {
				pet.displayPetDetails();
			}
		}
	}
	
	
	
}
