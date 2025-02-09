package toDoListAppV2;

public class Task {

	private String name;
	private String description;
	private boolean isDone;
	
	public Task(String name, String description) {
		this.name = name;
		this.description = description;
		this.isDone = false; // 
	}
	
	public String getName() {
		return name;
	}
	
	public String getDescription() {
		return description;
	}
	
	public boolean isDone() {
		return isDone;
	}
	
	public void setDone(boolean done) {
		isDone = done;
	}
}
