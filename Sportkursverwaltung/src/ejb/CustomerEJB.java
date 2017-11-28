package ejb;

import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import entitys.Customer;

@LocalBean
@Stateless
public class CustomerEJB {

	@PersistenceContext
	EntityManager em;

	public void saveCustomer(Customer customer) {
		em.merge(customer);
	}

	public void delteCustomer(Customer customer) {
		em.remove(customer);
	}

	public String getEmail(Customer customer) {
		Query q = em.createQuery("SELECT user FROM User user");
		return (String) q.getSingleResult();
	}
	public boolean isValidUser(String email,String password){
		System.out.println(email);
		System.out.println(password);
		Query q = em.createQuery("SELECT id FROM customer where email="+email+" and where password="+password);
		System.out.println(email);
		System.out.println(password);
		System.out.println(q);
		return q.getResultList().size()==1;
	}

}


