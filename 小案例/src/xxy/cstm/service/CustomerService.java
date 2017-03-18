package xxy.cstm.service;

import java.util.List;

import xxy.cstm.dao.CustomerDao;
import xxy.cstm.domain.Customer;

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
	 * 查询所有
	 */
	public List<Customer> findAll()
	{
		return customerDao.findAll();
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
	public List<Customer> query(Customer c)
	{
		return customerDao.query(c);
	}
}
