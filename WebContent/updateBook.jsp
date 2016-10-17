<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" type="text/css" href="css/back.css" />
<title>图书管理</title>
</head>
<body>
	<h2 id="headline">
		<a href="index.jsp">图书管理</a>
	</h2>
	<div id="main"> 
		<div id="content"> 
			<h3 id="add-book-head">修改图书信息</h3>
			<div id="inputForm"> 
				<s:form theme="simple" action="commitUpdatedBook" id="bookAdded">
					<s:hidden name="modify" value="true" />
					<label class="addBookLbl" for="bookAdded_newBook_isbn">ISBN(编号)：</label>
					<s:textfield name="newBook.isbn" readonly="true" /> 
					<br />
					<br />

					<label class="addBookLbl" for="bookAdded_newBook_title">书名：</label>
					<s:textfield name="newBook.title" /> 
					<br />
					<br />

					<label class="addBookLbl" for="bookAdded_newBook_authorID">作者编号：</label>
					<s:textfield name="newBook.authorID" readonly="true" /> 
					<br />
					<br />

					<label class="addBookLbl" for="bookAdded_newBook_publisher">出版社：</label>
					<s:textfield name="newBook.publisher" /> 
					<br />
					<br />

					<label class="addBookLbl" for="bookAdded_newBook_publishDate">出版日期：</label>
					<s:textfield name="year" class="date" />年
					<s:textfield name="month" class="date" />月
					<s:textfield name="day" class="date" />日 
					<br />
					<br />

					<label class="addBookLbl" for="bookAdded_newBook_price">价格：￥</label>
					<s:textfield name="newBook.price" />
					<s:fielderror>
						<s:param>newBook.invail</s:param>
					</s:fielderror> 
					<br />
					<br />

					<s:submit value="修改" id="modify_submit" /> 
				</s:form>

			</div>
		</div>
		<br />
		<br />
		<a id="back2" href="index.jsp">返回</a>
	</div>
</body>
</html>