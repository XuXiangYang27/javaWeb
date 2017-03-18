package jdbcUtils;

import java.sql.Connection;
import java.sql.SQLException;

import javax.sql.DataSource;


import com.mchange.v2.c3p0.ComboPooledDataSource;

public class jdbcUtils
{
	public static ComboPooledDataSource pool =new ComboPooledDataSource();
	/*
	 * 使用连接池返回一个连接
	 */
	//配置文件的默认配置，要求你必须给出c3p0-config.xml
	public static Connection getConnection() throws SQLException
	{
		Connection con=tl.get();
		//如果事物开启，con将不为空    相反，事物没有被开启 ，将会返回连接池里面的东西
		if (con!=null)
			return con;
		else
			return pool.getConnection();
	}
	/*
	 * 返回连接池对象
	 */
	public static DataSource getDataSource()
	{
		return pool;
	}
	//以下三个方法 使得工具类加入了对事物的支持，使得service层不出现connection
	/*
	 * 开启事物
	 * 1、获取一个connection，设置它的setAutoCommit（false）
	 * 2、还要保证dao中使用的连接是我们刚刚创建的！
	 * ---------------------------------------------------
	 * 1、创建一个Connection，设置为手动提交
	 * 2、把这个connection给dao用
	 * 3、还要让commitTransaction或rollbackTransaction
	 */
	private static ThreadLocal<Connection> tl=new ThreadLocal<Connection>();//事物专用连接
	public static void beginTransaction() throws SQLException
	{
		Connection con=tl.get();
		if(con!=null) throw new SQLException("已经开启了实务，不要重复开启");
		/*
		 * 1、给con赋值
		 * 2、给con设置为手动提交
		 */
		con=getConnection();//给con赋值，表示事物已经开始
		con.setAutoCommit(false);
		tl.set(con);
	}
	public static void commitTransaction() throws SQLException
	{
		Connection con=tl.get();
		if(con==null) throw new SQLException("还没有开启事物，不能提交！");
		con.commit();
		con.close();
		tl.remove();
	}
	public static void rollbackTransaction() throws SQLException
	{
		Connection con=tl.get();
		if(con==null) throw new SQLException("还没有开启事物，不能回滚！");
		con.rollback();
		con.close();
		tl.remove();
	}
	/*
	 * 释放连接
	 * 
	 */
	public static void releaseConnection(Connection connection) throws SQLException
	{
		Connection con=tl.get();
		/*
		 * 判断它是不是实务专用，如果是，就不关闭
		 * 如果不是实务专用，那么就要关闭！
		 */
		if (con==null) connection.close();
		if (con!=connection) connection.close();
	}
}
