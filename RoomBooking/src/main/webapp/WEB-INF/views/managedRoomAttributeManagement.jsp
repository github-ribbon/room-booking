<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<jsp:include page="header.jsp">
	<jsp:param value="bookingConfirmation" name="appName" />
</jsp:include>


<div id="main_container">
	<h1 id="page_title">
		<spring:message code="title.roomAttributeDetails" />
	</h1>
	<c:choose>
		<c:when test="${isRoomAttributePKIncorrect.equals(true)}">
			<div class="error">
				<spring:message code="incorrect.raPK" />
			</div>
		</c:when>
		<c:otherwise>
			<nav id="context_menu">
				<a class="button"
					href="<c:url value="/confirmer/campus/manage?campus_id=${roomAttribute.id.campusId}" />"><spring:message
						code="word.campus" /></a> &raquo; <a class="button"
					href="<c:url value="/confirmer/building?campus_id=${roomAttribute.id.campusId}" />"><spring:message
						code="word.buildings" /></a> &raquo; <a class="button"
					href="<c:url value="/confirmer/building/manage?campus_id=${roomAttribute.id.campusId}&building_id=${roomAttribute.id.buildingId}" />"><spring:message
						code="word.building" /></a> &raquo; <a class="button"
					href="<c:url value="/confirmer/room?campus_id=${roomAttribute.id.campusId}&building_id=${roomAttribute.id.buildingId}" />"><spring:message
						code="word.rooms" /></a> &raquo; <a class="button"
					href="<c:url value="/confirmer/room/manage?campus_id=${roomAttribute.id.campusId}&building_id=${roomAttribute.id.buildingId}&room_id=${roomAttribute.id.roomId}" />"><spring:message
						code="word.room" /></a> &raquo; <a class="button"
					href="<c:url value="/confirmer/room-attribute?campus_id=${roomAttribute.id.campusId}&building_id=${roomAttribute.id.buildingId}&room_id=${roomAttribute.id.roomId}&room_attribute_type_id=${roomAttribute.id.roomAttributeTypeId}" />"><spring:message
						code="word.roomAttributes" /></a>

			</nav>
			<div class="content">
				<c:url value="/confirmer/room-attribute/manage" var="url" />
				<form:form modelAttribute="roomAttribute" action="${url}"
					method="post">
					<div class="field">
						<label class="key"><spring:message code="db.campusId" /></label>
						<div class="field_value">
							<c:out value="${roomAttribute.id.campusId}"></c:out>
						</div>
					</div>
					<div class="field">
						<label class="key"><spring:message code="db.buildingId" /></label>
						<div class="field_value">
							<c:out value="${roomAttribute.id.buildingId}"></c:out>
						</div>
					</div>
					<div class="field">
						<label class="key"><spring:message code="db.roomId" /></label>
						<div class="field_value">
							<c:out value="${roomAttribute.id.roomId}"></c:out>
						</div>
					</div>
					<div class="field">
						<label class="key"><spring:message
								code="db.roomAttributeId" /></label>
						<div class="field_value">
							<c:out value="${roomAttribute.id.roomAttributeTypeId}"></c:out>
						</div>
					</div>
					<div class="field">
						<label class="normal"><spring:message code="db.value" /></label>
						<div class="field_value">
							<c:out value="${roomAttribute.value}"></c:out>
						</div>
					</div>
					<div class="button_container"></div>
				</form:form>
			</div>
		</c:otherwise>
	</c:choose>
</div>

<jsp:include page="footer.jsp"></jsp:include>




