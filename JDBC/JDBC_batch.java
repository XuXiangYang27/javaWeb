package batchProcess;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import jdbcUtils.JdbcUtils;

import org.junit.Test;

public class demo01
{
	/*
	 * pstmt对象内部有集合
	 *  1、用循环疯狂向pstmt中添加sql参数，它自己有模板，使用一组参数与模板可以匹配出一条sql语句
	 *  2、调用它的执行批方法，完成向数据库发送
	 *  
	 */
	@Test
	public void fun1() throws ClassNotFoundException, IOException, SQLException
	{
		/*
		 * pstmt:
		 * 	>添加参数到批中
		 * 	>执行批
		 */
		Connection con=JdbcUtils.getConnection();
		String sql="insert into stu values(?,?,?)";
		PreparedStatement ps=con.prepareStatement(sql);
		//1、疯狂的添加参数
		for (int i = 0; i <1000; i++) 
		{
			ps.setInt(1, i);
			ps.setString(2, "stu_"+i);
			ps.setInt(3, i);
			ps.addBatch();//添加批，把这一组参数添加到集合中
		}
		long star=System.currentTimeMillis();
		ps.executeBatch();//执行批
		long end=System.currentTimeMillis();
		System.out.println(star-end);
	}
}
