<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	String view = (String)request.getAttribute("view");


%>    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>고교성적관리</title>
</head>
<body>
	<div id="container">
		<header>
			(과정평가형 정보처리산업기사) 고교성적관리 프로그램 ver2019-06
		</header>
		<nav>
			<a href="StudentReg">학생등록</a> 
			<a href="ScoreReg">성적입력</a>
			<a href="ScoreList">성적조회</a>
			<a href="ScoreList2">성적조회2</a>
			<a href="ScoreList3">성적조회3</a>
			<a href="ClassAnaly">반별통계</a>
			<a href="index.jsp">홈</a>			
		</nav>
		<section>
		<% if(view != null) { %>
			<jsp:include page="<%=view %>"/>
		<% } else { %>
			<pre>
			과정평가형 자격 CBQ
			국가직무능력(NCS : National Competency Standars) 으로
			
			산업현장 중심의
			
			알고 있는
			</pre>
		<% } %>
		</section>
		<footer>
			HRDKOREA Copyright&copy;2019 All rights reserved. Humans
		</footer>
	</div>
</body>
</html>