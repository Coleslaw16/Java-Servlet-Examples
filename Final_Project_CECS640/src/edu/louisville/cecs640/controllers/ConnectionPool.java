package edu.louisville.cecs640.controllers;
import java.sql.Connection;
import java.sql.SQLException;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class ConnectionPool {
	private static ConnectionPool pool = null;
	private static DataSource dataSource = null;
	private static Context initContext = null;
	
	private ConnectionPool(String resourceName)
	{
		try
		{
			initContext = new InitialContext();
			Context envContext = (Context) initContext.lookup("java:/comp/env");
			dataSource = (DataSource) envContext.lookup(resourceName);
		}
		catch(NamingException e)
		{
			System.out.print("There is something wrong in constructor of connection pool");
			e.printStackTrace();
		}
	}
	
	public static ConnectionPool getInstance(String resourceName)
	{
		if(pool == null)
		{
			pool = new ConnectionPool(resourceName);
		}
		return pool;
	}
	
	public Connection getConnection()
	{
		try
		{
			return dataSource.getConnection();
		}
		catch(SQLException e)
		{
			System.out.print("Error in the getConnection method");
			e.printStackTrace();
			return null;
	    }
	}
	
	public void freeConnection(Connection c)
	{
		try
		{
			c.close();
		}
		catch(SQLException sqle)
		{
			System.out.print("Error in free connection");
			sqle.printStackTrace();
		}
	}
}
