package Xstream;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import com.thoughtworks.xstream.XStream;

/*
 * 演示XStream
 */
public class Demo1
{
	public List<Province> getProvinceList()
	{
		Province p1=new Province();
		p1.setName("北京");
		p1.addCity(new City("东城区", "DongChengQu"));
		p1.addCity(new City("昌平区", "ChangPingQu"));
		
		Province p2=new Province();
		p2.setName("辽宁");
		p2.addCity(new City("沈阳", "shenYang"));
		p2.addCity(new City("葫芦岛", "huLuDao"));
		
		List<Province> list=new ArrayList<Province>();
		list.add(p1);
		list.add(p2);
		return list;
	}
	
	/*
	 * 演示原始的 xml文件 
	 */
	@Test
	public void fun1()
	{
		List<Province> proList=getProvinceList();
		/*
		 * 创建XStream对象
		 * 调用toXML把集合转换成xml字符串
		 */
		XStream  stream=new XStream();
		String s=stream.toXML(proList);
		System.out.println(s);
	}
	/*
	 * 别名：alias
	 * 
	 * 希望：
	 * *默认List类型对应<List>元素，希望让其对应<china>元素
	 * *默认Province类型对应<cn.itcast.demo1.Province>,希望对应<province>
	 * *默认City类型对应<cn.itcast.demo1.City>,希望它对应<city>元素
	 */
	@Test
	public void fun2()
	{
		List<Province> proList=getProvinceList();
		XStream  stream=new XStream();
		/*
		 * 给指定类型 指定 别名
		 */
		stream.alias("china",List.class);//给List类型指定别名
		stream.alias("province",Province.class);//给Province类型指定别名
		stream.alias("city",City.class);//给List类型指定别名
		String s=stream.toXML(proList);
		System.out.println(s);
	
	}
	/*
	 * 把name成员变成provicne的属性
	 */
	@Test
	public void fun3()
	{
		List<Province> proList=getProvinceList();
		XStream  stream=new XStream();
		/*
		 * 给指定类型 指定 别名
		 */
		stream.alias("china",List.class);//给List类型指定别名
		stream.alias("province",Province.class);//给Province类型指定别名
		stream.alias("city",City.class);//给List类型指定别名
		//把name成员变成provicne的属性
		stream.useAttributeFor(Province.class, "name");
		
		String s=stream.toXML(proList);
		System.out.println(s);
	}
	
	/*
	 * 去掉Province对象下  不显示cities集合名，只显示集合里装的对象
	 */
	@Test
	public void fun4()
	{
		List<Province> proList=getProvinceList();
		XStream  stream=new XStream();
		/*
		 * 给指定类型 指定 别名
		 */
		stream.alias("china",List.class);//给List类型指定别名
		stream.alias("province",Province.class);//给Province类型指定别名
		stream.alias("city",City.class);//给List类型指定别名
		
		//把name成员变成provicne的属性
		stream.useAttributeFor(Province.class, "name");
		//去除province类的名为cities的List类型的属性
		stream.addImplicitCollection(Province.class, "cities");
		
		String s=stream.toXML(proList);
		System.out.println(s);
	}
	
	/*
	 * 去掉不想要的javabean属性
	 * 就是让某些javabean属性，不生成对应的xml元素
	 */
	@Test
	public void fun5()
	{
		List<Province> proList=getProvinceList();
		XStream  stream=new XStream();
		/*
		 * 给指定类型 指定 别名
		 */
		stream.alias("china",List.class);//给List类型指定别名
		stream.alias("province",Province.class);//给Province类型指定别名
		stream.alias("city",City.class);//给List类型指定别名
		
		//把name成员变成provicne的属性
		stream.useAttributeFor(Province.class, "name");
		//去除province类的名为cities的List类型的属性
		stream.addImplicitCollection(Province.class, "cities");
		//去除不需要的javabena属性
		stream.omitField(City.class,"description");
		String s=stream.toXML(proList);
		System.out.println(s);
	}
}
