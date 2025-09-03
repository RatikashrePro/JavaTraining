<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body bgcolor="pink">
<div style="text-align: center;">
	<h1>Hioooo JSP page</h1>
	<%! int a=100; int  b=200; int c; %>
	<% c=a+b; %>
	<h1>Sum of A + B = <%=c %></h1>
	</div>
	<%! String id,salary, name, desig; %>
	<%
		id= request.getParameter("id");
		name= request.getParameter("name");
		salary= request.getParameter("salary");
		desig= request.getParameter("desig");
	%>
	<h1> My name is : <%=name %></h1>
	<h1> My id is : <%=id %></h1>
	<h1> My salary is : <%=salary %></h1>
	<h1> My desig is : <%=desig %></h1>

</body>
</html>