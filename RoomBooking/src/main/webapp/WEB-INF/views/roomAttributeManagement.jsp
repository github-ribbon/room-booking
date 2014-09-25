<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<jsp:include page="header.jsp">
	<jsp:param value="objectManagement" name="appName" />
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
					href="<c:url value="/campus/manage?campus_id=${roomAttribute.id.campusId}" />"><spring:message
						code="word.campus" /></a> &raquo; <a class="button"
					href="<c:url value="/building?campus_id=${roomAttribute.id.campusId}" />"><spring:message
						code="word.buildings" /></a> &raquo; <a class="button"
					href="<c:url value="/building/manage?campus_id=${roomAttribute.id.campusId}&building_id=${roomAttribute.id.buildingId}" />"><spring:message
						code="word.building" /></a> &raquo; <a class="button"
					href="<c:url value="/room?campus_id=${roomAttribute.id.campusId}&building_id=${roomAttribute.id.buildingId}" />"><spring:message
						code="word.rooms" /></a> &raquo; <a class="button"
					href="<c:url value="/room/manage?campus_id=${roomAttribute.id.campusId}&building_id=${roomAttribute.id.buildingId}&room_id=${roomAttribute.id.roomId}" />"><spring:message
						code="word.room" /></a> &raquo; <a class="button"
					href="<c:url value="/room-attribute?campus_id=${roomAttribute.id.campusId}&building_id=${roomAttribute.id.buildingId}&room_id=${roomAttribute.id.roomId}&room_attribute_type_id=${roomAttribute.id.roomAttributeTypeId}" />"><spring:message
						code="word.roomAttributes" /></a>


			</nav>
			<div class="content">
				<c:url value="/room-attribute/manage" var="url" />
				<form:form modelAttribute="roomAttribute" action="${url}"
					method="post">
					<form:hidden path="id.campusId" />
					<form:hidden path="id.buildingId" />
					<form:hidden path="id.roomId" />
					<form:hidden path="id.roomAttributeTypeId" />
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
						<form:input path="value" />
						<form:errors path="value" cssClass="warning" />
					</div>
					<div class="button_container">
						<button type="submit" name="update">
							<spring:message code="word.save" />
						</button>
						<button type="submit" name="delete">
							<spring:message code="word.delete" />
						</button>
					</div>
				</form:form>
			</div>
		</c:otherwise>
	</c:choose>
</div>

<jsp:include page="footer.jsp"></jsp:include>




