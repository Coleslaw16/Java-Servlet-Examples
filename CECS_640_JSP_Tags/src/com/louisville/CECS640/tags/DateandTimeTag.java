package com.louisville.CECS640.tags;
import java.io.IOException;
import java.util.Date;
import javax.servlet.jsp.tagext.TagSupport;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.JspTagException;

public class DateandTimeTag extends TagSupport {

	private static final long serialVersionUID = -3887964615188236064L;

	/**
	 * 
	 */


	public int doStartTag() throws JspException
	{
			JspWriter out = pageContext.getOut();
			Date now = new Date();
			try
			{
				out.print("<p style=\"color:red;font-family:Helvetica;\">" + now.toString() + "</p>");
			}
			catch(IOException e)
			{
				throw new JspTagException("DateandTime: " + e.getMessage());
			}
		return SKIP_BODY;	
	}
}
