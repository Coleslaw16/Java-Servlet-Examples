package com.louisville.CECS640.tags;

import javax.servlet.jsp.tagext.BodyTagSupport;
import java.io.IOException;

import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.BodyContent;
public class VerticalLetterWriter extends BodyTagSupport {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6366884936001379087L;
	public void setBodyContent(BodyContent bc)
	{
		super.setBodyContent(bc);
	}
	
	public int doAfterBody()
	{
		try
		{
			BodyContent bc = super.getBodyContent();
			String bodyContent = bc.getString();
			JspWriter out = bc.getEnclosingWriter();
			for(int i=0;i<bodyContent.length();i++)
			{
				out.print(bodyContent.charAt(i) + "<br>");
			}
			bc.clear();
		}
		catch (IOException e)
		{
			System.out.println("Error in doAfterBody()" + e.getMessage());
		}
		return SKIP_BODY;
	}

}
