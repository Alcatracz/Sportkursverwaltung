package model;

public class ActivityModel {

	private String id;
	private String name;
	private String description;
	private int max_participants;
	
	public ActivityModel(String name, String description, int max_participants) {
		this.name = name;
		this.description = description;
		this.max_participants = max_participants;
	}
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
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
