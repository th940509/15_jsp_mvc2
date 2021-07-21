<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!-- *분석 2021/07/07 15:05 ~ 18:40 / 21:50 ~ 23:15 -->
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>01_main</title>
</head>
<%--

	CREATE DATABASE MVC2_BOARD_EX1;
	
	USE MVC2_BOARD_EX1;
	
	CREATE TABLE BOARD(
		NUM INT(10) NOT NULL AUTO_INCREMENT PRIMARY KEY,
	    WRITER VARCHAR(50),
	    EMAIL VARCHAR(50),
	    SUBJECT VARCHAR(50),
	    PASSWORD VARCHAR(20),
	    REG_DATE DATE,
        READ_COUNT INT,
	   	CONTENT VARCHAR(200)
	);
	
	SELECT * FROM BOARD;

--%>

<body>
	<div align="center" style="padding-top: 100px"> <!-- padding-top 속성: 요소 위쪽의 안쪽 여백 설정 속성 -->
		<img src="img/jsp.PNG" alt="jsp심볼" width="800px" height="500px"><br><br><br><br>
		<input type="button" value="게시판 보기" onclick="location.href='bList.do'"> <!-- 버튼 클릭 시  bList.do servlet으로 이동 -->
	</div>
</body>
</html>