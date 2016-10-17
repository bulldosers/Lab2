<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<link rel="stylesheet" type="text/css" href="css/back.css" />
	<style type="text/css" >
		
	</style>
	
	<title>图书管理</title>
</head>

<body>
	<h2 id="headline">
		<a href="index.jsp">图书管理</a>
	</h2>
	
	<div id="main">
		<div id="header">
			<ul>
				<li><a href="<s:url action='AuthorsSearch' />">查询作者</a></li>
				<li><a href="<s:url action='booksArray' />" >浏览图书</a></li>  
				<li><a href="<s:url action='Addbook' />">添加图书</a></li>
			</ul>
		</div>
		<h1 id="welcome">
			欢迎使用图书管理系统！
		</h1> 
		<h2 id = "PSinfo">PS:如果连接数据库超时，请刷新页面即可！</h2>
	</div>
</body>
</html>