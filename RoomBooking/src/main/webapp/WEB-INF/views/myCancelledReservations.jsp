<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<jsp:include page="header.jsp">
	<jsp:param value="roomBooking" name="appName" />
</jsp:include>


<div id="main_container">
	<h1 id="page_title">
		<spring:message code="word.cancelledReservations" />
	</h1>
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
							<td><spring:message code="db.when" /></td>
							<td><spring:message code="db.forWhom" /></td>
							<td><spring:message code="db.campusId" /></td>
							<td><spring:message code="db.buildingId" /></td>
							<td><spring:message code="db.roomId" /></td>
						</tr>
						<c:forEach items="${reservations}" var="item">
							<tr>
								<td>
									<form action="<c:url value="/client/manage" />" class="small"
										method="get">
										<input type="hidden" name="reservation_id"
											value="<c:out value="${item.reservationId}"></c:out>" />
										<button type="submit" class="button">
											<spring:message code="word.advance" />
										</button>
									</form>
								</td>
								<td><fmt:formatDate type="date" pattern="yyyy-MM-dd"
										value="${item.reservationDate}" /></td>
								<td><fmt:formatDate type="time" pattern="H:mm"
										value="${item.timeFrom}" />-<fmt:formatDate type="time"
										pattern="H:mm" value="${item.timeTo}" /></td>
								<td><c:out value="${item.personGroupId}"></c:out></td>
								<td><c:out value="${item.campusId}"></c:out></td>
								<td><c:out value="${item.buildingId}"></c:out></td>
								<td><c:out value="${item.roomId}"></c:out></td>
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

