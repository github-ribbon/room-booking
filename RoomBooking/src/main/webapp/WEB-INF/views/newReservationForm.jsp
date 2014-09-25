<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<jsp:include page="header.jsp">
	<jsp:param value="roomBooking" name="appName" />
</jsp:include>


<div id="main_container">
	<h1 id="page_title">
		<spring:message code="title.creatingReservation" />
	</h1>
	<nav id="context_menu"></nav>
	<div class="content">
		<c:url value="/client/new" var="url" />
		<form:form modelAttribute="reservation" action="${url}" method="post">
			<div class="field">
				<label class="normal"><spring:message code="db.campusId" /></label>
				<c:if test="${not empty reservation.campusId}">
					<div class="field_value">
						<c:out value="${reservation.campusId}"></c:out>
					</div>
				</c:if>
				<button type="submit" class="button" name="choose_campus">
					<spring:message code="word.choose" />
				</button>
				<form:hidden path="campusId" />
				<form:errors path="campusId" cssClass="warning" />
			</div>


			<div class="field">
				<label class="normal"><spring:message code="db.buildingId" /></label>

				<c:if test="${not empty reservation.buildingId}">
					<div class="field_value">
						<c:out value="${reservation.buildingId}"></c:out>
					</div>
				</c:if>

				<c:if test="${not empty reservation.campusId}">
					<button type="submit" class="button" name="choose_building">
						<spring:message code="word.choose" />
					</button>
				</c:if>
				<form:hidden path="buildingId" />
				<form:errors path="buildingId" cssClass="warning" />
			</div>


			<div class="field">
				<label class="normal"><spring:message code="db.roomId" /></label>
				<c:if test="${not empty reservation.roomId}">
					<div class="field_value">
						<c:out value="${reservation.roomId}"></c:out>
					</div>
				</c:if>
				<c:if test="${not empty reservation.buildingId}">
					<button type="submit" class="button" name="choose_room">
						<spring:message code="word.choose" />
					</button>
				</c:if>

				<form:hidden path="roomId" />
				<form:errors path="roomId" cssClass="warning" />
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
				<label class="picker normal"><spring:message
						code="db.reservationDate" /> <spring:message
						code="word.dateSuffix" /></label>
				<form:input path="reservationDate"
					cssClass="small_textbox date_picker" />
				<form:errors path="reservationDate" cssClass="warning" />
			</div>

			<div class="field">
				<label class="picker normal"><spring:message
						code="db.timeFrom" /> <spring:message code="word.timeSuffix" /></label>
				<form:input path="timeFrom" cssClass="small_textbox time_picker" />
				<form:errors path="timeFrom" cssClass="warning" />
			</div>

			<div class="field">
				<label class="picker normal"><spring:message
						code="db.timeTo" /> <spring:message code="word.timeSuffix" /></label>
				<form:input path="timeTo" cssClass="small_textbox time_picker" />
				<form:errors path="timeTo" cssClass="warning" />
			</div>

			<div class="field">
				<label class="normal"><spring:message code="db.forWhom" /></label>
				<c:if test="${not empty reservation.personGroupId}">
					<div class="field_value">
						<c:out value="${reservation.personGroupId}"></c:out>
					</div>
				</c:if>

				<button type="submit" class="button" name="n_choose_person_group">
					<spring:message code="word.choose" />
				</button>

				<form:hidden path="personGroupId" cssClass="small_textbox" />
				<form:errors path="personGroupId" cssClass="warning" />
			</div>

			<div class="field">
				<label class="normal"><spring:message code="db.purpose" /></label>
				<form:textarea path="purpose" />
			</div>

			<div class="button_container">
				<button type="submit" class="button" name="create">
					<spring:message code="word.book" />
				</button>
			</div>
		</form:form>
	</div>
</div>

<jsp:include page="footer.jsp"></jsp:include>


