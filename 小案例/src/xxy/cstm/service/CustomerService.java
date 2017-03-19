package xxy.cstm.service;

import java.util.List;

import xxy.cstm.dao.CustomerDao;
import xxy.cstm.domain.Customer;
import xxy.cstm.domain.PageBean;

public class CustomerService
{
	/*
	 * 添加客户
	 */
	private CustomerDao customerDao=new CustomerDao();
	public void add(Customer c)
	{
		customerDao.add(c);
	}
	/*
	 * 查询指定页面的所有用户
	 */
	public PageBean<Customer> findAll(int pc,int ps)
	{
		return customerDao.findAll( pc, ps);
	}
	/*
	 * 查询指定id用户
	 */
	public Customer findByID(String cid)
	{
		return customerDao.findByID(cid);
	}
	/*
	 * 更新指定id用户信息
	 */
	public int edit(String cid,Customer customer)
	{
		return customerDao.edit(cid, customer);
	}
	/*
	 * 删除指定id用户信息
	 */
	public int delete(String cid)
	{
		return customerDao.delete(cid);
	}
	/*
	 * 条件查询
	 */
	public PageBean<Customer> query(Customer c,int pc,int ps)
	{
		return customerDao.query(c,pc,ps);
	}
}
