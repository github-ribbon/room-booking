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
		<spring:message code="title.creatingCampus" />
	</h1>
	<nav id="context_menu"></nav>
	<div class="content">
		<c:url value="/campus/new" var="url" />
		<form:form modelAttribute="campus" action="${url}" method="post">
			<div class="field">
				<label class="key"><spring:message code="db.campusId" /></label>
				<form:input path="id.campusId" />
				<form:errors path="id.campusId" cssClass="warning" />
			</div>
			<div class="field">
				<label class="normal"><spring:message code="db.name" /></label>
				<form:input path="name" />
				<form:errors path="name" cssClass="warning" />
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


