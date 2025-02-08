package petmanager;

public class MyRunnable implements Runnable {
	
	private Owner owner;
		
	public MyRunnable(Owner owner) { // Constructor to initialize the Owner
		this.owner = owner;
		}

	@Override
	public void run() {
		while (true) { // Infinite loop to decrease hunger periodically
			try {
				Thread.sleep(6000); // Pause for 6 seconds
	            synchronized (this) {
	            	for (Pet pet : owner.getPets()) { // Iterate through pets
	            		pet.hungerDecrease();
	            		}
	            	}
	            } catch (InterruptedException e) {
	                System.out.println("Thread was interrupted");
	                break; // Exit the loop if the thread is interrupted
	            }
		}
	}
}
