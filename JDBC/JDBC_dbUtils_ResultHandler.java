package jdbcUtils;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.MapHandler;
import org.apache.commons.dbutils.handlers.MapListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;
import org.junit.Test;
public class Demo02 
{
	/*
	 * BeanListHandler的应用：他是多行处理器
	 * 每行对象一个Stu对象
	 */
	@Test
	public void fun1() throws SQLException
	{
		QueryRunner qr=new QueryRunner(jdbcUtils.getDataSource());
		String sql="select * from stu";
		List<Stu> stuList=qr.query(sql, new BeanListHandler<Stu>(Stu.class));
		System.out.println(stuList);
	}
	/*
	 * MapHandler的应用，它是单行处理器，把一行转换成一个map对象
	 */
	@Test
	public void fun2() throws SQLException
	{
		QueryRunner qr=new QueryRunner(jdbcUtils.getDataSource());
		String sql="select * from stu where number=?";
		Object[] params={1};
		Map map=qr.query(sql, new MapHandler(), params);
		System.out.println(map);
	}
	/*
	 * MapListHandler的应用，它是多行处理器，把一行转换成一个map对象
	 * 然后把所有的Map对象都放入list中
	 */
	@Test
	public void fun3() throws SQLException
	{
		QueryRunner qr=new QueryRunner(jdbcUtils.getDataSource());
		String sql="select * from stu ";
		List<Map<String, Object>> mapList=qr.query(sql,new MapListHandler());
		System.out.println(mapList);
	}
	/*
	 * ScalarHandler,它是单行单列时使用，最为合适
	 */
	@Test
	public void fun4() throws SQLException
	{
		QueryRunner qr=new QueryRunner(jdbcUtils.getDataSource());
		String sql="select count(*) from stu ";
		//驱动包不同，返回的类型也不同 5.*jar包返回的Long类型，
		//因为Long、Integer的共同父类都是Number类
		
		
		Number  cnt=(Number)qr.query(sql,new ScalarHandler());
		long c=cnt.longValue();
		System.out.println(c);
	}
	
}
