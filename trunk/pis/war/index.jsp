<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">

<html>
  <head>
    <meta http-equiv="content-type" content="text/html; charset=UTF-8">
    <title>Login</title>
    <link rel="stylesheet" type="text/css" href="/Script/resources/css/ext-all.css" />
	<script type="text/javascript" src="/Script/adapter/ext/ext-base.js"></script> 
	<script type="text/javascript" src="/Script/ext-all-debug.js"></script>
	<script type="text/javascript" src="/Script/ext-util.js"></script>
	<script type="text/javascript" src="/Script/index.js"></script>
  	<style type="text/style">
  		#form{
  			border:1px solid #CCCCCC;
  		}
  		#form.tr.td{
  			padding:5px;
  		}
  	</style>
  </head>	
  <body>  
	 <div style="margin-top:300px">
	 	<table id="form" align="center">
	 		<tr>
	 		<td>用户名：</td>
	 		<td><input type="text" id="UserId" /></td>
	 		</tr>
	 		
	 		<tr>
	 		<td>密码：</td>
	 		<td><input type="text" id="Password"/></td>
	 		</tr>
	 		
	 		<tr>
	 		<td></td>
	 		<td><input type="button" id="btnLogin" value="登陆"/>
	 			<input type="button" id="btnCancel" value="退出"/></td>
	 		</tr>
	 	</table>
	 </div> 
  </body>
</html>
