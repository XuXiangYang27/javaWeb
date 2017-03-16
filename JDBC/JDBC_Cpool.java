package xxy.jdbcPool;
import java.sql.Connection;
import java.sql.SQLException;
import org.apache.commons.dbcp.BasicDataSource;
import org.junit.Test;
/*
 * 数据库连接池
 */
public class Demo01
{
	@Test
	public void fun1()
	{
		/*
		 * 1、创建连接池对象
		 * 2、配置四大参数
		 * 3、配置池参数
		 * 4、得到连接对象
		 */
		//1、创建连接池对象
		BasicDataSource datasource=new BasicDataSource();
		//2、配置四大参数
		datasource.setDriverClassName("com.mysql.jdbc.Driver");
		datasource.setUrl("jdbc:mysql://localhost:3306/stu");
		datasource.setUsername("root");
		datasource.setPassword("123456");
		//3、配置池参数
		datasource.setMaxActive(20);
		datasource.setMinIdle(3);
		datasource.setMaxWait(1000);
		//4、得到连接对象
		Connection con;
		try 
		{
			con = datasource.getConnection();
			System.out.println(con.getClass().getName());
		} 
		catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
}
