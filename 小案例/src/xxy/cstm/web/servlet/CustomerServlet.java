package xxy.cstm.web.servlet;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.Method;
import java.util.List;
import java.util.Map;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.sun.xml.internal.ws.policy.privateutil.PolicyUtils.Commons;
import xxy.cstm.domain.Customer;
import xxy.cstm.domain.PageBean;
import xxy.cstm.service.CustomerService;
import cn.itcast.commons.CommonUtils;
import cn.itcast.servlet.BaseServlet;

/**
 * @author 许湘扬 2017-3-18
 * @邮箱   547139255@qq.com
 * @说明   web层
 */
public class CustomerServlet extends BaseServlet 
{
	
	private CustomerService customerService=new CustomerService();
	/*
	 * 添加客户
	 */
	public String add(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		/*
		 * 1、封装表单数据到Customer对象
		 * 2、补全：cid，使用uuid
		 * 3、使用service方法完成添加工作
		 * 4、向request域中保存成功信息
		 * 5、转发到msg.jsp
		 */
		//1、封装表单数据到Customer对象
		Customer c=CommonUtils.toBean(request.getParameterMap(),Customer.class);
		//2、补全：cid，使用uuid
		c.setCid(CommonUtils.uuid());
		// 3、使用service方法完成添加工作
		customerService.add(c);
		//4、向request域中保存成功信息
		request.setAttribute("msg", "恭喜，添加客户成功！");
		System.out.println("插入成功");
		return "f:/msg.jsp";
	}
	/*
	 * 查询所有
	 */
//	public String findAll(HttpServletRequest request, HttpServletResponse response)
//			throws ServletException, IOException {
//		/*
//		 * 1、调用service得到所有客户
//		 * 2、保存到request域
//		 * 3、转发到list.jsp
//		 * 
//		 */
//		
//		//1、调用service得到所有客户
//		List<Customer> customers=customerService.findAll();
//		//2、保存到request域
//		request.setAttribute("cstmList", customers);
//		//3、转发到list.jsp
//		return "f:/list.jsp";
//	}
	public String findAll(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		/*
		 * 1、获取页面传递的pc
		 * 2、给定PS的值
		 * 3、使用pc和ps调用service方法，得到pageBean，保存到request域中
		 * 4、转发到list.jsp
		 * 
		 */
		
		//1、得到pc,
		//		如果参数存在，需要转换成int类型
		//      如果不存在，说明pc=1
		int pc =getPc(request);
		//2、给定PS的值
		int ps=10;//给定ps的值，每页10行记录
		//3、使用pc和ps调用service方法，得到pageBean，保存到request域中
		PageBean<Customer> pb=customerService.findAll(pc,ps);
		
		//设置URL
		pb.setUrl(getUrl(request));
		request.setAttribute("pb", pb);
		return "f:/list.jsp";
	}
	/*
	 * 获取pc（当前页码）
	 */
	private int getPc(HttpServletRequest req)
	{
		String pc=req.getParameter("pc");
		if (pc==null || pc.trim().isEmpty()) {
			return 1;
		}
		return Integer.parseInt(pc);
	}
	/*
	 * 编辑信息_前奏
	 */
	public String preEdit(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		/*
		 * 1、得到sid
		 * 2、得到指定sid的信息
		 * 3、写入req中
		 * 3、转发到edit.jsp
		 * 
		 */
		
		//1、得到sid
		String cid=request.getParameter("cid");
		//2、得到指定sid的信息
		Customer customer=customerService.findByID(cid);
		//3、写入req中
		request.setAttribute("cstm", customer);
		return "f:/edit.jsp";
	}
	/*
	 * 编辑信息
	 */
	public String edit(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		/*
		 * 1、得到更新信息的ID
		 * 2、得到指定sid的信息
		 * 3、写入req中
		 * 3、转发到msg.jsp
		 * 
		 */	
		//1、得到sid
		String cid=request.getParameter("cid");
		//2、封装更新后的信息到javabean中
		Customer customer=CommonUtils.toBean(request.getParameterMap(), Customer.class);
		//3、调用更新方法//4、保存成功信息到request域中，并转发
		if(customerService.edit(cid, customer)==1)
			request.setAttribute("msg", "修改成功");
		return "f:/msg.jsp";
	}
	/*
	 * 删除用户
	 */
	public String delete(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		/*
		 * 1、得到指定的ID
		 * 2、调用service删除方法
		 * 3、成功信息写入到req中
		 * 3、转发到msg.jsp
		 * 
		 */
		
		//1、得到sid
		String cid=request.getParameter("cid");
		//2、调用service删除方法
		int i= customerService.delete(cid);
		//3、调用更新方法//4、保存成功信息到request域中，并转发
		if(i==1)
			request.setAttribute("msg", "删除成功");
		return "f:/msg.jsp";
	}
	/*
	 * 多条件组合查询
	 */
//	public String query(HttpServletRequest request, HttpServletResponse response)
//			throws ServletException, IOException {
//		/*
//		 * 1、封装表单数据到Customer对象中，它只有四个属性
//		 * 2、使用customer调用service方法，得到list<customer>
//		 * 3、保存到request中
//		 * 4、转发到list.jsp
//		 * 
//		 */
//		
//		//1、封装表单数据到Customer对象中，它只有四个属性
//		Customer criteria=CommonUtils.toBean(request.getParameterMap(), Customer.class);
//		
//		//2、调用service删除方法
//		List<Customer> cstmList=customerService.query(criteria);
//		//3、保存到request中
//		request.setAttribute("cstmList", cstmList);
//		
//		return "f:/list.jsp";
//	}
	/*
	 * 截取Url
	 *	/项目名/Servlet路径？参数字符串
	 */
	public String getUrl(HttpServletRequest request)
	{
		//项目名
		String contextPath=request.getContextPath();
		//servlet路径
		String servletPath=request.getServletPath();
		//参数字符串
		String queryString=request.getQueryString();
		
		if (queryString.contains("&pc=")) {
			int index=queryString.lastIndexOf("&pc=");
			queryString=queryString.substring(0, index); 
		}
		return contextPath+servletPath+"?"+queryString;
	}
	/*
	 * 处理编码问题：四个参数
	 */
	private Customer encoding(Customer c) throws UnsupportedEncodingException
	{
		String cname=c.getCname();
		String gender=c.getGender();
		String cellphone=c.getCellphone();
		String email=c.getEmail();
		//System.out.println("cname:"+cname);
		if (cname!=null || !cname.trim().isEmpty()) {
			cname=new String(cname.getBytes("ISO-8859-1"), "utf-8");
			c.setCname(cname);
		}
		//System.out.println("cname2:"+c.getCname());
		if (gender!=null || !gender.trim().isEmpty()) {
			gender=new String(gender.getBytes("ISO-8859-1"), "utf-8");
			c.setGender(gender);
		}if (cellphone!=null || !cellphone.trim().isEmpty()) {
			cellphone=new String(cellphone.getBytes("ISO-8859-1"), "utf-8");
			c.setCellphone(cellphone);
		}
		if (email!=null || !email.trim().isEmpty()) {
			email=new String(email.getBytes("ISO-8859-1"), "utf-8");
			c.setEmail(email);
		}
		return c;
	}
	public String query(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException
	{
		System.out.println(getUrl(request));
		/*
		 * 0、把条件封装到Customer对象中
		 * 1、得到pc
		 * 2、给定ps
		 * 3、使用pc和ps，以及条件对象，调用service方法得到pageBean
		 * 4、把pageBean保存到request域中
		 * 5、转发到list.jsp中
		 */
		//0、把条件封装到Customer对象中
		Customer criteria=CommonUtils.toBean(request.getParameterMap(), Customer.class);
		/*
		 * 处理get请求方式中的编码问题
		 */
		criteria=encoding(criteria);
	
		int pc=getPc(request);//得到当前页码
		int ps=10;//给定ps的值，每页10行
		PageBean<Customer> pb=customerService.query(criteria, pc, ps);
		
		//得到url，保存到pb中，
		pb.setUrl(getUrl(request));
		request.setAttribute("pb", pb);
		return "f:/list.jsp";
	}
}
