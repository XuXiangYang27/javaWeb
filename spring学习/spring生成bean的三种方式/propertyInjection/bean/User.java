package propertyInjection.bean;

public class User 
{
	private String name;
	private Integer age;
	private Car car;
	
	public User(String name,Car car)
	{
		System.out.println("User(String name,Car car)");
		this.car=car;
		this.name=name;
		
	}
	public User(Integer name,Car car)
	{
		System.out.println("User(Integer name,Car car)");
		this.car=car;
		this.name=name+"";
		
	}
	public Car getCar() {
		return car;
	}
	public void setCar(Car car) {
		this.car = car;
	}
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return name+age+car.toString();
	}
	public User()
	{
		System.out.println("空参构造方法");
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Integer getAge() {
		return age;
	}
	public void setAge(Integer age) {
		this.age = age;
	}
	
}
