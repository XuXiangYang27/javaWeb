package servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ValiedUsernameServlet extends HttpServlet 
{


	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException
	{
		System.out.println("111111111111111");
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		
		String name=(String )request.getParameter("username");
		
		System.out.println(name);
		if(name.equals("许湘扬"))
		{
			response.getWriter().print(1);
		}
		else
			response.getWriter().print(0);
	}

}
