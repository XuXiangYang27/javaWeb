package xxy.BaseServlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class BServlet extends BaseServlet {

	
	public String fun1(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException 
	{
		System.out.println("fun1....");
		return "f:/index.jsp";
	}
	public String fun2(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException 
	{
		System.out.println("fun2....");
		return "r:/index.jsp";
	}
	public String fun3(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException 
	{
		System.out.println("fun3....");
		return null;
	}

}
