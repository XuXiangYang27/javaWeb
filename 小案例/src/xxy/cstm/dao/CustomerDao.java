package xxy.cstm.dao;


import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.junit.Test;

import com.sun.org.apache.bcel.internal.generic.NEW;

import xxy.cstm.domain.Customer;

import cn.itcast.commons.CommonUtils;
import cn.itcast.jdbc.JdbcUtils;
import cn.itcast.jdbc.TxQueryRunner;

/**
 * @author 许湘扬 2017-3-18
 * @邮箱   547139255@qq.com
 * @说明   持久层
 */
public class CustomerDao 
{
	private QueryRunner qr=new TxQueryRunner();
	/*
	 * 插入一条记录（添加客户）
	 */
	public void add(Customer c)
	{
		try {
			String sql = "insert into t_customer values(?,?,?,?,?,?,?)";
			Object[] params = { c.getCid(), c.getCname(), c.getGender(),
					c.getBirthday(), c.getCellphone(), c.getEmail(),
					c.getDescription()};
			qr.update(sql, params);
		} catch(SQLException e) {
			throw new RuntimeException(e);
		}
	}
	/*
	 * 查询所有记录
	 */
	public List<Customer> findAll()
	{
		try 
		{
			String sql = "select * from t_customer";
			return qr.query(sql, new BeanListHandler<Customer>(Customer.class));
			
		} catch(SQLException e) {
			throw new RuntimeException(e);
		}
	}
	/*
	 * 通过id查询用户信息
	 */
	public Customer findByID(String cid)
	{
		try 
		{
			String sql = "select * from t_customer where cid=?";
			
			return qr.query(sql, new BeanHandler<Customer>(Customer.class), cid);
			
		} catch(SQLException e) {
			throw new RuntimeException(e);
		}
	}
	/*
	 * 通过用户ID编辑用户信息
	 */
	public int edit(String cid,Customer customer)
	{
		try 
		{
			String sql = "update t_customer set " +
					"cname=?" +
					",gender=?" +
					",birthday=?" +
					",cellphone=?" +
					",email=?" +
					",description=? where cid=?";
			
			return qr.update(sql, customer.getCname(),
					customer.getGender(),
					customer.getBirthday(),
					customer.getCellphone(),
					customer.getEmail(),
					customer.getDescription(),
					cid);	
		} catch(SQLException e) {
			throw new RuntimeException(e);
		}
	}
	/*
	 * 通过用户ID删除 用户
	 */
	public int delete(String cid)
	{
		try 
		{
			String sql = "delete from t_customer where cid=?";
			
			return qr.update(sql, cid);
						
		} catch(SQLException e) {
			throw new RuntimeException(e);
		}
	}
	/*
	 * 通过体检查询满足条件的用户
	 */
	public List<Customer> query(Customer c)
	{
		try 
		{
			List<Object> params=new ArrayList<Object>();
			StringBuilder sql = new StringBuilder("select * from t_customer where 1=1");
			String cname=c.getCname();
			if (cname!=null && !cname.trim().isEmpty()) {
				sql.append(" and cname like ?");
				params.add("%"+cname+"%");
			}
			String gender=c.getGender();
			if (gender!=null && !gender.trim().isEmpty()) {
				sql.append(" and gender=?");
				params.add(gender);
			}
			String cellphone=c.getCellphone();
			if (cellphone!=null && !cellphone.trim().isEmpty()) {
				sql.append(" and cellphone like ?");
				params.add("%"+cellphone+"%");
			}
			String email=c.getEmail();
			if (email!=null && !email.trim().isEmpty()) {
				sql.append(" and email like ?");
				params.add("%"+email+"%");
			}
			
			return qr.query(sql.toString(), new BeanListHandler<Customer>(Customer.class), params.toArray());
				
		} catch(SQLException e) {
			throw new RuntimeException(e);
		}
	}
	@Test
	public void fun1()
	{
		int i= delete("477942AB7AC24FDD99F2A763D7CAEED1");
		System.out.println(i);
	}
	@Test
	public void fun2() throws SQLException
	{
		for(int i=1;i<300;i++)
		{
			
			add(new Customer(CommonUtils.uuid(),"xxy"+i,
					i%2==0 ? "男":"女",
							1900+i+"",
							100000+i+""
							,i+"@qq.com",
							"描述"+i));
			
		}
	}
}
