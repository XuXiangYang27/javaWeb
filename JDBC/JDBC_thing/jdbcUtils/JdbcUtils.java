package jdbcUtils;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

/**
 * @author 许湘扬 2017-3-16
 * @邮箱   547139255@qq.com
 * @说明   JDBC工具类
 */
public class JdbcUtils 
{
	public static Connection getConnection() throws IOException, ClassNotFoundException, SQLException
	{
		/*
		 * 1、加载配置文件
		 * 2、加载驱动类
		 * 3、调用DriverManager.getconnection()
		 */
		//加载配置文件
		
		InputStream in=JdbcUtils.class.getClassLoader()
				.getResourceAsStream("dbConfig.properties");
		Properties props=new Properties();
		props.load(in);
		System.out.println(props.getProperty("driverClassName"));
		//加载驱动类
		Class.forName("com.mysql.jdbc.Driver");
		//得到connnecton
		return DriverManager.getConnection
				(props.getProperty("url"), props.getProperty("username"),props.getProperty("password"));
	}
	
}	
