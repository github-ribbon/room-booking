<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>

<jsp:include page="header.jsp">
	<jsp:param value="objectManagement" name="appName" />
</jsp:include>


<div id="main_container">
	<h1 id="page_title">
		<spring:message code="title.roomManagerDetails" />
	</h1>
	<c:choose>
		<c:when test="${isRoomManagerPKIncorrect.equals(true)}">
			<div class="error">
				<spring:message code="incorrect.roomManagerPK" />
			</div>
		</c:when>
		<c:otherwise>
			<nav id="context_menu">
				<a class="button"
					href="<c:url value="/campus/manage?campus_id=${roomManager.id.campusId}" />"><spring:message
						code="word.campus" /></a> &raquo; <a class="button"
					href="<c:url value="/building?campus_id=${roomManager.id.campusId}" />"><spring:message
						code="word.buildings" /></a> &raquo; <a class="button"
					href="<c:url value="/building/manage?campus_id=${roomManager.id.campusId}&building_id=${roomManager.id.buildingId}" />"><spring:message
						code="word.building" /></a> &raquo; <a class="button"
					href="<c:url value="/room?campus_id=${roomManager.id.campusId}&building_id=${roomManager.id.buildingId}" />"><spring:message
						code="word.rooms" /></a> &raquo; <a class="button"
					href="<c:url value="/room/manage?campus_id=${roomManager.id.campusId}&building_id=${roomManager.id.buildingId}&room_id=${roomManager.id.roomId}" />"><spring:message
						code="word.room" /></a> &raquo; <a class="button"
					href="<c:url value="/room-attribute?campus_id=${roomManager.id.campusId}&building_id=${roomManager.id.buildingId}&room_id=${roomManager.id.roomId}" />"><spring:message
						code="word.roomAttributes" /></a>

			</nav>
			<div class="content">
				<c:url value="/room-manager/manage" var="url" />
				<form:form modelAttribute="roomManager" action="${url}"
					method="post">
					<form:hidden path="id.campusId" />
					<form:hidden path="id.buildingId" />
					<form:hidden path="id.roomId" />
					<form:hidden path="id.usrGroupId" />
					<div class="field">
						<label class="key"><spring:message code="db.campusId" /></label>
						<div class="field_value">
							<c:out value="${roomManager.id.campusId}"></c:out>
						</div>
					</div>
					<div class="field">
						<label class="key"><spring:message code="db.buildingId" /></label>
						<div class="field_value">
							<c:out value="${roomManager.id.buildingId}"></c:out>
						</div>
					</div>
					<div class="field">
						<label class="key"><spring:message code="db.roomId" /></label>
						<div class="field_value">
							<c:out value="${roomManager.id.roomId}"></c:out>
						</div>
					</div>
					<div class="field">
						<label class="key"><spring:message
								code="word.userGroupName" /></label>
						<div class="field_value">
							<c:out value="${roomManager.usrGroup.id.usrGroupId}"></c:out>
						</div>
						<sec:authorize access="hasRole('ROLE_GROUP')">
							<a
								href="<c:url value="/usr-group/manage?usr_group_id=${roomManager.id.usrGroupId}" />"><spring:message
									code="word.advance" /></a>
						</sec:authorize>

					</div>
					<div class="button_container">
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


