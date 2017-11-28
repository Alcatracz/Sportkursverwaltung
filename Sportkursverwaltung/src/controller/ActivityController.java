package controller;

import java.util.ArrayList;
import java.util.List;

import javax.faces.component.UICommand;
import javax.faces.component.UIForm;

import model.Activity;

public class ActivityController {

	private List<Activity> activities;
	private Activity activity;
	
	private UIForm form;
	private UIForm tableForm;
	private UICommand addCommand;
	
	public ActivityController() {
		activities = new ArrayList<Activity>();
	}
	
    public String addNew() {
        activity = new Activity("", "", 0);
        form.setRendered(true);
        addCommand.setRendered(false);
        return null;
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
	
	
	public List<Activity> getActivities() {
		return activities;
	}
	public void setActivities(List<Activity> activities) {
		this.activities = activities;
	}
	public Activity getActivity() {
		return activity;
	}
	public void setActivity(Activity activity) {
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
