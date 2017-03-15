package demo1;
import java.sql.DriverManager;
import java.sql.SQLException;
import org.junit.Test;
import com.mysql.jdbc.Connection;
/**
 * @author 许湘扬 2017-3-15
 * @邮箱   547139255@qq.com
 * @说明   JDBC连接MYSQL
 */
/*
 * ClassNotFoundException
 * 	>没导包
 * 	>forname里面名字打错了
 * SQLException
 * 	>检查url,username,password是否正确
 * 	>查看mysql是否开启服务
 */

public class Demo1 
{
	@Test
	public void fun1() throws ClassNotFoundException, SQLException
	{
		/*
		 * jdbc四大配置参数：
		 * >driverClassName:com.mysql.jdbc.Driver
		 * >url:jdbc:mysql://localhost:3306/stu
		 * >username:root
		 * >password:123
		 */
		//设置参数
		String url="jdbc:mysql://localhost:3306/stu";
		String username="root";
		String password="123456";
		/*
		 *1、加载驱动类（其实就是注册驱动）:加载类的时候，会执行类的静态初始化块，这个块里面执行了<注册驱动>方法
		 *   注：所有的java.sql.Driver实现类，都提供了static块，块内的代码就是把自己注册到DriverManager中！
		 */
		Class.forName("com.mysql.jdbc.Driver");
		//2、获得连接
		Connection con=(Connection) DriverManager.getConnection(url, username, password);
		System.out.println(con);
	}
}
