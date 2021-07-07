<%@page import="java.util.Vector"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>04_bList</title>
</head>
<body>
	<div align="center" style="padding-top: 100px"> <!-- css -->
		<h1>게시글 보기</h1>
		<br>
		<table border="1">
			<colgroup> <!-- 행(tr)의 열 그룹 (1행 1열 ~ X열) -->
				<col width="10%"> <!-- X행1열의 width(1번째 td: 번호 부분) -->
				<col width="40%"> <!-- X행2열의 width(2번째 td: 제목 부분) -->
				<col width="20%">
				<col width="20%">
				<col width="10%">
			</colgroup>
			<tr align="center">
				<td>번호</td>
				<td>제목</td>
				<td>작성자</td>
				<td>작성일</td>
				<td>조회수</td>
			</tr>
			<c:forEach var="board" items="${boardList}"> <!-- jstl 사용 전 *태그 라이브러리 선언 / forEach문  var="임시변수명" items="${배열명}" -->
				<c:set var="idx" value="${idx+1 }" /> <!-- jstl 변수 생성 시  기본값 = 0 , 반복문 안에  value를 idx+1로 할 경우 1부터 1씩 증감 -->
				<tr align="center">
					<td><c:out value="${idx}"/></td> <!-- 위의 jstl 증감식 출력 -->
					<td><a href="bInfo.do?num=${board.num}">${board.subject}</a></td><!-- 제목, 작성자, 작성일, 조회수 -> board(jstl에서 boardList를 넣은것)에서 가져옴 -->
					<td>${board.writer}</td>
					<td>${board.regDate}</td>
					<td>${board.readCount}</td>
				</tr>
			</c:forEach>
			<tr align="right">
				<td colspan="5"> <!-- 열병합 -->
					<input type="button" style="float: right" value="글쓰기" onclick="location.href='bWrite.do'">
				</td>
			</tr>
		</table>
	</div>					
</html>