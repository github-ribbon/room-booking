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
	<c:when test="${role.equals('client')}">
		<jsp:include page="header.jsp">
			<jsp:param value="roomBooking" name="appName" />
		</jsp:include>
	</c:when>
	<c:when test="${role.equals('confirmer')}">
		<jsp:include page="header.jsp">
			<jsp:param value="bookingConfirmation" name="appName" />
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
		<spring:message code="title.choosingBuilding" />
	</h1>
	<c:choose>
		<c:when test="${isCampusPKIncorrect.equals(true)}">
			<div class="error">
				<spring:message code="incorrect.campusPK" />
			</div>
			<span class="vertical_separator"></span>
		</c:when>
		<c:when test="${isSourceIncorrect.equals(true)}">
			<div class="error">
				<spring:message code="incorrect.source" />
			</div>
			<span class="vertical_separator"></span>
		</c:when>
		<c:otherwise>
			<nav id="context_menu">
				<sec:authorize access="hasRole('ROLE_ADMIN')">
					<a href="<c:url value="/building/new?campus_id=${campusId}" />"
						target="_blank"><spring:message code="word.newBuilding" /></a>
				</sec:authorize>
			</nav>
			<div class="content">
				<c:choose>
					<c:when test="${empty buildings}">
						<div class="notification">
							<spring:message code="empty.buildings" />
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
									<td class="key"><spring:message code="db.campusId" /></td>
									<td class="key"><spring:message code="db.buildingId" /></td>
									<td><spring:message code="db.name" /></td>
									<td><spring:message code="db.description" /></td>
								</tr>
								<c:forEach items="${buildings}" var="item">
									<tr>
										<td><c:choose>
												<c:when
													test="${source.equals('res-list-by-day') && role.equals('confirmer')}">
													<form
														action="<c:url value="/confirmer/reservation-list-by-day-form" />"
														class="small" method="get">
												</c:when>
												<c:when
													test="${source.equals('res-list-by-day') && role.equals('client')}">
													<form
														action="<c:url value="/client/reservation-list-by-day-form" />"
														class="small" method="get">
												</c:when>
												<c:when
													test="${source.equals('reservation')&&role.equals('client')}">
													<form action="<c:url value="/client/new" />" class="small"
														method="get">
												</c:when>
												<c:when
													test="${source.equals('room')&&role.equals('admin')}">
													<form action="<c:url value="/room/new" />" class="small"
														method="get">
												</c:when>
												<c:when test="${source.equals('ra')&&role.equals('admin')}">
													<form action="<c:url value="/room-attribute/new" />"
														class="small" method="get">
												</c:when>
												<c:when
													test="${source.equals('room_manager') && role.equals('admin')}">
													<form action="<c:url value="/room-manager/new" />"
														class="small" method="get">
												</c:when>
											</c:choose> <input type="hidden" name="campus_id"
											value="<c:out value="${item.id.campusId}"></c:out>" /> <input
											type="hidden" name="building_id"
											value="<c:out value="${item.id.buildingId}"></c:out>" /> <input
											type="hidden" name="_params"
											value="<c:out value="${_params}"></c:out>" />
											<button title="" type="submit" class="button">
												<spring:message code="word.choose" />
											</button>
											</form></td>
										<td class="dark_value"><c:out value="${item.id.campusId}"></c:out>
										</td>
										<td class="dark_value"><c:out
												value="${item.id.buildingId}"></c:out></td>
										<td><c:out value="${item.name}"></c:out></td>
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

