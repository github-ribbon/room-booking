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
		<spring:message code="title.roomStatuses" />
	</h1>
	<nav id="context_menu"></nav>
	<div class="content">
		<c:url value="/client/room-statuses" var="url" />
		<form method="get" action="<c:out value="${url}"></c:out>">
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
					type="text" name="date" class="small_textbox date_picker" value="" />
			</div>
			<div class="button_container">
				<button type="submit" class="button" name="status" value="available">
					<spring:message code="word.availableRooms" />
				</button>
				<button type="submit" class="button" name="status" value="busy">
					<spring:message code="word.busyRooms" />
				</button>
			</div>
		</form>
	</div>
</div>

<jsp:include page="footer.jsp"></jsp:include>


