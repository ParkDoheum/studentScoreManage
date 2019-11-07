<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.*" %>
<%@ page import="hrd.*" %>    
<%
	List<ScoreListVo> list = (List<ScoreListVo>) request.getAttribute("list");
	GroupVo groupVo = (GroupVo)request.getAttribute("groupVo");
%>    
<h1>학생성적조회</h1>
<table>
	<tr>
		<th>학년-반-번호</th>
		<th>이름</th>
		<th>성별</th>
		<th>국어</th>
		<th>영어</th>
		
		<th>수학</th>
		<th>총점</th>
		<th>평균</th>
	</tr>
	<% if(list != null) { %>
	
		<% for(ScoreListVo vo : list) { %>		
			<tr>
				<td><%=vo.getPk() %></td>
				<td><%=vo.getSname() %></td>
				<td><%=vo.getGender()%></td>
				<td><%=vo.getKor()%></td>
				<td><%=vo.getEng()%></td>
				
				<td><%=vo.getMath()%></td>
				<td><%=vo.getTotalSum()%></td>
				<td><%=String.format("%.1f", vo.getTotalAvg()) %></td>
			</tr>
		<% } %>	
	<% } %>
	<% if(groupVo != null) { %>
		<tr>
			<td></td><td></td>
			<td>학년총점</td>
			<td><%=groupVo.getSumKor() %></td>
			<td><%=groupVo.getSumEng() %></td>
			
			<td><%=groupVo.getSumMath() %></td>			
			<td></td><td></td>			
		<tr>
		<tr>
			<td></td><td></td>
			<td>학년평균</td>
			<td><%=String.format("%.1f", groupVo.getAvgKor()) %></td>
			<td><%=String.format("%.1f", groupVo.getAvgEng()) %></td>
					
			<td><%=String.format("%.1f", groupVo.getAvgMath()) %></td>
			<td></td><td></td>
		<tr>
	<% } %>
</table>
