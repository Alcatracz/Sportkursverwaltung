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

/**
 * Servlet implementation class AddActivity
 */
@WebServlet("/AddActivity")
public class AddActivity extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AddActivity() {
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
		System.out.println("doPost");
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
		
		Activity activity = new Activity();
		activity.setName(request.getParameter("name"));
		activity.setDescription(request.getParameter("description"));
		activity.setMax_participants(Integer.valueOf(request.getParameter("max_participants")));
		
		System.out.println(activity.toString());
		
		ActivityModel model = new ActivityModel(dbConnection);
		model.addActivity(activity);
		System.out.println("Addded");
		
	}

}
