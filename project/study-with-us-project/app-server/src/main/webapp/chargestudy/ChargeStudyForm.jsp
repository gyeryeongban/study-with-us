<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"
    trimDirectiveWhitespaces="true" %>
    
<!DOCTYPE html>
<html>
<head>
  <div style='text-align: center;'>
  <title>유료 스터디</title>
  
  <style>
  label {
    margin-right: 5px;
    text-align: right;
    display: inline-block;
    width: 60px;
  }
  </style>
  </div>
</head>

<body>
  
<h1>유료 스터디 등록</h1>
<form action='Add.jsp'>

<label for='title'>제목</label> <input id='title' type='text' name='title'>
<br>
<label for='area'>지역</label>
<select name = "area">
  <option value="" selected="selected">시/도</option>
  <option value="서울" >서울</option>
  <option value="경기">경기</option>
</select><select name = "area">
  <option value="" selected="selected">구/군</option>
  <option value="강남" >강남</option>
  <option value="서초">분당</option>
</select>
<br>

<label for='content'>내용<br><br><br><br><br><br><br><br><br><br><br><br>
<br></label>
<textarea cols="50" rows="15" placeholder="개설할 스터디 내용을 입력해주세요.">
</textarea>
<br>
<label for='startDate'>시작일</label> <input id='startDate' type='date' name='startDate'><br>
<label for='endDate'>종료일</label> <input id='endDate' type='date' name='endDate'><br>
<label for='maxMembers'>정원</label> <input id='maxMembers' type='text' name='maxMembers'><br>
<label for='price'>금액</label> <input id='price' type='text' name='price'><br>

<label for='writer'>작성자</label> <input id='writer' type='text' name='name'><br>
<br>
<div style='text-align: center;'>

<input type="reset" value="초기화">
<input type="submit" value="등록">
<button>취소</button><br>
</div>
</form>
</body>
</html>








