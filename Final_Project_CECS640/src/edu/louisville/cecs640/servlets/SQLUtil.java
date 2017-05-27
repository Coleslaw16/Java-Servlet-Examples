package edu.louisville.cecs640.servlets;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;

public class SQLUtil {
	public static String getHtmlTable(ResultSet results, String specitivity) throws SQLException {
//		System.out.println("Are we even getting here?");
		StringBuffer htmlRows = new StringBuffer();
		ResultSetMetaData metaData = results.getMetaData();
		int columnCount = metaData.getColumnCount();
		if (specitivity.equals("not")) {
			htmlRows.append("<table class=\"display\">");
			htmlRows.append("</tr>");
			String[] holder = new String[columnCount];
			for (int i = 1; i <= columnCount; i++) {
				htmlRows.append("<th><b>" + metaData.getColumnName(i) + "</th>");
			}

			htmlRows.append("<th></th>");
			htmlRows.append("</tr>");
			int count = 0;
			while (results.next()) {
				htmlRows.append("<tr>");
				for (int i = 1; i <= columnCount; i++) {
					String theString = results.getString(i);
					htmlRows.append("<td>" + theString + "</td>");
					if(i == 1)
					{
						holder[count] = theString;
					}
				}
				htmlRows.append("<td> <form name=\"myform\" method=\"post\" action=\"Query\"><input type=\"hidden\" name = \"pk\" value= \"" + holder[count] + "\"><input type=\"submit\" value = \"Explore\"></form></td>");
				htmlRows.append("</tr>");
				count++;
			}
			htmlRows.append("</tr>");
			htmlRows.append("</table>");
		}
		else
		{
			htmlRows.append("<table class=\"display\">");
			htmlRows.append("</tr>");
			for (int i = 1; i <= columnCount; i++) {
				htmlRows.append("<th><b>" + metaData.getColumnName(i) + "</th>");
			}
			htmlRows.append("</tr>");
			while (results.next()) {
				htmlRows.append("<tr>");
				for (int i = 1; i <= columnCount; i++) {
					String theString = results.getString(i);
					htmlRows.append("<td>" + theString + "</td>");
				}
				htmlRows.append("</tr>");
			}
			htmlRows.append("</tr>");
			htmlRows.append("</table>");
			htmlRows.append("<br><td> <form name=\"myform\" method=\"post\" action=\"Query\"><input type=\"hidden\" name = \"pk\" value= \"none\"><input type=\"submit\" value = \"Go Back\"></form></td>");
		}
		return htmlRows.toString(); 
	}
}
