package PrepareStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import org.junit.Test;
/*
 * 	PrepareStatement用法
 */
public class demo1 
{
	@Test
	public void fun1()
	{
		Connection con=null;
		Statement stmt=null;
		ResultSet rs=null;
		String url="jdbc:mysql://localhost:3306/stu?useServerPrepStmts=true&cachePrepStmts=true";
		String username="root";
		String password="123456";
		try 
		{
			Class.forName("com.mysql.jdbc.Driver");
			con= DriverManager.getConnection(url, username, password);
			/*
			 * 一、得到PparedStatement
			 * 	1、给出sql模板：所有的参数使用?来替代
			 * 	2、调用Connection方法，得到PreparedStatement
			 */
			String sql="select * from emp where job=? and sal>?";
			PreparedStatement pstmt=con.prepareStatement(sql);
			/*
			 * 二、为参数复制
			 */
			pstmt.setString(1, "销售员");//给第1个问号赋值，值为“销售员”
			pstmt.setInt(2, 1000);//给第2个问号赋值，值为1000
			/*
			 * 三、执行
			 */
			rs=pstmt.executeQuery();//调用查询方法，向数据库发送查询语句
			//输出查询结果
			int count =rs.getMetaData().getColumnCount();
			while(rs.next())
			{
				for (int i = 1; i <=count; i++)
					System.out.print(rs.getObject(i)+"\t");
				System.out.println();
			}
		} 
		catch (ClassNotFoundException e) {e.printStackTrace();} 
		catch (SQLException e) {e.printStackTrace();}	
		finally{
			try {//关闭资源
				if(rs!=null) rs.close();
				if(stmt!=null)stmt.close();
				if(con!=null) con.close();
			} 
			catch (SQLException e) {e.printStackTrace();}	
		}
	}
}
