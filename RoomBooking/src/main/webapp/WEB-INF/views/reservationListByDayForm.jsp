<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
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


<c:choose>
	<c:when test="${role.equals('client')}">
		<c:url value="/client/reservation-list-by-day-processing" var="url" />
	</c:when>

	<c:when test="${role.equals('confirmer')}">
		<c:url value="/confirmer/reservation-list-by-day-processing" var="url" />
	</c:when>
</c:choose>

<div id="main_container">
	<h1 id="page_title">
		<spring:message code="title.resListByDay" />
	</h1>
	<nav id="context_menu"></nav>

	<div class="content">
		<form method="post" action="<c:out value="${url}"></c:out>">

			<input type="hidden" name="${_csrf.parameterName}"
				value="${_csrf.token}" />
			<div class="field">
				<label class="normal"><spring:message code="db.campusId" /></label>
				<c:if test="${not empty campusId}">
					<div class="field_value">
						<c:out value="${campusId}"></c:out>
					</div>
				</c:if>
				<button type="submit" class="button" name="choose_campus">
					<spring:message code="word.choose" />
				</button>

				<input type="hidden" name="campus_id"
					value="<c:out value="${campusId}"></c:out>" />
			</div>

			<div class="field">
				<label class="normal"><spring:message code="db.buildingId" /></label>
				<c:if test="${not empty buildingId}">
					<div class="field_value">
						<c:out value="${buildingId}"></c:out>
					</div>
				</c:if>
				<c:if test="${not empty campusId}">
					<button type="submit" class="button" name="choose_building">
						<spring:message code="word.choose" />
					</button>
				</c:if>

				<input type="hidden" name="building_id"
					value="<c:out value="${buildingId}"></c:out>" />
			</div>

			<div class="field">
				<label class="normal"><spring:message code="db.roomId" /></label>
				<c:if test="${not empty roomId}">
					<div class="field_value">
						<c:out value="${roomId}"></c:out>
					</div>
				</c:if>
				<c:if test="${not empty buildingId}">
					<button type="submit" class="button" name="choose_room">
						<spring:message code="word.choose" />
					</button>
				</c:if>
				<input type="hidden" name="room_id"
					value="<c:out value="${roomId}"></c:out>" />
			</div>

			<script
				src="<c:url value="/resources/scripts/js/jquery-ui-1.8.23.custom.min.js" />"></script>
			<script
				src="<c:url value="/resources/scripts/jquery-ui-timepicker-addon.js" />"></script>
			<script>
				jQuery(function($) { //on document.ready
					$('input.time_picker').timepicker({});
					$('input.date_picker').datepicker({
						dateFormat : 'yy-mm-dd'
					});

				});
			</script>

			<div class="field">
				<label class="normal"><spring:message code="word.date" /></label> <input
					type="text" name="reservation_date"
					class="small_textbox date_picker"
					value="<fmt:formatDate type="date" pattern="yyyy-MM-dd" value="${reservationDate}" />" />
			</div>

			<div class="button_container">
				<button type="submit" class="button" name="advance">
					<spring:message code="word.advance" />
				</button>
			</div>

		</form>
	</div>
</div>

<jsp:include page="footer.jsp"></jsp:include>
