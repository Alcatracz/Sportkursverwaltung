package App;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class Login
 */
@WebServlet("/Login")
public class Login extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Login() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//doGet(request, response);
		try {
			Class.forName("org.postgresql.Driver");
			} catch (ClassNotFoundException e) {
				System.out.println("Treiber nicht gefunden");
			}
		
		String host = "localhost";
		String dbName = "Terminverwaltung";
		String user = "admin";
		String password = "password";
		Connection dbConnection= null;
		try {
			dbConnection = DriverManager.getConnection("jdbc:postgresql://" + host + "/" + dbName, user, password);
			dbConnection.setAutoCommit(false);
		} catch (SQLException e) {
			System.out.println("Connection fehlgeschlagen");
		}
		
		String form_email = request.getParameter("email");	
		String form_password = request.getParameter("password");
		
		System.out.println("Input: "+form_email+", "+form_password);
		CustomerModel customerModel = new CustomerModel(dbConnection);
		String urli ="";
		if(customerModel.isValidCustomer(form_email,form_password)) {
			HttpSession session = request.getSession();
			
			session.setAttribute("user", form_email);
			urli = "/home.html";
			System.out.println("login succesful");
		}else{
			System.out.println("login failed");
			urli = "Ferienhaus/login.html";
		}
		response.sendRedirect(urli);
	}

}
