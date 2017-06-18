package propertyInjection;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import propertyInjection.bean.User;



public class PropertyInjectionDemo
{
	//属性注入  set方式
	@Test
	public void test1()
	{
		ApplicationContext ac=new ClassPathXmlApplicationContext("/propertyInjection/applicationContext.xml");
		User user=(User)ac.getBean("user");
		System.out.println(user.toString());
	}
	//属性注入  构造器方式
	@Test
	public void test2()
	{
		ApplicationContext ac=new ClassPathXmlApplicationContext("/propertyInjection/applicationContext.xml");
		User user=(User)ac.getBean("user3");
		System.out.println(user.toString());
	}
}
