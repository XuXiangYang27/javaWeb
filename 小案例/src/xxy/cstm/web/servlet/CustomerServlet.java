package xxy.cstm.web.servlet;
import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.Method;
import java.util.List;
import java.util.Map;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.sun.xml.internal.ws.policy.privateutil.PolicyUtils.Commons;
import xxy.cstm.domain.Customer;
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
	public String findAll(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		/*
		 * 1、调用service得到所有客户
		 * 2、保存到request域
		 * 3、转发到list.jsp
		 * 
		 */
		
		//1、调用service得到所有客户
		List<Customer> customers=customerService.findAll();
		//2、保存到request域
		request.setAttribute("cstmList", customers);
		//3、转发到list.jsp
		return "f:/list.jsp";
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
	public String query(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		/*
		 * 1、封装表单数据到Customer对象中，它只有四个属性
		 * 2、使用customer调用service方法，得到list<customer>
		 * 3、保存到request中
		 * 4、转发到list.jsp
		 * 
		 */
		
		//1、封装表单数据到Customer对象中，它只有四个属性
		Customer criteria=CommonUtils.toBean(request.getParameterMap(), Customer.class);
		
		//2、调用service删除方法
		List<Customer> cstmList=customerService.query(criteria);
		//3、保存到request中
		request.setAttribute("cstmList", cstmList);
		
		return "f:/list.jsp";
	}
}
