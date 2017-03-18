package jdbcUtils;
import java.sql.Connection;
import java.sql.SQLException;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.ResultSetHandler;
public class XXYQueryRunner extends QueryRunner
{

	@Override
	public int[] batch(String sql, Object[][] params) throws SQLException {
		Connection con=jdbcUtils.getConnection();
		int[] result=super.batch(con,sql, params);
		jdbcUtils.releaseConnection(con);
		return result;
	}
	@Override
	public <T> T query(String sql, ResultSetHandler<T> rsh, Object... params)
			throws SQLException {
		Connection con=jdbcUtils.getConnection();
		T result= super.query(con,sql, rsh, params);
		jdbcUtils.releaseConnection(con);
		return result;
	}
	@Override
	public <T> T query(String sql, ResultSetHandler<T> rsh) throws SQLException {
		Connection con=jdbcUtils.getConnection();
		T result= super.query(con,sql, rsh);
		jdbcUtils.releaseConnection(con);
		return result;
	}
	@Override
	public int update(String sql, Object... params) throws SQLException {
		Connection con=jdbcUtils.getConnection();
		int result= super.update(con,sql, params);
		jdbcUtils.releaseConnection(con);
		return result;
	}
	@Override
	public int update(String sql, Object param) throws SQLException {
		Connection con=jdbcUtils.getConnection();
		int result=super.update(con,sql, param);
		jdbcUtils.releaseConnection(con);
		return result;
	}
	@Override
	public int update(String sql) throws SQLException {
		Connection con=jdbcUtils.getConnection();
		int result=  super.update(con,sql);
		jdbcUtils.releaseConnection(con);
		return result;
	}
}
