package filter;

import java.io.File;
import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;



public class StaticFilter implements Filter {
	private ServletContext servletContext=null;
    public StaticFilter() {}
	public void init(FilterConfig fConfig) throws ServletException 
	{
		this.servletContext=fConfig.getServletContext();
	}
	public void destroy() {}

	
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		/*
		 * 1、第一次访问时，查找请求对应的html页面是否存在，如果存在重定向到html
		 * 2、如果不存在。放行！把servlet访问数据库后，输出给客户端的数据保存到一个html文件中
		 * 再重定向到html
		 */
		/*
		 * 一、获取category参数
		 * category有四种可能
		 *    null->null.html
		 *    1->1.html
		 *    2->2.html
		 *    3->3.html
		 *  html保存到htmls目录下
		 */
		
		HttpServletRequest req=(HttpServletRequest) request;
		HttpServletResponse resp=(HttpServletResponse) response;
		
		String category=req.getParameter("category");//获取类别
		String htmlPage=category+".html";//得到文件名
		String htmlPath=servletContext.getRealPath("/htmls");//得到文件的存放目录
		File destFile=new File(htmlPath, htmlPage);//(文件夹，文件名)
		
		if (destFile.exists())//如果文件存在
		{
			resp.sendRedirect(req.getContextPath()+"/htmls/"+htmlPage);
			return;
		}
		else//如果文件不存在，我们要生成HTML
		{
			StaticResponse response2=new StaticResponse(resp, destFile.getAbsolutePath());
			chain.doFilter(request, response2);
			resp.sendRedirect(req.getContextPath()+"/htmls/"+htmlPage);
		}
	}
}
