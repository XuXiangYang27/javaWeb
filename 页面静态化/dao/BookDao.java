package dao;

import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import cn.itcast.jdbc.TxQueryRunner;
import domain.Book;

public class BookDao 
{
	private QueryRunner qr=new TxQueryRunner();
	
	//按分类查询
	public List<Book> findAll()
	{
		try
		{
			String sql="select * from t_book";
			return qr.query(sql, new BeanListHandler<Book>(Book.class));
		}
		catch (Exception e) 
		{
			throw new RuntimeException();
		}
	}
	
	//按指定类别查询
	public List<Book> findByCategory(int category)
	{
		try
		{
			String sql="select * from t_book where category=?";
			return qr.query(sql, new BeanListHandler<Book>(Book.class),category);
		}
		catch (Exception e) 
		{
			throw new RuntimeException();
		}
	}
}
