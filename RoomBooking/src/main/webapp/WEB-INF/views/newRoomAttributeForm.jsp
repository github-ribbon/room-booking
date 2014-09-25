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
		<spring:message code="title.creatingRoomAttribute" />
	</h1>
	<nav id="context_menu"></nav>
	<div class="content">
		<c:url value="/room-attribute/new" var="url" />
		<form:form modelAttribute="roomAttribute" action="${url}"
			method="post">
			<div class="field">
				<label class="key"><spring:message code="db.campusId" /></label>
				<c:if test="${not empty roomAttribute.id.campusId}">
					<div class="field_value">
						<c:out value="${roomAttribute.id.campusId}"></c:out>
					</div>
				</c:if>
				<button type="submit" class="button" name="choose_campus">
					<spring:message code="word.choose" />
				</button>
				<form:hidden path="id.campusId" />
				<form:errors path="id.campusId" cssClass="warning" />
			</div>
			<div class="field">
				<label class="key"><spring:message code="db.buildingId" /></label>
				<c:if test="${not empty roomAttribute.id.buildingId}">
					<div class="field_value">
						<c:out value="${roomAttribute.id.buildingId}"></c:out>
					</div>
				</c:if>
				<c:if test="${not empty roomAttribute.id.campusId}">
					<button type="submit" name="choose_building">
						<spring:message code="word.choose" />
					</button>
				</c:if>
				<form:hidden path="id.buildingId" />
				<form:errors path="id.buildingId" cssClass="warning" />
			</div>
			<div class="field">
				<label class="key"><spring:message code="db.roomId" /></label>
				<c:if test="${not empty roomAttribute.id.roomId}">
					<div class="field_value">
						<c:out value="${roomAttribute.id.roomId}"></c:out>
					</div>
				</c:if>

				<c:if test="${not empty roomAttribute.id.buildingId}">
					<button type="submit" class="button" name="choose_room">
						<spring:message code="word.choose" />
					</button>
				</c:if>
				<form:hidden path="id.roomId" />
				<form:errors path="id.roomId" cssClass="warning" />
			</div>
			<div class="field">
				<label class="key"><spring:message code="db.roomAttributeId" /></label>
				<c:if test="${not empty roomAttribute.id.roomAttributeTypeId}">
					<div class="field_value">
						<c:out value="${roomAttribute.id.roomAttributeTypeId}"></c:out>
					</div>
				</c:if>
				<button type="submit" name="choose_rat">
					<spring:message code="word.choose" />
				</button>
				<form:hidden path="id.roomAttributeTypeId" />
				<form:errors path="id.roomAttributeTypeId" cssClass="warning" />
			</div>
			<div class="field">
				<label class="normal"><spring:message code="db.value" /></label>
				<form:input path="value" />
				<form:errors path="value" cssClass="warning" />
			</div>
			<div class="button_container">
				<button type="submit" name="create">
					<spring:message code="word.create" />
				</button>
			</div>
		</form:form>
	</div>
</div>

<jsp:include page="footer.jsp"></jsp:include>


