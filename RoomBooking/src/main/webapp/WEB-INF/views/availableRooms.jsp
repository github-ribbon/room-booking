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
		<spring:message code="title.availableRooms" />
	</h1>
	<c:choose>
		<c:when test="${isDateFormatIncorrect.equals(true)}">
			<div class="error">
				<spring:message code="incorrect.dateFormat" />
			</div>
			<span class="vertical_separator"></span>
		</c:when>
		<c:when test="${isDatePast.equals(true)}">
			<div class="error">
				<spring:message code="pastDate" />
			</div>
			<span class="vertical_separator"></span>
		</c:when>
		<c:otherwise>
			<div class="date_header">
				<fmt:formatDate type="date" pattern="yyyy-MM-dd"
					value="${reservationDate}" />
			</div>
			<div class="content">
				<c:choose>
					<c:when test="${empty bookableRooms}">
						<div class="notification">
							<spring:message code="empty.availableRooms" />
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
									<td><spring:message code="db.campusId" /></td>
									<td><spring:message code="db.buildingId" /></td>
									<td><spring:message code="db.roomId" /></td>
								</tr>
								<c:forEach items="${bookableRooms}" var="item">
									<tr>
										<td>
											<form action="<c:url value="/client/new" />" class="small"
												method="get">
												<input type="hidden" name="campus_id"
													value="<c:out value="${item.id.campusId}"></c:out>" /> <input
													type="hidden" name="building_id"
													value="<c:out value="${item.id.buildingId}"></c:out>" /> <input
													type="hidden" name="room_id"
													value="<c:out value="${item.id.roomId}"></c:out>" /> <input
													type="hidden" name="reservation_date"
													value="<fmt:formatDate type="date" pattern="yyyy-MM-dd" value="${reservationDate}" />" />
												<button type="submit" class="button">
													<spring:message code="word.book" />
												</button>
											</form>
										</td>
										<td><c:out value="${item.id.campusId}"></c:out></td>
										<td><c:out value="${item.id.buildingId}"></c:out></td>
										<td><c:out value="${item.id.roomId}"></c:out></td>
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

