package springCreateBeanDemo;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import springCreateBeanDemo.bean.User;


public class creadDemo01
{
	@Test
	public void fun1()
	{
		//1、创建容器对象      
		ApplicationContext ac=
				new ClassPathXmlApplicationContext("/springCreateBeanDemo/applicationContext.xml");
		//2、向容器“要”user对象
		User user=(User)ac.getBean("user3");
		//3、打印user对象
		System.out.println(user);
	}
	
	
	
}
