<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>

<jsp:include page="header.jsp">
	<jsp:param value="groupManagement" name="appName" />
</jsp:include>



<div id="main_container">
	<h1 id="page_title">
		<spring:message code="title.choosingPerson" />
	</h1>
	<c:choose>
		<c:when test="${isSourceIncorrect.equals(true)}">
			<div class="error">
				<spring:message code="incorrect.source" />
			</div>
			<span class="vertical_separator"></span>
		</c:when>
		<c:otherwise>
			<nav id="context_menu">
				<sec:authorize access="hasRole('ROLE_GROUP')">
					<a href="<c:url value="/person/new" />" target="_blank"><spring:message
							code="word.newPerson" /></a>
				</sec:authorize>
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
										<td><c:choose>
												<c:when test="${source.equals('usr')}">
													<form action="<c:url value="/usr/new" />" class="small"
														method="get">
												</c:when>
												<c:when test="${source.equals('person_group_person')}">
													<form action="<c:url value="/person-group-person/new" />"
														class="small" method="get">
														<input type="hidden" name="person_group_id"
															value="<c:out value="${personGroupId}"></c:out>" />
												</c:when>
											</c:choose> <input type="hidden" name="person_id"
											value="<c:out value="${item.personId}"></c:out>" /> <input
											type="hidden" name="_params"
											value="<c:out value="${_params}"></c:out>" />
											<button title="" type="submit" class="button">Choose</button>
											</form></td>
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
		</c:otherwise>
	</c:choose>
</div>

<jsp:include page="footer.jsp"></jsp:include>

