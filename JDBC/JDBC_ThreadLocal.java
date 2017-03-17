package xxy.threadLocal;
import java.util.HashMap;
import java.util.Map;
import org.junit.Test;
/*
 * ThreadLocal对象的用法
 */
public class Demo01 
{
	@Test
	public void fun1()
	{
		ThreadLocal< String> t1=new ThreadLocal<String>();
		t1.set("hello");//寸
		String s=t1.get();//取
		t1.remove();//删
		System.out.println(s);
	}
}
/*
 * 它的内部是一个Map
 */
class TL<T>
{
	private Map<Thread, T> map=new HashMap<Thread, T>();
	
	public void set(T data)
	{
		//一个线程 拥有独有的数据
		map.put(Thread.currentThread(), data);
	}
	public T get()
	{
		return map.get(Thread.currentThread());
	}
	public void remove(T data)
	{
		//一个线程 拥有独有的数据
		map.remove(Thread.currentThread());
	}
}







