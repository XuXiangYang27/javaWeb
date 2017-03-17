package xxy.BaseServlet;

import java.io.IOException;
import java.lang.reflect.Method;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public abstract class BaseServlet extends HttpServlet 
{
	protected void service(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException 
	{
		//1、获取参数，用来识别用户想请求的方法
		String methodName=(String) req.getParameter("method");
		if (methodName==null || methodName.trim().isEmpty()) 
		{
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
			System.out.println(methodName);
			method=c.getMethod(methodName, HttpServletRequest.class,HttpServletResponse.class);
			
		}
		catch (Exception e)
		{
			throw new RuntimeException("您要调用的方法不存在");
		} 
		try 
		{
		   /*获取请求处理方法执行后返回的字符串，它表示转发或者重定向的路径
			*帮它完成转发或重定向
			*/
			
			String result =(String)method.invoke(this, req,resp);
			System.out.println(result);
			/*
			 * 查看返回的字符串中是否包含冒号，如果没有，表示转发
			 * 如果有，使用冒号分割字符串，得到前缀和后缀
			 * 其中前缀如果是f，表示转发，如果是r表示重定向，后缀就是要转发或重定向的路劲
			 * 如果返回的字符串为null或者为“”，那么我们什么也不做！
			 */
			if (result==null || result.trim().isEmpty()){return ;}
			if (result.contains(":"))
			{
				int index=result.indexOf(":");//获取冒号的位置
				String s=result.substring(0, index);//前缀
				String path=result.substring(index+1 );//后缀
				if(s.equalsIgnoreCase("r"))
				{
					resp.sendRedirect(req.getContextPath()+path);
					
				}
				else if (s.equalsIgnoreCase("f")) {
					req.getRequestDispatcher(path).forward(req, resp);
				}else
				{
					throw new RuntimeException("你指定的操作："+s+",当前还不支持");
				}
			}
			
		}
		catch (Exception e)
		{
			System.out.println("您调用的方法内部抛出了异常");
			throw new RuntimeException(e);
		}
		
	}
}
