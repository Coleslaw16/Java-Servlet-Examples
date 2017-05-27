package edu.louisville.cecs640.servlets;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import edu.louisville.cecs640.controllers.ConnectionPool;

/**
 * Servlet implementation class Query
 */
@WebServlet("/Query")
public class Query extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Query() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request,response);
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//		System.out.println("We here");
		String sqlStatement = request.getParameter("pk");
		if(sqlStatement == null)
		{
			System.out.println("It is null");
		}
//		HttpSession session = request.getSession();
//		session.invalidate();
		ConnectionPool pool = ConnectionPool.getInstance("jdbc/jhston02");
		Connection connection = pool.getConnection();
		String results = parseAndExecute(sqlStatement, connection);
		request.setAttribute("sqlResult", results);
		request.setAttribute("sqlStatement",  sqlStatement);
		request.setAttribute("QueryRan", "true");
		getServletContext().getRequestDispatcher("/explore.jsp").forward(request, response);
//		doGet(request, response);
	}
	
	public String parseAndExecute(String sqlStatement, Connection connection)
	{
		String sqlResult = "";
		try
		{
			Statement statement = connection.createStatement();
			
			if(sqlStatement.equals("none"))
			{
//				System.out.println("Hello");
					try
					{
//						System.out.println(sqlStatement.toString());
						ResultSet resultSet = statement.executeQuery("SELECT * FROM SOLAR_SYSTEM");
						sqlResult = SQLUtil.getHtmlTable(resultSet, "not");
						resultSet.close();
					}
					catch(SQLException e)
					{
						System.out.println("Cannot excecute query");
						e.printStackTrace();
					}
				}
				else
				{
					try
					{
						String query = "SELECT * FROM SOLAR_SYSTEM, STAR, PLANETS WHERE SOLAR_SYSTEM.SS_NAME = ? AND SS_NAME = STAR_NAME AND SS_NAME = MULTIPLANETARYSYSTEM";
						PreparedStatement ps = connection.prepareStatement(query);
						ps.setString(1, sqlStatement);
						ResultSet rs = ps.executeQuery();
//						ResultSet resultSet = statement.executeQuery("SELECT * FROM SOLAR_SYSTEM, STAR, PLANETS WHERE SOLAR_SYSTEM.SS_NAME = "+ sqlStatement+ " AND SS_NAME = STAR_NAME AND SS_NAME = MULTIPLANETARYSYSTEM");
						sqlResult = SQLUtil.getHtmlTable(rs, "yes");
						rs.close();
					}
					catch(SQLException e)
					{
						System.out.println("Cannot excecute query");
						e.printStackTrace();
					}
				}
			
		}
		catch(SQLException e)
		{
			System.out.println("Could not create statement: " + e.getMessage());
		}
		return sqlResult;
	}

}
