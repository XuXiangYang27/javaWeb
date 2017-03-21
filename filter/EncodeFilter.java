package xxy.filter;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;


public class EncodeFilter implements Filter {

	public void destroy() {
		
	}

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		//处理post请求编码
		request.setCharacterEncoding("utf-8");
		/*
		 * 处理GET请求的编码问题
		 *  调包request
		 *  		1、写一个request的装饰类
		 *  		2、在放行时，使用我们自己的request
		 */
		HttpServletRequest req=(HttpServletRequest)request;
		
		if(req.getMethod().equals("GET"))
		{
			EncodingRequest er=new EncodingRequest(req);
			chain.doFilter(er, response);
		}
		else
		{
			chain.doFilter(request, response);
		}
	}

	public void init(FilterConfig fConfig) throws ServletException {
		
	}

}
