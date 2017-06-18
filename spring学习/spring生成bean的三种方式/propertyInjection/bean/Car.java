package propertyInjection.bean;

public class Car
{
	private String name;
	private String color;
	
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return name+color;
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getColor() {
		return color;
	}
	public void setColor(String color) {
		this.color = color;
	}
	
}
