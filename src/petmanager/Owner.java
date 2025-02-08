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
	
	public void getName() {
		System.out.println(name);
	}

	public void addPet(Pet pet) {
		pets.add(pet);
		System.out.println(pet.getName() + " successfully added");
	}
	
	public void removePet(String petName) {
		pets.removeIf(pet -> pet.getName().equals(petName));
	}
	
	public void listPets() {
		if(pets.isEmpty()) {
			System.out.println(name + " has no pets");
		}
		else {
			System.out.println(name + "'s pets: ");
			for(Pet pet : pets) {
				pet.displayPetDetails();
			}
		}
	}
	
	public Pet getPetByName(String petName) {
		for(Pet pet : pets) {
			if(pet.getName().equals(petName)) {
				return pet;
			}
		}
		return null; //if petName has no matches
	}
	
	public void feedingPet(String petName) {
	    Pet petToFeed = getPetByName(petName);
	    if (petToFeed != null) {
	        petToFeed.feed(); // Call a method in the Pet class to update its feeding status
	        System.out.println("You fed " + petName);
	    } else {
	        System.out.println("Pet with name " + petName + " not found.");
	    }
	}
}
