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
		<spring:message code="title.campusDetails" />
	</h1>
	<c:choose>
		<c:when test="${isCampusPKIncorrect.equals(true)}">
			<div class="error">
				<spring:message code="incorrect.campusPK" />
			</div>
		</c:when>
		<c:otherwise>
			<nav id="context_menu">
				<a class="button"
					href="<c:url value="/confirmer/building?campus_id=${campus.id.campusId}" />"><spring:message
						code="word.buildings" /></a>

			</nav>
			<div class="content">
				<c:url value="/campus/manage" var="url" />
				<form:form modelAttribute="campus" action="${url}" method="post">
					<form:hidden path="id.campusId" />
					<div class="field">
						<label class="key"><spring:message code="db.campusId" /></label>
						<div class="field_value">
							<c:out value="${campus.id.campusId}"></c:out>
						</div>
					</div>
					<div class="field">
						<label class="normal"><spring:message code="db.name" /></label>
						<div class="field_value">
							<c:out value="${campus.name}"></c:out>
						</div>
					</div>
					<div class="field">
						<label class="normal"><spring:message
								code="db.description" /></label>
						<div class="field_value">
							<c:out value="${campus.description}"></c:out>
						</div>
					</div>
					<div class="button_container"></div>
				</form:form>
			</div>
		</c:otherwise>
	</c:choose>
</div>

<jsp:include page="footer.jsp"></jsp:include>


