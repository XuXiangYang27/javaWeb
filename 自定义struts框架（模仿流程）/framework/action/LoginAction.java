package framework.action;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import service.Service;

import entity.User;

/*
 * Action表示动作类
 * 	1、一个servlet对应一个action
 *  2、action中负责处理具体的请求
 *  
 */
public class LoginAction
{
	/**
	 * 处理登陆请求
	 * @param req
	 * @param resp
	 * @throws ServletException
	 * @throws IOException
	 */
	public Object login(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException 
	{
		Object uri;
		Service service=new Service();
		//1、获取表单，封装到对象User中
		String name=req.getParameter("username");
		String pwd=req.getParameter("password");
		User user=new User();
		user.setName(name);
		user.setPwd(pwd);
		//2、调用service
		User userInfo=service.login(user);
		//3、转发
		if(userInfo==null)//登陆失败
			//req.getRequestDispatcher("/login.jsp").forward(req, resp);
			uri="loginFaild";
		else//登陆成功，跳转到首页
		{
			req.getSession().setAttribute("userInfo", userInfo);
			//resp.sendRedirect(req.getContextPath()+"/index.jsp");
			
			uri="loginSuccess";
		}
		return uri;
	}
}
