package xxy.BaseServlet;

import java.io.IOException;
import java.lang.reflect.Method;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public abstract class BaseServlet extends HttpServlet {
	protected void service(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException 
	{
		//1、获取参数，用来识别用户想请求的方法
		String methodName=(String) req.getParameter("method");
		if (methodName==null || methodName.trim().isEmpty()) {
			throw new RuntimeException("您没有传递method参数，无法确定您想要调用的方法");
		}
		//2、然后判断是哪一个方法
		/*
		 * 得到方法名称，可通过反射来调用指定的方法
		 */
		Class c=this.getClass();//得到当前类的class对象
		Method method=null;
		try 
		{
			method=c.getMethod(methodName, HttpServletRequest.class,HttpServletResponse.class);
		}
		catch (Exception e)
		{
			throw new RuntimeException("您要调用的方法不存在");
		} 
		try 
		{
			method.invoke(this, req,resp);
		}
		catch (Exception e)
		{
			System.out.println("您调用的方法内部抛出了异常");
			throw new RuntimeException(e);
		}
		
	}
}
