<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<c:choose>
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
		<spring:message code="title.reservationChildren" />
	</h1>

	<c:choose>
		<c:when test="${isReservationPKIncorrect.equals(true)}">
			<div class="error">
				<spring:message code="incorrect.reservationPK" />
			</div>
			<span class="vertical_separator"></span>
		</c:when>
		<c:otherwise>
			<nav id="context_menu"></nav>
			<div class="content">
				<c:choose>
					<c:when test="${empty reservations}">
						<div class="notification">
							<spring:message code="empty.reservations" />
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
									<td><spring:message code="db.reservationDate" /></td>
									<td><spring:message code="db.forWhom" /></td>
									<td><spring:message code="db.campusId" /></td>
									<td><spring:message code="db.buildingId" /></td>
									<td><spring:message code="db.roomId" /></td>
									<td>Status</td>
								</tr>

								<c:forEach items="${reservations}" var="item">
									<tr>
										<td><c:choose>
												<c:when test="${role.equals('client')}">
													<form action="<c:url value="/client/manage" />"
														class="small" method="get">
												</c:when>
												<c:when test="${role.equals('confirmer')}">
													<form action="<c:url value="/confirmer/manage" />"
														class="small" method="get">
												</c:when>
											</c:choose> <input type="hidden" name="reservation_id"
											value="<c:out value="${item.reservationId}"></c:out>" />
											<button type="submit" class="button">
												<spring:message code="word.advance" />
											</button>
											</form></td>

										<td><fmt:formatDate type="time" pattern="H:mm"
												value="${item.timeFrom}" />-<fmt:formatDate type="time"
												pattern="H:mm" value="${item.timeTo}" /> <br /> <fmt:formatDate
												type="date" pattern="yyyy-MM-dd"
												value="${item.reservationDate}" /></td>
										<td><c:out value="${item.personGroupId}"></c:out></td>
										<td><c:out value="${item.campusId}"></c:out></td>
										<td><c:out value="${item.buildingId}"></c:out></td>
										<td><c:out value="${item.roomId}"></c:out></td>
										<c:choose>
											<c:when test="${item.dbStatusId.equals('U')}">
												<td class="green"><spring:message code="word.booked" /></td>
											</c:when>
											<c:when test="${item.dbStatusId.equals('C')}">
												<td><spring:message code="word.waiting" /></td>
											</c:when>
											<c:when test="${item.dbStatusId.equals('D')}">
												<td class="red"><spring:message code="word.cancelled" /></td>
											</c:when>
										</c:choose>
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

