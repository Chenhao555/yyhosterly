<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@include  file="/WEB-INF/jspf/basePath.jspf" %>
<%
    Object bp1=application.getAttribute("BASEPATH");
    if(null==bp1){
        String path = request.getContextPath();
        String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path;
        application.setAttribute("BASEPATH", basePath);
    }
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, user-scalable=no, initial-scale=1.0,
			maximum-scale=1.0, minimum-scale=1.0"/>
		<title></title>
<title>Insert title here</title>
</head>
<link rel="stylesheet" href="${BASEPATH}/static/css/bootstrap.css" />
	<style>
		.container{
			border:1px solid red;
		}
		.col-md-9{
			background-color: red;
		}
		.col-md-4{
			background-color:yellow;
		}
	</style>
<body>
	<div class="contianer">
			<div class="row">
				<div class="col-md-9">col-md-9</div>
				<div class="col-md-4">col-md-3</div>
			</div>
		</div>
		<ul class="list-inline">
			<li>1</li>
			<li>2</li>
			<li>3</li>
			<li>4</li>
		</ul>
		<script type="text/javascript" src="${BASEPATH}/static/js/jquery-1.12.4.js" ></script>
		<script type="text/javascript" src="${BASEPATH}/static/js/bootstrap.js" ></script>
</body>
</html>