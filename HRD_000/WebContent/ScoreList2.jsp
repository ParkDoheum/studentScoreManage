<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.*" %>
<%@ page import="hrd.*" %>    
<%
	List<ScoreVo> score = (List<ScoreVo>) request.getAttribute("score");
	List<StudentVo> student = (List<StudentVo>) request.getAttribute("student");
	
%>    
<h1>학생성적조회2</h1>
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
	<% if(score != null) { %>
		
		<% for(int i=0; i<score.size(); i++) { %>	
		<%
			ScoreVo scVo = score.get(i);
			StudentVo stVo = student.get(i);
		
			String pk = String.format("%s-%s-%s", 
					scVo.getSyear(), scVo.getSclass(), scVo.getSno());
			
			int kor = scVo.getKor();
			int eng = scVo.getEng();
			int math = scVo.getMath();
			int sum = kor + eng + math;
			double avg = (double)sum / 3;
			
			sumKor += kor;
			sumEng += eng;
			sumMath += math;
			
		%>	
			<tr>
				<td><%=pk %></td>
				<td><%=stVo.getSname() %></td>
				<td><%=stVo.getGender().equals("M") ? "남" : "여"%></td>
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
		<td><%=String.format("%.1f", (double)sumKor / score.size()) %></td>
		<td><%=String.format("%.1f", (double)sumEng / score.size()) %></td>
				
		<td><%=String.format("%.1f", (double)sumMath / score.size()) %></td>
		<td></td><td></td>
	<tr>
	
	
</table>
