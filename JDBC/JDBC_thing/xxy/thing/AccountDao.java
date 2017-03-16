package xxy.thing;

import java.sql.Connection;
import java.sql.PreparedStatement;

import jdbcUtils.JdbcUtils;

public class AccountDao 
{
	/*
	 * 修改指定用户的余额
	 */
	public void updateBalance(Connection con1,String name,double balance)
	{
		try
		{
			Connection con=con1;
			
			String sql="update account set balance=balance+? where name=?";
			PreparedStatement ps=con.prepareStatement(sql);
			ps.setDouble(1, balance);
			ps.setString(2, name);
			ps.executeUpdate();
		}
		catch (Exception e) {
			throw new RuntimeException();
		}
	}

	
		
}
