<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>

<jsp:include page="header.jsp">
	<jsp:param value="groupManagement" name="appName" />
</jsp:include>



<div id="main_container">
	<h1 id="page_title">
		<spring:message code="word.persons" />
	</h1>
	<nav id="context_menu">
		<a href="<c:url value="/person/new" />"><spring:message
				code="word.newPerson" /></a>
	</nav>
	<div class="content">
		<c:choose>
			<c:when test="${empty persons}">
				<div class="notification">
					<spring:message code="empty.persons" />
				</div>
			</c:when>
			<c:otherwise>


				<!-- Pagination -->
				<c:url var="pag_link" value="${pageContext.request.requestURI}" />
				<jsp:include page="pagination.jsp"></jsp:include>



				<div class="table_container">
					<table class="buttons_included">
						<tr>
							<td></td>
							<td><spring:message code="db.givenName" /></td>
							<td><spring:message code="db.familyName" /></td>
						</tr>
						<c:forEach items="${persons}" var="item">
							<tr>
								<td>
									<form action="<c:url value="/person/manage" />" class="small"
										method="get">
										<input type="hidden" name="person_id"
											value="<c:out value="${item.personId}"></c:out>" />
										<button title="" type="submit">
											<spring:message code="word.advance" />
										</button>
									</form>
								</td>
								<td><c:out value="${item.givenName}"></c:out></td>
								<td><c:out value="${item.familyName}"></c:out></td>
							</tr>
						</c:forEach>
					</table>
				</div>
				<!-- table_container -->


				<!-- Pagination -->
				<c:url var="pag_link" value="${pageContext.request.requestURI}" />
				<jsp:include page="pagination.jsp"></jsp:include>




			</c:otherwise>
		</c:choose>
	</div>
</div>

<jsp:include page="footer.jsp"></jsp:include>

