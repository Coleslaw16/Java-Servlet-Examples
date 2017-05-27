package edu.louisville.cecs640.servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class Directions
 */
@WebServlet("/Directions")
public class Directions extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Directions() {
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
		String url = "";
		String action = request.getParameter("action");
		if(action.equals("login") || action.equals("account"))
		{
			url = "/Login";
		}
		else if(action.equals("query"))
		{
			System.out.println("We here as well");
			url = "/Query";
		}
		else if(action.equals("create") || action.equals("destroy") || action.equals("update"))
		{
			url = "/CreateDestroy";
		}
		getServletContext().getRequestDispatcher(url).forward(request, response);
//		doGet(request, response);
	}

}
