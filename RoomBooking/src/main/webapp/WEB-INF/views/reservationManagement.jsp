<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<jsp:include page="header.jsp">
	<jsp:param value="roomBooking" name="appName" />
</jsp:include>




<div id="main_container">
	<h1 id="page_title">
		<spring:message code="title.reservationDetails" />
	</h1>
	<c:choose>
		<c:when test="${isReservationPKIncorrect.equals(true)}">
			<div class="error">
				<spring:message code="incorrect.reservationPK" />
			</div>
			<span class="vertical_separator"></span>
		</c:when>
		<c:otherwise>


			<nav id="context_menu">

				<c:if test="${not empty reservation.reservationParentId}">
					<a class="button"
						href="<c:url value="/client/manage?reservation_id=${reservation.reservationParentId}" />"><spring:message
							code="word.reservationParent" /></a>
				</c:if>

				<c:if
					test="${(not empty reservation.reservationParentId)&&(not empty reservation.reservationChildren)}">
					&raquo; 
				</c:if>

				<c:if test="${not empty reservation.reservationChildren}">
					<a class="button"
						href="<c:url value="/client/reservation-children?reservation_id=${reservation.reservationId}" />"><spring:message
							code="word.reservationChildren" /></a>
				</c:if>

			</nav>

			<div class="content">

				<div style="margin: 0 auto; text-align: center">


					<c:choose>
						<c:when test="${reservation.dbStatusId.equals('U')}">
							<div class="field_value green">
								<spring:message code="word.booked" />
							</div>
						</c:when>

						<c:when test="${reservation.dbStatusId.equals('C')}">
							<div class="waiting field_value">
								<spring:message code="word.waiting" />
							</div>
						</c:when>

						<c:when test="${reservation.dbStatusId.equals('D')}">
							<div class="field_value red">
								<spring:message code="word.cancelled" />
							</div>
						</c:when>
					</c:choose>



					<c:if test="${isDatePast.equals(true)}">
						<div class="field_value red">
							<spring:message code="word.pastReservation" />
						</div>
					</c:if>


				</div>




				<c:url value="/client/manage" var="url" />
				<form:form modelAttribute="reservation" action="${url}"
					method="post">


					<form:hidden path="reservationId" />


					<div class="field">
						<label class="normal"><spring:message code="db.campusId" /></label>
						<div class="field_value">
							<c:out value="${reservation.campusId}"></c:out>
						</div>
						<form:hidden path="campusId" />
						<form:errors path="campusId" cssClass="warning" />
					</div>



					<div class="field">
						<label class="normal"><spring:message code="db.buildingId" /></label>
						<div class="field_value">
							<c:out value="${reservation.buildingId}"></c:out>
						</div>
						<form:hidden path="buildingId" />
						<form:errors path="buildingId" cssClass="warning" />
					</div>



					<div class="field">
						<label class="normal"><spring:message code="db.roomId" /></label>
						<div class="field_value">
							<c:out value="${reservation.roomId}"></c:out>
						</div>
						<form:hidden path="roomId" />
						<form:errors path="roomId" cssClass="warning" />
					</div>



					<script
						src="<c:url value="/resources/scripts/js/jquery-ui-1.8.23.custom.min.js" />"></script>
					<script
						src="<c:url value="/resources/scripts/jquery-ui-timepicker-addon.js" />"></script>
					<script>

    jQuery(function($){ //on document.ready
        $('input.time_picker').timepicker({});
        $('input.date_picker').datepicker({dateFormat: 'yy-mm-dd'});
    
    });

