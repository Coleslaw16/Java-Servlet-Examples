package edu.louisville.cecs640.servlets;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import edu.louisville.cecs640.controllers.ConnectionPool;

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
		doPost(request, response);
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String url = "/login.jsp";
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		ConnectionPool pool = ConnectionPool.getInstance("jdbc/jhston02");
		Connection connection = pool.getConnection();
		String results;
		if( request.getParameter("action").equals("login"))
		{
			results = logIn(username, password, connection);
		}
		else
		{
			createAccount(username, password, connection);
			results = logIn(username, password, connection);
		}
		if(results != null)
		{
			HttpSession session = request.getSession();
			if(session != null)
			{
				session.setAttribute("user", results);
			}
			url = "/index.jsp";
		}
		else
		{
			request.setAttribute("Error", "Not valid username and password");;
		}
		getServletContext().getRequestDispatcher(url).forward(request, response);
//		doGet(request, response);
	}
	
	public String logIn(String username, String password, Connection connection)
	{
		String value = null;
		try
		{
			String query = "SELECT * FROM USERS WHERE USERNAME = ? AND USERS_PASSWORD = ?";
			PreparedStatement ps = connection.prepareStatement(query);
			ps.setString(1, username);
			ps.setString(2, password);
			ResultSet rs = ps.executeQuery();
			if(rs.next())
			{
				 value = rs.getString(1);
			}
		}
		catch (SQLException sqle)
		{
			System.out.print("There was a problem running the query");
		}
		return value;
	}
	
	public void createAccount(String username, String password, Connection connection)
	{
		String query = "INSERT INTO USERS VALUES(?,?)";
		try {
			PreparedStatement ps = connection.prepareStatement(query);
			ps.setString(1, username);
			ps.setString(2, password);
//			System.out.println("Got to here");
			int rs = ps.executeUpdate();
		} catch (SQLException e) {
			System.out.println("Cannot excecute query");
			 e.printStackTrace();

		}
	}

}
