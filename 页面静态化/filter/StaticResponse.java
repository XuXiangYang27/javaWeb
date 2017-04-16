package filter;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServletResponseWrapper;
public class StaticResponse extends HttpServletResponseWrapper
{
	private HttpServletResponse response;
	private PrintWriter pw;
	
	
	public StaticResponse(HttpServletResponse response,String path)
	{
		super(response);
		this.response=response;
		try 
		{//创建一个与html文件路劲在一起的流对象
			pw=new PrintWriter(path,"utf-8");
		} catch (Exception e) 
		{
			throw new RuntimeException();
		}
	}
	@Override
	public PrintWriter getWriter() throws IOException
	{
		//返回一个与html绑定在一起的printwriter对象，
		//这样jsp中的输出都到html文件了
		return pw;
	}
}
