<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<link rel="stylesheet" type="text/css" href="css/back.css" />
<title>图书管理</title>

</head>
<body>
	<h2 id="headline">
		<a href="index.jsp">图书管理</a>
	</h2>
	<div id="main"> 
		<div id="content">
			<table>
				<thead>
					<tr> 
						<th class="isbn">ISBN号</th>
						<th class="sm">书名</th>
						<th class="zzbh">作者编号</th>
						<th class="cbs">出版社</th>
						<th class="cbrq">出版日期</th>
						<th class="jg">价格</th>
						<th class="cz">操作</th>
					</tr>
				</thead>
			</table>

			<div class="scrollable"> 
					<table> 
							<s:iterator value="books">
								<tr> 
									<td class="isbn"> <s:property value="isbn" /> </td>

									<td class="sm">
										<a class="titlelink" 
											href="<s:url action='updateBook'>  <s:param name='isbn' value='isbn' /> </s:url>">
											<s:property value="title" />
										</a>
									</td>

									<td class="zzbh" >
										<a class="titlelink" 
											href="<s:url action='clickAuthor'>  <s:param name='authorID' value='authorID' /> </s:url>">
											<s:property value="authorID" />
										</a> 
									</td>
									
									<td class="cbs"><s:property value="publisher" /></td>
									<td class="cbrq"><s:property value="publishDate" /></td>
									<td class="jg">￥<s:property value="price" /></td>
									
									<td class="cz" colspan="2">
										<a href="<s:url action='updateBook'> <s:param name='isbn' value='isbn' /> </s:url>">
											编辑 
										</a>  
										
										<a class="danger" 
											href="<s:url action='delBook'> <s:param name='isbn' value='isbn' /> </s:url>"
											onclick="javascript:if(confirm('确定删除？')){alert('删除成功!');return true;}return false;">
											删除
										</a>
									</td>
								</tr>
							</s:iterator>  
					</table>
			</div> 
			<br />
			<br />
			<a id="back" href="index.jsp">返回</a>
		</div>
	</div>
</body>
</html>