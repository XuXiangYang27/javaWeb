<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>


<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>客户列表</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->

  </head>
  
  <body>
<h3 align="center">客户列表</h3>
<table border="1" width="70%" align="center">
	<tr>
		<th>客户姓名</th>
		<th>性别</th>
		<th>生日</th>
		<th>手机</th>
		<th>邮箱</th>
		<th>描述</th>
		<th>操作</th>
	</tr>
<!-- 
		items="${requestScope.cstmList}":要遍历的集合
		var="cstm"  遍历出来的对象
		
		现在我们要遍历的是 pageBean中beanList这个集合
 -->
<c:forEach items="${requestScope.pb.beanList}" var="cstm">
	<tr>
		<td>${cstm.cname }</td>
		<td>${cstm.gender }</td>
		<td>${cstm.birthday }</td>
		<td>${cstm.cellphone }</td>
		<td>${cstm.email }</td>
		<td>${cstm.description }</td>
		<td>
			<a href="<c:url value='/CustomerServlet?method=preEdit&cid=${cstm.cid }'/>">编辑</a>
			<a href="<c:url value='/CustomerServlet?method=delete&cid=${cstm.cid }'/>">删除</a>
		</td>
	</tr>
</c:forEach>
</table>

<br/>
<!-- 
	给出分页相关的连接
 -->
<center>
第${pb.pc}页/共${pb.tp}页

<a href="${pb.url}&pc=1">首页</a>
<c:if test="${pb.pc>1 }">
<a href= "${pb.url}&pc=${pb.pc-1}" >上一页</a>
</c:if>
<!-- 计算begin 和 end -->
<c:choose>
	<%--当总页面<=10时,把所有的页标都显示出来 --%>
	<c:when test="${pb.tp<=10}">
		<c:set var="begin" value="1"/>
		<c:set var="end" value="${pb.tp}"/>
	</c:when>
	<%--当总页面>10时，通过公式计算：begin=当前页-5，end=当前页+4 --%>
	<c:otherwise>
		<c:set var="begin" value="${pb.pc-5}"/>
		<c:set var="end" value="${pb.pc+4}"/>
		<%--头溢出 --%>
		<c:if test="${begin<1}">
			<c:set var="begin" value="1"/>
			<c:set var="end" value="10"/>
		</c:if>
		<%--尾溢出 --%>
		<c:if test="${end>pb.tp}">
			<c:set var="begin" value="${end-9}"/>
			<c:set var="end" value="${pb.tp}"/>
		</c:if>
	</c:otherwise>
</c:choose>

<!-- 循环遍历页码列表 -->
<c:forEach var="i" begin="${begin}" end="${end}">
	<c:choose>
		<c:when test="${i eq pb.pc }">
			[${i}] 
		</c:when>
		<c:otherwise>
			<a href="${pb.url}&pc=${i}">${i}</a>
		</c:otherwise>
	</c:choose>	
</c:forEach>

<c:if test="${pb.pc<pb.tp }">
<a href="${pb.url}&pc=${pb.pc+1}">下一页</a>
</c:if>
<a href="${pb.url}&pc=${pb.tp}">尾页</a>

</center>
  </body>
</html>
