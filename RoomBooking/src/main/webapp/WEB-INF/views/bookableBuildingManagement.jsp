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
		<spring:message code="title.buildingDetails" />
	</h1>
	<c:choose>
		<c:when test="${isBuildingPKIncorrect.equals(true)}">
			<div class="error">
				<spring:message code="incorrect.buildingPK" />
			</div>
		</c:when>
		<c:otherwise>
			<nav id="context_menu">
				<a class="button"
					href="<c:url value="/client/campus/manage?campus_id=${building.id.campusId}" />"><spring:message
						code="word.campus" /></a> &raquo; <a class="button"
					href="<c:url value="/client/building?campus_id=${building.id.campusId}" />"><spring:message
						code="word.buildings" /></a> &raquo; <a class="button"
					href="<c:url value="/client/room?campus_id=${building.id.campusId}&building_id=${building.id.buildingId}" />"><spring:message
						code="word.rooms" /></a>


			</nav>
			<div class="content">
				<c:url value="/client/building/manage" var="url" />
				<form:form modelAttribute="building" action="${url}" method="post">
					<form:hidden path="id.campusId" />
					<form:hidden path="id.buildingId" />
					<div class="field">
						<label class="key"><spring:message code="db.campusId" /></label>
						<div class="field_value">
							<c:out value="${building.id.campusId}"></c:out>
						</div>
					</div>
					<div class="field">
						<label class="key"><spring:message code="db.buildingId" /></label>
						<div class="field_value">
							<c:out value="${building.id.buildingId}"></c:out>
						</div>
					</div>
					<div class="field">
						<label class="normal"><spring:message code="db.name" /></label>
						<div class="field_value">
							<c:out value="${building.name}"></c:out>
						</div>
					</div>
					<div class="field">
						<label class="normal"><spring:message
								code="db.description" /></label>
						<div class="field_value">
							<c:out value="${building.description}"></c:out>
						</div>
					</div>
					<div class="button_container"></div>
				</form:form>
			</div>
		</c:otherwise>
	</c:choose>
</div>

<jsp:include page="footer.jsp"></jsp:include>


