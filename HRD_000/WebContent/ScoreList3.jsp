<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.*" %>
<%@ page import="hrd.*" %>    
<%
	List<StudentScoreVo> list = (List<StudentScoreVo>) request.getAttribute("list");
%>    
<h1>학생성적조회3</h1>
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
	<%
		int sumKor = 0;
		int sumEng = 0;
		int sumMath = 0;			
	%>
	<% if(list != null) { %>
		
		<% for(StudentScoreVo vo : list) { %>	
		<%
			String pk = String.format("%s-%s-%s", 
					vo.getSyear(), vo.getSclass(), vo.getSno());
			
			int kor = vo.getKor();
			int eng = vo.getEng();
			int math = vo.getMath();
			int sum = vo.getTotalSum();
			double avg = (double)sum / 3;
			
			sumKor += kor;
			sumEng += eng;
			sumMath += math;
			
			String gender = vo.getGender();
			
			if(gender.equalsIgnoreCase("m")) {
				gender = "남";
			} else if(gender.equalsIgnoreCase("f")) {
				gender = "여";
			} else {
				gender = "";
			}
			
		%>	
			<tr>
				<td><%=pk %></td>
				<td><%=vo.getSname() %></td>
				<td><%=gender%></td>
				<td><%=kor%></td>
				<td><%=eng%></td>
				
				<td><%=math%></td>
				<td><%=sum%></td>
				<td><%=String.format("%.1f", avg) %></td>
			</tr>
		<% } %>	
	<% } %>
	<tr>
		<td></td><td></td>
		<td>학년총점</td>
		<td><%=sumKor %></td>
		<td><%=sumEng %></td>
		
		<td><%=sumMath %></td>			
		<td></td><td></td>			
	<tr>
	<tr>
		<td></td><td></td>
		<td>학년평균</td>
		<td><%=String.format("%.1f", (double)sumKor / list.size()) %></td>
		<td><%=String.format("%.1f", (double)sumEng / list.size()) %></td>
				
		<td><%=String.format("%.1f", (double)sumMath / list.size()) %></td>
		<td></td><td></td>
	<tr>
	
	
</table>
