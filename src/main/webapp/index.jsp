<!DOCTYPE html >
<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/"; 
%>
<html>
<head>
<meta http-equiv='Content-Type' content='text/html; charset=utf-8'>

<script type="text/javascript" src="/js/jquery-3.2.1.js"></script>
<script type="text/javascript" src="<%=basePath %>js/calendar.js"></script> 

<link rel="stylesheet" type="text/css" href="<%=basePath %>/js/calendar.css"/>
<link href='http://fonts.googleapis.com/css?family=Roboto' rel='stylesheet' type='text/css'>

<title>Insert title here</title>
<style type="text/css">

</style>
</head>
<body>

	<form class="form1" action="<%=basePath %>/trans/do" method="post">
	<table align="center">
		<tr><td> 订单号：<input type="text" name="tid" /> </td> 
			<td> 店铺：<input type="text" name="seller_nick" /> </td> 
		</tr>
		<tr>
			<td> 创建日期：
						<input type="text" id="dt" >
				</td>
			<!-- <td> 创建日期结束：<input type="text" name="createdEnd" /> </td> -->
			<!-- <td> 状态：<input type="text" name="status" /> </td> -->
		</tr>
		<tr><td><input type="submit" /></td></tr>
	</table>
	</form>
	
	
<script type="text/javascript">


</script>
	
</body>
</html>
