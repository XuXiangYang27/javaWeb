package framework;

import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.Method;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import framework.bean.ActionMapping;
import framework.bean.ActionMappingManager;
import framework.bean.Result;
/**
 * 
 * @author 许湘扬 2017-5-4
 * @邮箱   547139255@qq.com
 * @说明	   核心控制器，此项目只有这一个servlet
 * 1、拦截所有的*.action为后缀的请求
 * 2、请求：http://localhost:8080/mystruts/login.action
 *          http://localhost:8080/mystruts/register.action
 */
public class ActionServlet extends HttpServlet 
{
	
	private ActionMappingManager actionMappingManager;
	
	@Override
	//已设置tomcat启动时，执行此方法
	public void init() throws ServletException
	{
		System.out.println(1111);
		actionMappingManager=new ActionMappingManager();
		System.out.println(2222);
	}

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException
	{

	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException 
	{
		
		try
		{
			//1、获取请求uri 【login】
			String uri=request.getRequestURI();
			//得到login
			String actionName=uri.substring(uri.lastIndexOf("/")+1, uri.indexOf(".action"));
			
			//2、根据路径名称，读取配置文件【framework.action.LoginAction】
			//当前请求的处理方法【method="login"】
			ActionMapping actionMapping=actionMappingManager.getActionMapping(actionName);
			String className=actionMapping.getClassName();
			String methodName=actionMapping.getMethod();
			
			
			
			//3、反射：创建对象，调用方法，获取方法返回的标记
			Class<?> clazz=Class.forName(className);
			Object obj=clazz.newInstance();
			Method method=clazz.getDeclaredMethod(methodName, HttpServletRequest.class,HttpServletResponse.class);
			//调用方法，得到标记
			String returnFlag=(String)method.invoke(obj, request,response);
			
			
			//4、拿到标记，读取配置文件得到标记对应的页面、跳转类型
			Result result=actionMapping.getResults().get(returnFlag);
			//类型
			String type=result.getType();
			//页面
			String page=result.getPage();
			//跳转
			System.out.println("page:"+page);
			System.out.println("type:"+type);
			if("redirect".equals(type))//重定向
			{
				System.out.println(request.getContextPath()+page);
				response.sendRedirect(request.getContextPath()+page);
			}
			else//转发
			{
				request.getRequestDispatcher(page).forward(request, response);
			}
			
		}
		catch (Exception e) {
			throw new RuntimeException();
		}
	}

}
