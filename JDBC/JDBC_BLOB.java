package BigData;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Blob;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.sql.rowset.serial.SerialBlob;

import jdbcUtils.JdbcUtils;

import org.apache.commons.io.IOUtils;
import org.junit.Test;
public class Demo01 
{
	/*
	 * 把mp3保存到数据库中
	 */
	@Test
	public void fun1() throws ClassNotFoundException, IOException, SQLException
	{
		Connection con=JdbcUtils.getConnection();
		String sql="insert into bigdata values(?,?,?)";
		PreparedStatement pstmt=con.prepareStatement(sql);
		pstmt.setInt(1, 1);
		pstmt.setString(2,"小幸运.mp3");
		/*
		 * Blob是个接口,需要得到Blob,Blob接口的实现类（SerialBlob(bytes)）可以通过byte[]来创建
		 *  1、我们有的是文件，目标是Blob
		 *  2、先把文件变成byte[]，通过commoms-io这个jar包很方便
		 *  3、再使用byte[]创建Blob
		 */
		//把文件转换成byte[]
		byte[] bytes=IOUtils.toByteArray(new FileInputStream("/Users/XXY/Music/小幸运.mp3"));
		//得到Blob对象
		Blob blob=new SerialBlob(bytes);
		pstmt.setBlob(3, blob);
		pstmt.executeUpdate();
	}
	/*
	 * 从数据库中取mp3
	 */
	@Test
	public void fun2() throws ClassNotFoundException, IOException, SQLException
	{
		Connection con=JdbcUtils.getConnection();
		String sql="select * from bigdata";
		PreparedStatement pstmt=con.prepareStatement(sql);
		ResultSet rs=pstmt.executeQuery();
		if (rs.next())
		{
			Blob blob=rs.getBlob("data");
			/*
			 * 把Blob对象变成硬盘上的文件
			 * 1、通过Blob获得输入流
			 * 2、自己创建一个输出流
			 * 3、把输入流的数据写到输出流
			 */
			System.out.println(111);
			InputStream is=blob.getBinaryStream();
			FileOutputStream os=new FileOutputStream("/Users/XXY/许湘扬.mp3");
			IOUtils.copy(is, os);
		}
	}
}
