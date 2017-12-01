package entity;

import java.util.Collection;

import javax.persistence.EntityManager;
import javax.persistence.Query;

public class CustomerService {

	// Use this as followed
	// EntityManagerFactory emf =
	// Persistence.createEntityManagerFactory("Sportverwaltung");
	// EntityManager em = emf.createEntityManager();
	// CustomerService service = new CustomerService(em);
	// em.getTransaction().begin();
	// Customer emp = service.createCustomer(var,var,var,var,var);
	// em.getTransaction().commit();

	private EntityManager em;

	public CustomerService(EntityManager em) {
		this.em = em;
	}

	public Customer createCustomer(Long id, String email, String firstname, String lastname, String password) {
		Customer emp = new Customer();
		emp.setId(id);
		emp.setEmail(email);
		emp.setFirstname(firstname);
		emp.setLastname(lastname);
		emp.setPassword(password);
		em.persist(emp);
		return emp;
	}

	public void removeCustomer(int id) {
		Customer emp = findCustomer(id);
		if (emp != null) {
			em.remove(emp);
		}
	}

	public Customer findCustomer(int id) {
		return em.find(Customer.class, id);
	}

	public Collection<Customer> findAllCustomers() {
		Query query = em.createQuery("SELECT * FROM customer;");
		return (Collection<Customer>) query.getResultList();
	}
}
