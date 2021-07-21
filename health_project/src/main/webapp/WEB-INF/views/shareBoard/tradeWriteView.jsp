<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>  
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>글쓰기</title>
	  <link rel="preconnect" href="https://fonts.gstatic.com">
	  <link rel="stylesheet" href="https://fonts.googleapis.com/css2?family=Black+Han+Sans&display=swap" >
	  <link rel="stylesheet" href="/resources/css/shareBoard/shareWriteTrade.css">
	  
  <script  src="http://code.jquery.com/jquery-latest.min.js"></script>
  
    <script>
       function writeCheck(f){
    	   if (sessionNULL()){return}
    	   
    	   if($("#shareTradeTitle").val()==""){
    		   alert("제목을 입력해주세요.")
    		   $("#shareTradeTitle").focus();
    		   return;
    	   }
    	   if($("#shareTradeMoney").val()==""){
    		   alert("가격을 입력해주세요.")
    		   $("#shareTradeMoney").focus();
    		   return;
    	   }
    	   var tempPrice = Number($("#shareTradeMoney").val());
    	   if (isNaN(tempPrice)){
    		   alert('가격에는 숫자만 입력해주세요.')
    		   $("#shareTradeMoney").focus();
    		   return;
    	   }
    	   if($("#shareTradeRegion").val()==""){
    		   alert("지역을 입력해주세요.")
    		   $("#shareTradeRegion").focus();
    		   return;
    	   }
    	   if($("#file").val()==""){
    		   alert("사진을 등록해주세요.")
    		   $("#file").focus();
    		   return;
    	   }
    	   if($("#shareTradeContent").val()==""){
    		   alert("내용을 입력해주세요.")
    		   $("#shareTradeContent").focus();
    		   return;
    	   }
    	
    	   $(f).submit();
    	  }
    </script>
</head>
<body>
	<div class="writefull">
	
	<div class="labelintro"># 구매 / 판매 글 작성</div>
	
	
	<form action="/shareBoard/writeTrade.do" id="writeForm" name="writeForm" method="post" enctype="multipart/form-data">
	
		<input type="hidden" name="shareTradeId" value="${sessionScope.userId }">
		<input type="hidden" name="shareTradeNick" value="${sessionScope.userNick }" readonly>
		<input type="hidden" name="shareTradeTel" value="${sessionScope.userTel }">
		
		<table>
			<tr>
				<td width="200px"><label for="shareTradeTitle">제목</label></td>
				<td width="800px"><input type="text" id ="shareTradeTitle"  name="shareTradeTitle" placeholder="제목을 입력해주세요."></td>
			</tr>
			<tr>
				<td><label for="shareTradeCategory">카테고리</label></td>
				<td>
					<select id="shareTradeCategory" name="shareTradeCategory">
						<option value="">카테고리를 선택해주세요</option>
						<option value="구매">구매</option>
						<option value="판매">판매</option>
					</select>
				</td>
			</tr>
			<tr>
				<td><label for="shareTradeMoney">가격</label></td>
				<td><input type="text" id="shareTradeMoney" name="shareTradeMoney" placeholder="숫자만 입력해주세요."></td>
			</tr>
			<tr>
				<td><label for="shareTradeRegion">주소</label></td>
				<td><input type="text" id="shareTradeRegion" name="shareTradeRegion" placeholder="주소를 입력해주세요."></td>
			</tr>
			<tr>
				<td><label for='file'>사진 첨부</label></td>
				<td><input type="file" name="file" id="file" ></td>
			</tr>
			<tr>
				<td><label for="shareTradeContent">내용</label></td>
				<td><textarea id="shareTradeContent" name="shareTradeContent"></textarea></td>
			</tr>
		</table>

		<div class="divBtnBox" style="">
	        <input type="button" onclick="writeCheck(this.form)" class="write" value="작성완료">
        </div>
    </form>

 </div>

</body>
</html>