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
		<spring:message code="title.creatingRoomManager" />
	</h1>
	<nav id="context_menu"></nav>
	<div class="content">
		<c:url value="/room-manager/new" var="url" />
		<form:form modelAttribute="roomManager" action="${url}" method="post">
			<div class="field">
				<label class="key"><spring:message code="db.campusId" /></label>
				<c:if test="${not empty roomManager.id.campusId}">
					<div class="field_value">
						<c:out value="${roomManager.id.campusId}"></c:out>
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

				<c:if test="${not empty roomManager.id.buildingId}">
					<div class="field_value">
						<c:out value="${roomManager.id.buildingId}"></c:out>
					</div>
				</c:if>

				<c:if test="${not empty roomManager.id.campusId}">
					<button type="submit" name="choose_building">
						<spring:message code="word.choose" />
					</button>
				</c:if>
				<form:hidden path="id.buildingId" />
				<form:errors path="id.buildingId" cssClass="warning" />
			</div>
			<div class="field">
				<label class="key"><spring:message code="db.roomId" /></label>

				<c:if test="${not empty roomManager.id.roomId}">
					<div class="field_value">
						<c:out value="${roomManager.id.roomId}"></c:out>
					</div>
				</c:if>

				<c:if test="${not empty roomManager.id.buildingId}">
					<button type="submit" class="button" name="choose_room">
						<spring:message code="word.choose" />
					</button>
				</c:if>
				<form:hidden path="id.roomId" />
				<form:errors path="id.roomId" cssClass="warning" />
			</div>
			<div class="field">
				<label class="key"><spring:message code="word.userGroupName" /></label>
				<c:if test="${not empty roomManager.id.usrGroupId}">
					<div class="field_value">
						<c:out value="${roomManager.id.usrGroupId}"></c:out>
					</div>
				</c:if>
				<button type="submit" name="choose_usr_group">
					<spring:message code="word.choose" />
				</button>
				<form:hidden path="id.usrGroupId" />
				<form:errors path="id.usrGroupId" cssClass="warning" />
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


