<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>

<c:choose>
	<c:when test="${role.equals('groupManagement')}">
		<jsp:include page="header.jsp">
			<jsp:param value="groupManagement" name="appName" />
		</jsp:include>
	</c:when>

	<c:when test="${role.equals('client')}">
		<jsp:include page="header.jsp">
			<jsp:param value="roomBooking" name="appName" />
		</jsp:include>
	</c:when>

	<c:otherwise>
		<jsp:include page="header.jsp">
			<jsp:param value="" name="appName" />
		</jsp:include>
	</c:otherwise>

</c:choose>


<div id="main_container">
	<h1 id="page_title">
		<spring:message code="title.choosingPersonGroup" />
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
					<a href="<c:url value="/person-group/new" />" target="_blank"><spring:message
							code="word.newPersonGroup" /></a>
				</sec:authorize>
			</nav>
			<div class="content">
				<c:choose>
					<c:when test="${empty personGroups}">
						<div class="notification">
							<spring:message code="empty.personGroups" />
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
									<td class="key"><spring:message code="db.name" /></td>
									<td><spring:message code="db.description" /></td>
								</tr>
								<c:forEach items="${personGroups}" var="item">
									<tr>
										<td><c:choose>
												<c:when
													test="${source.equals('reservation') && action.equals('create')}">
													<form action="<c:url value="/client/new" />" class="small"
														method="get">
												</c:when>
												<c:when
													test="${source.equals('reservation') && action.equals('manage')}">
													<form action="<c:url value="/client/manage" />"
														class="small" method="get">
														<input type="hidden" name="reservation_id"
															value="<c:out value="${reservationId}"></c:out>" />
												</c:when>
												<c:when test="${source.equals('person_group_person')}">
													<form action="<c:url value="/person-group-person/new" />"
														class="small" method="get">
														<input type="hidden" name="person_id"
															value="<c:out value="${personId}"></c:out>" />
												</c:when>
											</c:choose> <input type="hidden" name="person_group_id"
											value="<c:out value="${item.id.personGroupId}"></c:out>" />
											<input type="hidden" name="_params"
											value="<c:out value="${_params}"></c:out>" />
											<button title="" type="submit" class="button">
												<spring:message code="word.advance" />
											</button>
											</form></td>
										<td class="dark_value"><c:out
												value="${item.id.personGroupId}"></c:out></td>
										<td><c:out value="${item.description}"></c:out></td>
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

