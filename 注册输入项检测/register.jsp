<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'register.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
<script type="text/javascript">
//创建异步对象
function createXMLHttpRequest()//创建异步对象
{
	var xml=null;
	try//适用于大多数浏览器
	{
		xml=new XMLHttpRequest();
	}
	catch (e) 
	{
		try//适用于ie6
		{
			xml=new ActiveXObject("Msxm12.XMLHTTP");
		}
		catch (e)
		{
			try//适用于ie5
			{
				xml=new ActiveXObject("Microsoft.XMLHTTP");
			}
			catch (e)
			{
				
			}
		}
	}
	return xml;
};


window.onload=function()
{
	//获取文本框，添加响应函数
	var usrenameEle=document.getElementById("usernameEle");
	usrenameEle.onblur=function()
	{
		var xmlHttp=createXMLHttpRequest();
		xmlHttp.open("POST","<c:url value='/ValiedUsernameServlet'/>",true);
		xmlHttp.setRequestHeader("Content-Type","application/x-www-form-urlencoded");
		xmlHttp.send("username="+usrenameEle.value);
		
		
		xmlHttp.onreadystatechange=function()
		{
			if(xmlHttp.readyState==4 && xmlHttp.status==200)
			{
				var text=xmlHttp.responseText;
				if(text=="1")
					document.getElementById("errorSpan").innerHTML="用户名已注册";
				else
					;
			}
		};
	};
};


</script>
  </head>
  
  <body>
    <h1>注册</h1>
    
    
   <form action="" method="post">
	用户名：<input type="text" name="username" id="usernameEle"/><span color="red" id="errorSpan"></span><br/>
	密　码：<input type="password" name="password"/><br/>
	<input type="submit" value="注册"/>
	</form>
    
    
  </body>
</html>
