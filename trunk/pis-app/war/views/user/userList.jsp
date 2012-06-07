<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.Map" %>
<%@ include file="/views/shared/header.jsp"%>
<div class="content">
	<div class="cph">
		<div class="section-title">
		<img alt="Dashboard" src="/images/ico-dashboard.png">User List
		</div>
		<div class="section-body">	
			<form:form method="POST" action="/user/userlist/1" commandName="model">			
					<table class="table-container">
						<tr><th>Nick Name:</th><td>
							<input type="text" class="textBox" name="nickname"/>
						</td>
						<th>Email:</th><td>
							<input type="text" class="textBox" name="email"/>
							</td>
							<th>User Name:</th><td>
							<input type="text" class="textBox" name="username"/>
							</td><td>
								<input type="submit" value="Search"/>
								<input type="reset" value="Clear"/>
								<input type="button" value="Add New" onclick="window.location.href='/user/useradd';"/>
							</td></tr>
					</table>
			</form:form>
			<div class="blackbar"></div>
			<table  style="width: 65%; border-collapse: collapse;" class="tablestyle" border="1" cellspacing="0">
				<tbody>
	        <tr class="headerstyle">
					<th>Nick Name</th>
					<th>Email</th>
					<th>User Name</th>
					<th>Operation</th>
					</tr>				
					<%
						Object obj = request.getAttribute("users");
					  List<Map<String,Object>> users = (ArrayList<Map<String,Object>>)obj;
					  if(users != null){
							for(Map<String,Object> o : users){ %>
							<tr class="rowstyle">
							<td><%= o.get("NickName")%></td>
							<td><%=o.get("Email")%></td>
							<td><%=o.get("UserName")%></td>
							<td><a href="/user/useredit/<%=o.get("Id")%>">Edit</a></td>
							</tr>
						<%} 
						}%>			
				</tbody>
			</table>
		</div>
	</div>
</div>
<%@ include file="/views/shared/footer.jsp"%>