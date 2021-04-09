<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>사원 목록</title>
	<link rel="stylesheet" href="css/emplist_style.css">
</head>
<body>
<div id="container">
	<h1>사원목록</h1>
	<table class="rwd-table">
		<thead id="thead">
			<tr>
				<td>사원번호</td>
				<td>사원명</td>
				<td>직책</td>
				<td>직속상사</td>
				<td>급여</td>
				<td>부서</td>
				<td>입사일</td>
			</tr>
		</thead>
		<tbody id="tbody">
			<c:forEach var="emp" items="${list }">
				<tr>
					<td>${emp.no }</td>
					<td>${emp.name}</td>
					<td>${emp.title.name }</td>
					<td>
						<c:choose>
							<c:when test="${emp.manager eq null }">
							</c:when>
							<c:otherwise>
								${emp.manager.name } ( ${emp.manager.no } )
							</c:otherwise>
							</c:choose></td>
					<td>${emp.salary }</td>
					<td>${emp.dept.name }</td>
					<td><fmt:formatDate value="${emp.hireDate }" pattern="yyyy년 MM월 dd일"/></td>
					
				</tr>
			</c:forEach>
		</tbody>
	</table>
	</div>
</body>
</html>