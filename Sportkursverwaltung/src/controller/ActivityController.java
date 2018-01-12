package controller;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.faces.component.UICommand;
import javax.faces.component.UIForm;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

import entity.Activity;
import model.ActivityModel;

public class ActivityController {

	private List<ActivityModel> activities;
	private ActivityModel activity;
	
	private UIForm form;
	private UIForm tableForm;
	private UICommand addCommand;
	
	public ActivityController() {
		activities = new ArrayList<ActivityModel>();
		loadActivitiesFromDataBase();
	}
	
    public String addNew() {
        activity = new ActivityModel("", "", 0);
        form.setRendered(true);
        addCommand.setRendered(false);
        return null;
    }
    
    public void loadActivitiesFromDataBase(){
    	//ArrayList<ActivityModel> ac = new ArrayList<ActivityModel>();
    	
    	EntityManagerFactory factory =
    			Persistence.createEntityManagerFactory("Sportkursverwaltung");
    			EntityManager manager = factory.createEntityManager();
    			// Daten auslesen und anzeigen
    			String sql = "SELECT * FROM activity;";
    			System.out.println(sql);
    			Query query = manager.createNativeQuery(sql);
    			
    			List<Object[]> res = query.getResultList();
    			List<Activity> list = new ArrayList<Activity>();
    	
    			Iterator itr = res.iterator();
    			
    			while(itr.hasNext()) {
    				
    			//Object[] line = itr.next();
    			
    			//ActivityModel test = new ActivityModel(line[0],line[1],line[3]);
    			}
   	
    			//for (Object[] person : liste) {
    			//ActivityModel asdf = new ActivityModel(person.getName(),person.getDescription(), Integer.valueOf(person.getMaxParticipants()));
    			//activities.add(asdf);
    			//}
    			
    			manager.close();
    	
    }

    public String save() {
    	activities.add(activity);
        form.setRendered(false);
        addCommand.setRendered(true);
        return null;
    }

    public String cancel() {
    	activities = null;
        form.setRendered(false);
        addCommand.setRendered(true);
        return null;
    }

    public String delete() {
    	activities.remove(activity);
        return null;
    }    
	
	
	public List<ActivityModel> getActivities() {
		return activities;
	}
	public void setActivities(List<ActivityModel> activities) {
		this.activities = activities;
	}
	public ActivityModel getActivity() {
		return activity;
	}
	public void setActivity(ActivityModel activity) {
		this.activity = activity;
	}

	public UIForm getForm() {
		return form;
	}

	public void setForm(UIForm form) {
		this.form = form;
	}



	public UICommand getAddCommand() {
		return addCommand;
	}

	public void setAddCommand(UICommand addCommand) {
		this.addCommand = addCommand;
	}

	public UIForm getTableForm() {
		return tableForm;
	}

	public void setTableForm(UIForm tableForm) {
		this.tableForm = tableForm;
	}
}
