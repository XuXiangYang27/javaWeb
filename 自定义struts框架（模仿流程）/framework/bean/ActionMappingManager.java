package framework.bean;

import java.io.InputStream;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import com.sun.xml.internal.txw2.Document;

/**
 * 
 * @author 许湘扬 2017-5-4
 * @邮箱   547139255@qq.com
 * @说明	   加载配置文件，封装整个mystruts.xml
 */
public class ActionMappingManager 
{
	//保存action的集合
	private Map<String,  ActionMapping> allActions;
	
	/**
	 * 根据请求路径名称，返回Action的映射对象
	 * @param actionName  当前请求路径
	 * @return	返回配置文件中代表action节点的ActionMapping对象	
	 */
	public ActionMapping getActionMapping(String actionName)
	{
		if(actionName==null)
			throw new RuntimeException("传入参数有误，请查看mystruts.xml配置的路径");
		
		ActionMapping actionMapping=allActions.get(actionName);
		
		if(actionMapping==null)
			throw new RuntimeException("路径在mystruts.xml配置中找不到，请检查");
		
		return actionMapping;
	}
	
	/**
	 * 构造函数，读取xml内容写入map中
	 */
	public ActionMappingManager()
	{
		allActions=new HashMap<String, ActionMapping>();
		this.init();
	}

	/**
	 * 初始化allActions集合
	 */
	private void init()
	{
		/***************DOM4J读取配置文件***************/
		try
		{
			//1、得到解析器
			SAXReader reader=new SAXReader();
			//得到src/mystruts.xml 文件流
			InputStream inStream=this.getClass().getResourceAsStream("/mystruts.xml");
			//2、加载文件
			org.dom4j.Document doc=reader.read(inStream);
			
			//3、获取根节点
			Element root=doc.getRootElement();
			
			//4、得到package节点
			Element ele_package=root.element("package");
			
			//5、得到package节点下，所有的action子节点
			List<Element> listActions=ele_package.elements("action");
			
			//6、遍历，封装
			for (Element element : listActions) 
			{
				//6.1封装一个ActionMapping对象
				ActionMapping actionMapping=new ActionMapping();
				actionMapping.setName(element.attributeValue("name"));
				actionMapping.setClassName(element.attributeValue("class"));
				actionMapping.setMethod(element.attributeValue("method"));
				
				//6.2封装当前action节点下所有的结果视图
				Map<String, Result> results=new HashMap<String, Result>();
				
				//得到当前action节点下所有的result子节点
				Iterator<Element> it=element.elementIterator("result");
				while(it.hasNext())
				{
					Element ele_result=it.next();
					
					Result result=new Result();
					result.setName(ele_result.attributeValue("name"));
					result.setType(ele_result.attributeValue("type"));
					result.setPage(ele_result.getTextTrim());
					
					results.put(result.getName(), result);
				}
				
				actionMapping.setResults(results);
				
				allActions.put(actionMapping.getName(), actionMapping);
				
			}
		
		}
		catch (Exception e)
		{
			throw new RuntimeException("ActionMappingManager初始化错误",e);
		}
		
		
	}
}
