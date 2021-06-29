<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%--
	M : model
	V : view
	C : controller

	# MVC2 데이터베이스 연동예시


	1. 이클립스에서 해당 프로젝트 > WebContent > WEB-INF > lib폴더에 라이브러리 추가 
		commons-collections4-4.1.jar
		commons-dbcp2-2.2.0.jar
		commons-pool2-2.5.0.jar
		jstl-1.2.jar
		mysql-connector-java-8.0.15.jar
		
	
	2. MySql Workbench에서 DB 관련 정보 생성
	
		CREATE DATABASE MVC2_LOGIN_EX;
		USE MVC2_LOGIN_EX;
		
		CREATE TABLE MEMBER(
		    ID VARCHAR(20),
		    PW VARCHAR(20),
		    NAME VARCHAR(20),
		    TEL VARCHAR(20),
		    EMAIL VARCHAR(30),
		    FIELD VARCHAR(20),        -- 지원분야
		    SKILL VARCHAR(70),        -- 기술능력
		    MAJOR VARCHAR(20)         -- 전공분야
		);
	
	 
	3. 이클립스에서 Servers폴더에 있는 Context.xml파일에 아래의 내용 추가 
	
		<Resource 
			auth="Container" 
			driverClassName="com.mysql.cj.jdbc.Driver"
			loginTimeout="10" 
			maxWait="5000" 
			name="jdbc/pool" 
			username="root"
			password="1234" 
			type="javax.sql.DataSource"
			url="jdbc:mysql://localhost:3306/MVC2_LOGIN_EX?serverTimezone=UTC"
		/> 

 --%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>메인</title>
</head>
<body>
	<div align="center">
		<c:if test="${ null ne id }">
			${ id }님, 환영합니다.<br><br>
			<a href="update.do">입사지원정보 수정</a><br><br>
			<a href="logout.do">로그아웃</a><br><br>
			<a href="delete.do">탈퇴</a><br><br>
		</c:if>
		
		<c:if test="${ null eq id }"> <!-- id = null일 경우 (jstl-if문) -->
			<a href="join.do">회원가입</a><br><br> <!-- 회원가입 링크 join.do (servlet) -->
			<a href="login.do">로그인</a> <!-- 로그인 링크 login.do (servlet) -->
		</c:if>
	</div>	
		
	<hr>
	<br><br><br>
	
	<div align="center"> <!-- 글자 가운데로 -->
		<a href="apply.do"><img alt="입사지원하기" src="img/applyonline.png"></a>
		<!-- 입사 지원 링크 apply.do (servlet) -->
	</div> 
</body>
</html>


