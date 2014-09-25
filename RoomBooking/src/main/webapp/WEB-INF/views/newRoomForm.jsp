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
		<spring:message code="title.creatingRoom" />
	</h1>
	<nav id="context_menu"></nav>
	<div class="content">
		<c:url value="/room/new" var="url" />
		<form:form modelAttribute="room" action="${url}" method="post">
			<div class="field">
				<label class="key"><spring:message code="db.campusId" /></label>
				<c:if test="${not empty room.id.campusId}">
					<div class="field_value">
						<c:out value="${room.id.campusId}"></c:out>
					</div>
				</c:if>
				<button type="submit" name="choose_campus">
					<spring:message code="word.choose" />
				</button>
				<form:hidden path="id.campusId" />
				<form:errors path="id.campusId" cssClass="warning" />
			</div>
			<div class="field">
				<label class="key"><spring:message code="db.buildingId" /></label>

				<c:if test="${not empty room.id.buildingId}">
					<div class="field_value">
						<c:out value="${room.id.buildingId}"></c:out>
					</div>
				</c:if>
				<c:if test="${not empty room.id.campusId}">
					<button type="submit" name="choose_building">
						<spring:message code="word.choose" />
					</button>
				</c:if>
				<form:hidden path="id.buildingId" />
				<form:errors path="id.buildingId" cssClass="warning" />
			</div>
			<div class="field">
				<label class="key"><spring:message code="db.roomId" /></label>
				<form:input path="id.roomId" cssClass="textbox" />
				<form:errors path="id.roomId" cssClass="warning" />
			</div>
			<div class="field">
				<label class="normal"><spring:message code="db.description" /></label>
				<form:textarea path="description" />
				<form:errors path="description" cssClass="warning" />
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


