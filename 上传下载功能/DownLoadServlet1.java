import java.io.FileInputStream;
import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.IOUtils;




public class DownLoadServlet1 extends HttpServlet
{
	@Override
	public void doGet(HttpServletRequest req, HttpServletResponse response)
			throws ServletException, IOException 
	{
		//获取两个头的参数
				String filename="/Users/XXY/Desktop/test.png";
				String contentType=this.getServletContext().getMimeType(filename);
				String contentDisposition="attachment;filename=a.mp3";
				
				//绑定指定文件的输入流
				FileInputStream input=new FileInputStream(filename);
				
				//设置两个头
				response.setHeader("Content-Type", contentType);
				response.setHeader("Content-Disposition", contentDisposition);
				
				//获取绑定客户端的流
				ServletOutputStream output=response.getOutputStream();
				
				IOUtils.copy(input, output);
				input.close();
	}
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException 
	{
		//获取两个头的参数
		String filename="/Users/XXY/Desktop/test.png";
		String contentType=this.getServletContext().getMimeType(filename);
		String contentDisposition="attachment;filename=a.mp3";
		
		//绑定指定文件的输入流
		FileInputStream input=new FileInputStream(filename);
		
		//设置两个头
		response.setHeader("Content-Type", contentType);
		response.setHeader("Content-Disposition", contentDisposition);
		
		//获取绑定客户端的流
		ServletOutputStream output=response.getOutputStream();
		
		IOUtils.copy(input, output);
		input.close();
	}

}
