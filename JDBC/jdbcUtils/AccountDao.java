package jdbcUtils;
import java.sql.Connection;
import java.sql.SQLException;
import org.apache.commons.dbutils.QueryRunner;

public class AccountDao 
{
	public static void update(String name,double monney) throws SQLException
	{
		QueryRunner qr=new XXYQueryRunner();
		String sql ="update account set balance=? where name=?";
		Object[] params={monney,name};

		qr.update(sql, params);
	}
}
