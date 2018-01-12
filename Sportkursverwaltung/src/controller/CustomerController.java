 package controller;

import java.util.ArrayList;
import java.util.List;

import javax.faces.component.UICommand;
import javax.faces.component.UIForm;

import model.ActivityModel;
import model.Customer;

public class CustomerController {

	private List<Customer> customers;
	private Customer customer;
	
	private UIForm form;
	private UIForm tableForm;
	private UICommand addCommand;
	
	public CustomerController() {
		customers= new ArrayList<Customer>();
	}
	
    public String addNew() {
    	customer = new Customer("", "","","");
        form.setRendered(true);
        addCommand.setRendered(false);
        return null;
    }

    public String save() {
    	customers.add(customer);
        form.setRendered(false);
        addCommand.setRendered(true);
        return null;
    }

    public String cancel() {
    	customers = null;
        form.setRendered(false);
        addCommand.setRendered(true);
        return null;
    }

    public String delete() {
    	customers.remove(customer);
        return null;
    }
    
    
    
	
	public List<Customer> getCustomers() {
		return customers;
	}
	public void setCustomers(List<Customer> customers) {
		this.customers = customers;
	}
	public Customer getCustomer() {
		return customer;
	}
	public void setCustomer(Customer customer) {
		this.customer = customer;
	}
	public UIForm getForm() {
		return form;
	}
	public void setForm(UIForm form) {
		this.form = form;
	}
	public UIForm getTableForm() {
		return tableForm;
	}
	public void setTableForm(UIForm tableForm) {
		this.tableForm = tableForm;
	}
	public UICommand getAddCommand() {
		return addCommand;
	}
	public void setAddCommand(UICommand addCommand) {
		this.addCommand = addCommand;
	}
}
