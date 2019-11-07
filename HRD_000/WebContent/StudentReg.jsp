<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	Integer result = (Integer)request.getAttribute("result");
%>

<form action="StudentReg" method="post" id="frm" onsubmit="return doSubmit()">
	<div>학년 <input type="text" name="syear"> (예)1 </div>
	<div>반 <input type="text" name="sclass"> (예)01 </div>
	<div>번호 <input type="text" name="sno"> (예)01 </div>
	<div>이름 <input type="text" name="sname" value="박도흠"> </div>
	<div>생년<br>월일 <input type="text" name="birth" value="20190301"> (예)20190301 </div>
	<div>성별 <label>남성<input type="radio" name="gender" value="M" checked></label>
			  <label>여성<input type="radio" name="gender" value="F"></label> 
	</div>
	<div>전화<br>번호 
		<input type="text" name="tel1" value="010"> - 
		<input type="text" name="tel2" value="2222"> -
		<input type="text" name="tel3" value="3333"> 
	</div>
	<div>
		<input type="reset" value="다시쓰기" onclick="return doReset()">
		<input type="submit" value="학생등록">		
	</div>
</form>

<script>

	  
	<% if(result != null) { %>
	
		<% if(result == 0) { %>
			alert("등록 실패")
			
		<% } else { %>	
	 		alert("등록 성공")
	 		
		<% } %>
		
	<% } %>
	
	
	
	function doReset() {
		alert('정보를 지우고 처음부터 다시 시작합니다.')		
		frm.syear.focus()
	}

	function doSubmit() {
		if(frm.syear.value.length == 0) {
			alert('학년을 입력해 주세요')
			frm.syear.focus()
			return false
			
		} else if (frm.sclass.value == "") {
			return check(frm.sclass, '반')
			
		} else if (frm.sno.value == "") {
			return check(frm.sno, '번호')
			
		} else if (frm.sname.value == "") {
			return check(frm.sname, '이름')
			
		} else if (frm.birth.value == "") {
			return check(frm.birth, '생년월일')
			
		} else if (frm.gender.value == "") {			
			alert('성별을 선택해 주세요')			
			return false
		
		} else if(frm.tel1.value == ""){		
			alert('전화번호1를 입력해 주세요')			
			return false
		} else if(frm.tel2.value == ""){		
			alert('전화번호2를 입력해 주세요')			
			return false
		} else if(frm.tel3.value == ""){		
			alert('전화번호3를 입력해 주세요')			
			return false
		}		
		return true
	}
	
	function check(ele, msg) {
		alert(msg + "을(를) 입력해 주세요")
		ele.focus()
		return false
	}
	
	
</script>











