<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>


<jsp:include page="header.jsp">
	<jsp:param value="bookingConfirmation" name="appName" />
</jsp:include>


<div id="main_container">
	<h1 id="page_title">
		<spring:message code="word.roomAttributes" />
	</h1>
	<c:choose>
		<c:when test="${isRoomPKIncorrect.equals(true)}">
			<div class="error">
				<spring:message code="incorrect.roomPK" />
			</div>
		</c:when>
		<c:otherwise>
			<nav id="context_menu">
				<a class="button"
					href="<c:url value="/confirmer/campus/manage?campus_id=${campusId}" />"><spring:message
						code="word.campus" /></a> &raquo; <a class="button"
					href="<c:url value="/confirmer/building?campus_id=${campusId}" />"><spring:message
						code="word.buildings" /></a> &raquo; <a class="button"
					href="<c:url value="/confirmer/building/manage?campus_id=${campusId}&building_id=${buildingId}" />"><spring:message
						code="word.building" /></a> &raquo; <a class="button"
					href="<c:url value="/confirmer/room?campus_id=${campusId}&building_id=${buildingId}" />"><spring:message
						code="word.rooms" /></a> &raquo; <a class="button"
					href="<c:url value="/confirmer/room/manage?campus_id=${campusId}&building_id=${buildingId}&room_id=${roomId}" />"><spring:message
						code="word.room" /></a>

			</nav>
			<div class="content">
				<c:choose>
					<c:when test="${empty roomAttributes}">
						<div class="notification">
							<spring:message code="empty.ras" />
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
									<td class="key"><spring:message code="db.roomId" /></td>
									<td class="key"><spring:message code="db.roomAttributeId" /></td>
									<td><spring:message code="db.value" /></td>
								</tr>

								<c:forEach items="${roomAttributes}" var="item">
									<tr>
										<td>
											<form
												action="<c:url value="/confirmer/room-attribute/manage" />"
												method="get">
												<input type="hidden" name="campus_id"
													value="<c:out value="${item.id.campusId}"></c:out>" /> <input
													type="hidden" name="building_id"
													value="<c:out value="${item.id.buildingId}"></c:out>" /> <input
													type="hidden" name="room_id"
													value="<c:out value="${item.id.roomId}"></c:out>" /> <input
													type="hidden" name="room_attribute_type_id"
													value="<c:out value="${item.id.roomAttributeTypeId}"></c:out>" />
												<button type="submit">
													<spring:message code="word.advance" />
												</button>
											</form>
										</td>
										<td class="dark_value"><c:out value="${item.id.campusId}"></c:out></td>
										<td class="dark_value"><c:out
												value="${item.id.buildingId}"></c:out></td>
										<td class="dark_value"><c:out value="${item.id.roomId}"></c:out></td>
										<td class="dark_value"><c:out
												value="${item.id.roomAttributeTypeId}"></c:out></td>
										<td><c:out value="${item.value}"></c:out></td>
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

