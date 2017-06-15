<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/"; 
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>Insert title here</title>
</head>
<body>

		<button id="but">按钮1</button>
	
</body>
<script type="text/javascript" src='jquery-3.2.1.min.js'></script>
<script type="text/javascript">

$("#but").click(function(){
	var a={
			"access_token":"ACCESS_TOKEN",
		     "button":[
		               {	
		                    "type":"click",
		                    "name":"今日歌曲",
		                    "key":"V1001_TODAY_MUSIC"
		                },
		                {
		                     "name":"菜单",
		                     "sub_button":[
		                     {	
		                         "type":"view",
		                         "name":"搜索",
		                         "url":"http://www.soso.com/"
		                      },
		                      {
		                           "type":"miniprogram",
		                           "name":"wxa",
		                           "url":"http://mp.weixin.qq.com",
		                           "appid":"wx286b93c14bbf93aa",
		                           "pagepath":"pages/lunar/index"
		                       },
		                      {
		                         "type":"click",
		                         "name":"赞一下我们",
		                         "key":"V1001_GOOD"
		                      }]
		                 }]
		           };
	
	    $.ajax({
	        type: "POST",
	        url: "https://api.weixin.qq.com/cgi-bin/menu/create?access_token=ACCESS_TOKEN",
	        dataType:"json",
	        data:"button={ 'type':'click','name':'wode','key':'vq234'}&signature='1234'&timestamp='1243'&nonce='1243'" ,
	        success: function(data) {
	           alert("成功");
	            },
	    });    
	    
	})

</script>
</html>