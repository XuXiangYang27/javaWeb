package springCreateBeanDemo.bean;

public class UserFactory 
{
	//静态工厂
	public static User createUser()
	{
		System.out.println("静态工厂创建User");
		return new User();
	}
	//实例工厂
	public  User createUser2()
	{
		System.out.println("实例工厂创建User");
		return new User();
	}
}
