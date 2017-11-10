package App;

public class Activity {

	private int id;
	private String name;
	private String description;
	private int max_participants;
	
	public Activity() {
		
	}

	
	public String toString() {
		return "ID: " + id + ", Name: " + name + ", Description: " + description + ", MaxParticipants: " + max_participants;
	}


	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public String getDescription() {
		return description;
	}


	public void setDescription(String description) {
		this.description = description;
	}


	public int getMax_participants() {
		return max_participants;
	}


	public void setMax_participants(int max_participants) {
		this.max_participants = max_participants;
	}
}
