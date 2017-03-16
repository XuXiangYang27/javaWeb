package demo1;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import org.junit.Test;
/**
 * @author 许湘扬 2017-3-15
 * @邮箱   547139255@qq.com
 * @说明   对数据库进行增删改查
 */
public class Demo2 
{
	@Test
	public void fun1() 
	{
		Connection con=null;
		Statement stmt=null;
		
		//设置参数
		String url="jdbc:mysql://localhost:3306/stu";
		String username="root";
		String password="123456";
		
		try 
		{
			//1、加载驱动类
			Class.forName("com.mysql.jdbc.Driver");
			//2、获得连接
			con= DriverManager.getConnection(url, username, password);
			/*
			 * 对数据库做增、删、改
			 * 1.通过Connection对象创建Statement
			 *   >Statement语句的发送器，它的功能就是向数据库发送sql语句!
			 * 2.调用它的 int executeUpdate(String sql),它可以发送DML DDL
			 * 	 >返回受影响的行数
			 */
			//1.通过Connection对象创建Statement
			stmt=con.createStatement();
			//2.调用它的 int executeUpdate(String sql)
			String sql="insert into stu values('0009','数据结构',43,'female')";
			int r=stmt.executeUpdate(sql);
			System.out.println(r);
		} 
		catch (ClassNotFoundException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		catch (SQLException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally
		{
			try 
			{
				//关闭资源
				if(stmt!=null)
				stmt.close();
				if(con!=null) con.close();
			} 
			catch (SQLException e) 
			{
				e.printStackTrace();
			}	
		}
	}
	/*
	 * 执行查询
	 */
	@Test
	public void fun2() 
	{
		Connection con=null;
		Statement stmt=null;
		ResultSet rs=null;
		/*
		 * 1、得到Connection
		 * 2、得到Statement，发送select语句
		 * 	  >调用ResultSet executeQuery(String querySql)方法
		 * 3、对查询返回的“表格”进行解析
		 */
		//设置参数
		String url="jdbc:mysql://localhost:3306/stu";
		String username="root";
		String password="123456";
		//加载驱动类
		try 
		{
			Class.forName("com.mysql.jdbc.Driver");
			//1、获得连接
			con= DriverManager.getConnection(url, username, password);
			//2、得到Statement，发送select语句，得到结果集
			stmt=con.createStatement();
			rs=stmt.executeQuery("select * from emp");
			//3、解析结果集
			// while(rs.next())
			// {
			// 	int number=rs.getInt(1);
			// 	String ename=rs.getString("ename");
			// 	Double sal=rs.getDouble("sal");
			// 	System.out.println(number+"\t"+ename+"\t"+sal);
			// }
			//通过结果集元数据 获得结果集列数
			int count=rs.getMetaData().getColumnCount();//获得结果集列数
			while(rs.next())
			{
				for (int i = 1; i <=count; i++) 
				{
					System.out.print(rs.getObject(i)+"\t");
				}System.out.println();
			}
		} 
		catch (ClassNotFoundException e) 
		{
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
			
		finally{
			try {
				//关闭资源
				if(rs!=null) rs.close();
				if(stmt!=null)
				stmt.close();
				if(con!=null) con.close();
			} 
			catch (SQLException e) {
				e.printStackTrace();
			}	
		}
	}
}












