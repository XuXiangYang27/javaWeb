package xxy.filter;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.security.Principal;
import java.util.Enumeration;
import java.util.Locale;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletInputStream;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import javax.servlet.http.HttpSession;

/*
 * 装饰request
 */
public class EncodingRequest extends HttpServletRequestWrapper
{
	private HttpServletRequest request;
	public EncodingRequest(HttpServletRequest request) {
		super(request);
		this.request=request;
	}
	public String getParameter(String name) {
		String value=request.getParameter(name);
		//处理编码
		try {
			value=new String(value.getBytes("iso-8859-1"),"utf-8");
		} catch (UnsupportedEncodingException e) {
			throw new RuntimeException(e);
		}
		return value;
	}
	
}
