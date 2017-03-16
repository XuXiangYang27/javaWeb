package xxy.thing;
import java.sql.Connection;
import java.sql.SQLException;
import org.junit.Test;
import jdbcUtils.JdbcUtils;
/*
 * 演示事物（转账）
 */
public class Demo01
{
	/*
	 * 转账方法
	 */
	public void zhuangZhang(String from,String to,double monney)
	{
		Connection con=null;
		try
		{
			 con=JdbcUtils.getConnection();
			//开启事物
			con.setAutoCommit(false);	
			
			
			AccountDao dao=new AccountDao();
			dao.updateBalance(con,from, -monney);//转出
			dao.updateBalance(con,to, monney);//转入
			
			con.commit();
			con.close();
		}
		catch (Exception e) {
			throw new RuntimeException();
		}
		finally//回滚事物
		{
			try {
				con.rollback();
				con.close();
			} catch (SQLException e) {}
		}
	}
	@Test
	public void fun1()
	{
		zhuangZhang("zs", "ls", 500);
	}
}
