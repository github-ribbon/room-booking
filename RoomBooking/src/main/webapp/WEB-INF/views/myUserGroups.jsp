<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>

<jsp:include page="header.jsp">
	<jsp:param value="userPanel" name="appName" />
</jsp:include>



<div id="main_container">
	<h1 id="page_title">
		<spring:message code="title.myUserGroups" />
	</h1>
	<div class="content">
		<c:choose>
			<c:when test="${empty usrGroups}">
				<div class="notification">
					<spring:message code="empty.usrAuths" />
				</div>
			</c:when>
			<c:otherwise>
				<div class="table_container">
					<table>
						<tr>
							<td class="key"><spring:message code="db.name" /></td>
							<td><spring:message code="db.description" /></td>
						</tr>
						<c:forEach items="${usrGroups}" var="item">
							<tr>
								<td class="dark_value"><c:out value="${item.id.usrGroupId}"></c:out></td>
								<td><c:out value="${item.description}"></c:out></td>
							</tr>
						</c:forEach>
					</table>
				</div>
				<!-- table_container -->

			</c:otherwise>
		</c:choose>
	</div>
</div>

<jsp:include page="footer.jsp"></jsp:include>

