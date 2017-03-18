package jdbcUtils;

import java.sql.SQLException;

import org.junit.Test;

public class Demo01 
{
	private AccountDao dao=new AccountDao();
	
	@Test
	public void serviceMethod()
	{
		try
		{
			jdbcUtils.beginTransaction();
			dao.update("xxy",99);
			dao.update("zs",99);
			jdbcUtils.commitTransaction();
		}
		catch(Exception e)
		{
			try {
				jdbcUtils.rollbackTransaction();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
	}
}
