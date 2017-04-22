package servlet;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadBase;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.io.IOUtils;

import com.sun.org.apache.bcel.internal.generic.INSTANCEOF;

import sun.org.mozilla.javascript.internal.ast.IfStatement;

import cn.itcast.commons.CommonUtils;
public class UpLoadServlet2 extends HttpServlet 
{
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException
	{
		
	}
	public void doPost(HttpServletRequest request, HttpServletResponse response)
	throws ServletException, IOException 
	{
		DiskFileItemFactory factory=new DiskFileItemFactory(1024*20,
				new File("/Users/XXY/Desktop/temp"));
		
		System.out.println("test");
		ServletFileUpload sfu=new ServletFileUpload(factory);
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8	");
		
		ServletContext context=this.getServletContext();
		//sfu.setFileSizeMax(100*1024);
		
		try 
		{
			List<FileItem> fileitemList=sfu.parseRequest(request);
			FileItem fi1=fileitemList.get(0);
			FileItem fi2=fileitemList.get(1);
			
			//1、获取保存的文件夹根目录
			String rootPath=context.getRealPath("/WEB-INF/files");
			
			//2、截取真实文件名
			String filename=fi2.getName();
			int index=filename.lastIndexOf("\\");
			if(index!=-1)
				filename=filename.substring(index+1);
			
			//3、处理文件重名问题，添加uuid前缀
			String uuid=CommonUtils.uuid();
			filename=uuid+"_"+filename;
			
			//4、得到hashcode
			int hashCode=filename.hashCode();
			String hex=Integer.toHexString(hashCode);
			
			//5、获得hex的前两位字母，与root连接在一起，生成一个完整的路径
			File dirFile=new File(rootPath+"/"+hex.charAt(0)+"/"+hex.charAt(1));
			dirFile.mkdirs();//创建目录链
			File destFile=new File(dirFile, filename);
			fi2.write(destFile);
			
		} 
		catch (FileUploadException e)//文件超过大小限制，就会抛出这个异常
		{
			if(e instanceof FileUploadBase.FileSizeLimitExceededException)
			{
				request.setAttribute("msg", "您上传的文件超过了100KB");
				request.getRequestDispatcher("/form1.jsp").forward(request, response);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		
	}

}
