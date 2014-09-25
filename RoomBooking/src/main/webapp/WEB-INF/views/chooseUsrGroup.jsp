<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>

<c:choose>
	<c:when test="${role.equals('admin')}">
		<jsp:include page="header.jsp">
			<jsp:param value="objectManagement" name="appName" />
		</jsp:include>
	</c:when>

	<c:when test="${role.equals('groupManagement')}">
		<jsp:include page="header.jsp">
			<jsp:param value="groupManagement" name="appName" />
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
		<spring:message code="title.choosingUserGroup" />
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
					<a href="<c:url value="/usr-group/new" />" target="_blank"><spring:message
							code="word.newUserGroup" /></a>
				</sec:authorize>
			</nav>
			<div class="content">
				<c:choose>
					<c:when test="${empty usrGroups}">
						<div class="notification">
							<spring:message code="empty.userGroups" />
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
								<c:forEach items="${usrGroups}" var="item">
									<tr>
										<td><c:choose>
												<c:when test="${source.equals('usr_group_usr')}">
													<form action="<c:url value="/usr-group-usr/new" />"
														class="small" method="get">
														<input type="hidden" name="usr_id"
															value="<c:out value="${usrId}"></c:out>" />
												</c:when>
												<c:when test="${source.equals('room_manager')}">
													<form action="<c:url value="/room-manager/new" />"
														class="small" method="get">
												</c:when>
											</c:choose> <input type="hidden" name="usr_group_id"
											value="<c:out value="${item.id.usrGroupId}"></c:out>" /> <input
											type="hidden" name="_params"
											value="<c:out value="${_params}"></c:out>" />
											<button title="" type="submit" class="button">
												<spring:message code="word.advance" />
											</button>
											</form></td>
										<td class="dark_value"><c:out
												value="${item.id.usrGroupId}"></c:out></td>
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

