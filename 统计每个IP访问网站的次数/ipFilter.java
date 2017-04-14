package Filter;

import java.io.IOException;
import java.util.Map;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

public class ipFilter implements Filter {

	ServletContext servletContext=null;
    public ipFilter() {
       
    }
	public void destroy() {
		
	}
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		Map<String, Integer> map=(Map<String, Integer>) servletContext.getAttribute("MAP");
		if(map.containsKey(request.getRemoteAddr()))
		{
			Integer count=map.get(request.getRemoteAddr());
			count++;
			map.put(request.getRemoteAddr(), count);
		}
		else
		{
			map.put(request.getRemoteAddr(), 0);
		}
		chain.doFilter(request, response);
	}
	public void init(FilterConfig fConfig) throws ServletException
	{
		this.servletContext=fConfig.getServletContext();
	}
}
