package xxy.cstm.dao;


import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;
import org.junit.Test;

import com.sun.org.apache.bcel.internal.generic.NEW;

import xxy.cstm.domain.Customer;
import xxy.cstm.domain.PageBean;

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
	public PageBean<Customer> findAll(int pc,int ps)
	{
		try 
		{
			/*
			 * 1、创建pageBean对象
			 * 2、设置pb的pc和ps
			 * 3、得到tr，设置给pb
			 * 4、得到beanList，设置给pb对象
			 */
			//1、创建pageBean对象
			PageBean<Customer> pb=new PageBean<Customer>();
			//2、设置pb的pc和ps
			pb.setPc(pc);
			pb.setPs(ps);
			//3、得到tr，设置给pb
			String sql = "select count(*) from t_customer";
			Number num=(Number)qr.query(sql, new ScalarHandler());
			int tr=num.intValue();
			pb.setTr(tr);
			sql="select * from t_customer limit ?,?";
			List<Customer> rs=qr.query(sql, new BeanListHandler<Customer>(Customer.class), (pc-1)*ps,ps);
			pb.setBeanList(rs);
			return pb;
		}
		catch(SQLException e) {
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
	public PageBean<Customer> query(Customer c,int pc,int ps)
	{
		try 
		{
			/*
			 * 1、创建PageBean对象
			 * 2、设置已有的属性，pc和ps
			 * 3、得到tr
			 * 4、得到beanList
			 */
			PageBean<Customer> pb=new PageBean<Customer>();
			pb.setPc(pc);
			pb.setPs(ps);
			 
			/*
			 * 得到所有符合要求的记录数：tr
			 */
			List<Object> params=new ArrayList<Object>();
			StringBuilder cntSql = new StringBuilder("select count(*) from t_customer ");
			StringBuilder whereSql=new StringBuilder(" where 1=1 ");
			//System.out.println("11111"+c.getCname());
			String cname=c.getCname();
			if (cname!=null && !cname.trim().isEmpty()) {
				whereSql.append(" and cname like ?");
				params.add("%"+cname+"%");
			}
			String gender=c.getGender();
			if (gender!=null && !gender.trim().isEmpty()) {
				whereSql.append(" and gender=?");
				params.add(gender);
			}
			String cellphone=c.getCellphone();
			if (cellphone!=null && !cellphone.trim().isEmpty()) {
				whereSql.append(" and cellphone like ?");
				params.add("%"+cellphone+"%");
			}
			String email=c.getEmail();
			if (email!=null && !email.trim().isEmpty()) {
				whereSql.append(" and email like ?");
				params.add("%"+email+"%");
			}
			/*
			 * 执行 条件组合查询语句 : select count(*) from ...+ where ...
			 */
			
			//System.out.println(whereSql.toString());
			Number num=(Number)qr.query(cntSql.append(whereSql).toString(), new ScalarHandler(), params.toArray());
			int tr=num.intValue();
			pb.setTr(tr);
			System.out.println(tr+"tr");
			/*
			 * 得到BeanList
			 */
			StringBuilder sql=new StringBuilder(" select * from t_customer ");
			//我们查询beanList这一步，还需要给出limit子句
			StringBuilder limitSql=new StringBuilder(" limit ?,? ");
			//设置limit里面的参数
			params.add((pc-1)*ps);
			params.add(ps);
			//执行
			//System.out.println(sql.append(whereSql).append(limitSql).toString());
			List<Customer> customers=qr.query(sql.append(whereSql).append(limitSql).toString(), 
					new BeanListHandler<Customer>(Customer.class), params.toArray());
			pb.setBeanList(customers);
			//System.out.println(sql.append(whereSql).append(limitSql).toString());
			return pb;
		} catch(SQLException e) {
			throw new RuntimeException(e);
		}
	}
//	public PageBean<Customer> query(Customer c,int pc,int ps)
//	{
//		try 
//		{
//			List<Object> params=new ArrayList<Object>();
//			StringBuilder sql = new StringBuilder("select * from t_customer where 1=1");
//			String cname=c.getCname();
//			if (cname!=null && !cname.trim().isEmpty()) {
//				sql.append(" and cname like ?");
//				params.add("%"+cname+"%");
//			}
//			String gender=c.getGender();
//			if (gender!=null && !gender.trim().isEmpty()) {
//				sql.append(" and gender=?");
//				params.add(gender);
//			}
//			String cellphone=c.getCellphone();
//			if (cellphone!=null && !cellphone.trim().isEmpty()) {
//				sql.append(" and cellphone like ?");
//				params.add("%"+cellphone+"%");
//			}
//			String email=c.getEmail();
//			if (email!=null && !email.trim().isEmpty()) {
//				sql.append(" and email like ?");
//				params.add("%"+email+"%");
//			}
//			
//			return qr.query(sql.toString(), new BeanListHandler<Customer>(Customer.class), params.toArray());
//				
//		} catch(SQLException e) {
//			throw new RuntimeException(e);
//		}
//	}
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
