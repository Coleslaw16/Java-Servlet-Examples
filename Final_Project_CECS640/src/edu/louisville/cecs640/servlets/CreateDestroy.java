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

import edu.louisville.cecs640.controllers.ConnectionPool;

/**
 * Servlet implementation class CreateDestroy
 */
@WebServlet("/CreateDestroy")
public class CreateDestroy extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public CreateDestroy() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doPost(request, response);
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)multiple input text with same name
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		String uri = "";
		String sqlStatement = request.getParameter("type");
		ConnectionPool pool = ConnectionPool.getInstance("jdbc/jhston02");
		Connection connection = pool.getConnection();
		if (sqlStatement.equals("create")) {
			int test = creator(request, connection);
			if (test == -1) {
				request.setAttribute("Error", "Your planet could not be created");
			}
			uri = "/create.jsp";
		} 
		else if(sqlStatement.equals("update"))
		{
//			System.out.print("Help");
			int test = updater(request, connection);
			if(test == -1)
			{
				request.setAttribute("Error", "Sometimes planets just can't be changed");
			}
			uri = "/update.jsp";
			
		}
		else {
			String planet = request.getParameter("planet");
//			System.out.println(planet);
			int test = destroyer(planet, connection);
			if(test == -1)
			{
				request.setAttribute("Error", "The planet was spared from your mighty wrath");
			}
			uri = "/delete.jsp";
			
		}
		getServletContext().getRequestDispatcher(uri).forward(request, response);
	}

	public int creator(HttpServletRequest request, Connection connection) {
		String[] planetMaker = request.getParameterValues("Planet");

		String query = "INSERT INTO PLANETS VALUES(?,?,?,?,?,?,?,?)";
		try {
			PreparedStatement ps = connection.prepareStatement(query);
			for (int i = 1; i <= planetMaker.length; i++) {
				ps.setString(i, planetMaker[i - 1]);
			}
			System.out.println("Got to here");
			int rs = ps.executeUpdate();
		} catch (SQLException e) {
			System.out.println("Cannot excecute query");
			// e.printStackTrace();
			return -1;

		}
		return 1;

	}

	public int destroyer(String planet, Connection connection) {
		try {
			String query = "DELETE FROM PLANETS WHERE PLANET_NAME = ?";
			PreparedStatement ps = connection.prepareStatement(query);
			ps.setString(1, planet);
			int rs = ps.executeUpdate();
			if(rs == 0)
			{
				throw new SQLException();
			}
		} catch (SQLException e) {
			return -1;
		}
		return 1;

	}
	
	public int updater(HttpServletRequest request, Connection connection) 
	{
		String changeValue = request.getParameter("update");
		String value = request.getParameter("value");
		double dval = Double.parseDouble(value);
//		System.out.println(changeValue + " " + dval); 
		String columnName = "";
		switch(changeValue)
		{
			case "size":
				columnName = "PLANET_SIZE";
				break;
			case "mass":
				columnName = "MASS";
				break;
			case "temp":
				columnName = "AVG_TEMP";
				break;
			case "dist":
				columnName = "DISTANCE";
				break;
			case "orbit":
				columnName = "ORBITAL_PERIOD";
				break;
			case "dl":
				columnName = "LENGTHOFDAY";
				break;
		}
//		System.out.println(columnName);
		String planet = request.getParameter("pname");
		System.out.println("planet");
		try {
			String query = "UPDATE PLANETS SET " + columnName + " = ? WHERE PLANET_NAME = ?";
			PreparedStatement ps = connection.prepareStatement(query);
			ps.setDouble(1, dval);
			ps.setString(2,  planet);
			int rs = ps.executeUpdate();
			if(rs == 0)
			{
				throw new SQLException();
			}
		} catch (SQLException | ClassCastException e) {
			return -1;
		}
		return 1;
	}

}
