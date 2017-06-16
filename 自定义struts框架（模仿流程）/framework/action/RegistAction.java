package framework.action;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import service.Service;
import entity.User;

public class RegistAction 
{
	/**
	 * 处理注册请求
	 * @param req
	 * @param resp
	 * @throws ServletException
	 * @throws IOException
	 */
	public Object regist(HttpServletRequest req, HttpServletResponse resp)
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
		service.regist(user);
		
		//3、转发
		//req.getRequestDispatcher("/login.jsp").forward(req, resp);
		uri="registSuccess";
		return uri;
	}
}
