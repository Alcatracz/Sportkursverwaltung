package entity;

import java.util.Collection;

import javax.persistence.Column;
import javax.persistence.EntityManager;
import javax.persistence.Query;

//Use this as followed (replace with "Class"Service and Classname)	
//EntityManagerFactory emf = Persistence.createEntityManagerFactory("Sportverwaltung");
//EntityManager em = emf.createEntityManager();
//CustomerService service = new CustomerService(em);
//em.getTransaction().begin();
//Customer emp = service.createCustomer(var,var,var,var,var);
//em.getTransaction().commit();

public class ActivityServices {
/*
	private EntityManager em;

	public ActivityServices(EntityManager em) {
		this.em = em;
	}

	public Activity createActivity(Long id, String description, String maxParticipants, String name) {
		Activity emp = new Activity();
		emp.setId(id);
		emp.setDescription(description);
		emp.setMaxParticipants(maxParticipants);
		emp.setName(name);
		em.persist(emp);
		return emp;
	}

	public void removeActivity(int id) {
		Activity emp = findActivity(id);
		if (emp != null) {
			em.remove(emp);
		}
	}

	public Activity findActivity(int id) {
		return em.find(Activity.class, id);
	}

	public Collection<Activity> findAllActivitys() {
		Query query = em.createQuery("SELECT * FROM activity;");
		return (Collection<Activity>) query.getResultList();
	}
	*/
}
