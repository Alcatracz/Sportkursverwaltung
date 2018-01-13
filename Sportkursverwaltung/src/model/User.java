package model;

public class User {
    private String name;
    private String password;
    private boolean istTrainer;
    private int id;

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
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }
	public boolean isIstTrainer() {
		return istTrainer;
	}
	public void setIstTrainer(boolean istTrainer) {
		this.istTrainer = istTrainer;
	}


}
