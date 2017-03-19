package xxy.cstm.domain;

import java.util.List;

public class PageBean<T> 
{
	private int pc;//当前页码page code
//	private int tp;//总页数total page
	private int tr;//总记录数total record
	private int ps;//每页记录数page size
	private List<T> beanList;//当前页的记录
	private String url;//它就是url后的条件
	
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public int getPc() 
	{
		return pc;
	}
	public void setPc(int pc)
	{
		this.pc = pc;
	}
	public int getTp() 
	{
		//通过记录数、每页个数 给出页数
		int tp=tr/ps;
		return tr%ps==0? tp:tp+1;
	}
//	public void setTp(int tp) {
//		this.tp = tp;
//	}
	public int getTr() 
	{
		return tr;
	}
	public void setTr(int tr)
	{
		this.tr = tr;
	}
	public int getPs() 
	{
		return ps;
	}
	public void setPs(int ps)
	{
		this.ps = ps;
	}
	public List<T> getBeanList()
	{
		return beanList;
	}
	public void setBeanList(List<T> beanList)
	{
		this.beanList = beanList;
	}
	public PageBean()
	{
		super();
		// TODO Auto-generated constructor stub
	}
	public PageBean(int pc, int tp, int tr, int ps, List<T> beanList) {
		super();
		this.pc = pc;
		//this.tp = tp;
		this.tr = tr;
		this.ps = ps;
		this.beanList = beanList;
	}
	
	
}