</script>


					<div class="field">
						<label class="picker normal"><spring:message
								code="db.reservationDate" /> <spring:message
								code="word.dateSuffix" /></label>

						<c:choose>
							<c:when test="${status.equals('D') || isDatePast.equals(true)}">
								<div class="field_value">
									<fmt:formatDate type="date" pattern="yyyy-MM-dd"
										value="${reservation.reservationDate}" />
								</div>
							</c:when>
							<c:otherwise>
								<form:input path="reservationDate"
									cssClass="small_textbox date_picker" />
								<form:errors path="reservationDate" cssClass="warning" />
							</c:otherwise>
						</c:choose>
					</div>


					<div class="field">
						<label class="picker normal"><spring:message
								code="db.timeFrom" /> <spring:message code="word.timeSuffix" /></label>
						<c:choose>
							<c:when test="${status.equals('D') || isDatePast.equals(true)}">
								<div class="field_value">
									<fmt:formatDate type="time" pattern="H:mm"
										value="${reservation.timeFrom}" />
								</div>
							</c:when>
							<c:otherwise>
								<form:input path="timeFrom" cssClass="small_textbox time_picker" />
								<form:errors path="timeFrom" cssClass="warning" />
							</c:otherwise>
						</c:choose>
					</div>


					<div class="field">
						<label class="picker normal"><spring:message
								code="db.timeTo" /> <spring:message code="word.timeSuffix" /></label>
						<c:choose>
							<c:when test="${status.equals('D') || isDatePast.equals(true)}">
								<div class="field_value">
									<fmt:formatDate type="time" pattern="H:mm"
										value="${reservation.timeTo}" />
								</div>
							</c:when>
							<c:otherwise>
								<form:input path="timeTo" cssClass="small_textbox time_picker" />
								<form:errors path="timeTo" cssClass="warning" />
							</c:otherwise>
						</c:choose>
					</div>




					<div class="field">
						<label class="normal"><spring:message code="db.forWhom" /></label>
						<c:choose>
							<c:when
								test="${status.equals('D')||status.equals('U')||isDatePast.equals(true)}">
								<div class="field_value">
									<c:out value="${reservation.personGroupId}"></c:out>
								</div>
							</c:when>
							<c:otherwise>
								<c:if test="${not empty reservation.personGroupId}">
									<div class="field_value">
										<c:out value="${reservation.personGroupId}"></c:out>
									</div>
								</c:if>
								<button type="submit" class="button"
									name="m_choose_person_group">
									<spring:message code="word.choose" />
								</button>
							</c:otherwise>
						</c:choose>
						<form:hidden path="personGroupId" />
						<form:errors path="personGroupId" cssClass="warning" />
					</div>


					<div class="field">
						<label class="normal"><spring:message code="db.created" /></label>
						<div class="field_value">
							<fmt:formatDate type="both" pattern="yyyy-MM-dd H:mm:ss "
								value="${reservation.created}" />
						</div>
					</div>


					<div class="field">
						<label class="normal"><spring:message code="db.modified" /></label>
						<div class="field_value">
							<fmt:formatDate type="both" pattern="yyyy-MM-dd H:mm:ss "
								value="${reservation.modified}" />
						</div>
					</div>


					<div class="field">
						<label class="normal"><spring:message code="db.purpose" /></label>
						<c:choose>
							<c:when
								test="${status.equals('D')||status.equals('U')||isDatePast.equals(true)}">
								<div class="field_value">
									<c:out value="${reservation.purpose}"></c:out>
								</div>
							</c:when>
							<c:otherwise>
								<form:textarea path="purpose" />
							</c:otherwise>
						</c:choose>
					</div>

					<form:hidden path="created" />
					<form:hidden path="modified" />
					<form:hidden path="dbStatusId" />
					<form:hidden path="reservationParentId" />

					<div class="button_container">
						<c:if
							test="${status.equals('D')==false && isDatePast.equals(false)}">
							<button type="submit" class="button" name="update">
								<spring:message code="word.change" />
							</button>
							<button type="submit" class="button" name="delete">
								<spring:message code="word.cancel" />
							</button>
						</c:if>
					</div>

				</form:form>
			</div>
		</c:otherwise>
	</c:choose>
</div>


<jsp:include page="footer.jsp"></jsp:include>


