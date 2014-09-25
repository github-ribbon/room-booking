<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<jsp:include page="header.jsp">
	<jsp:param value="groupManagement" name="appName" />
</jsp:include>


<div id="main_container">
	<h1 id="page_title">
		<spring:message code="title.creatingPerson" />
	</h1>
	<nav id="context_menu"></nav>
	<div class="content">
		<c:url value="/person/new" var="url" />
		<form:form modelAttribute="person" action="${url}" method="post">
			<div class="field">
				<label class="normal"><spring:message code="db.givenName" /></label>
				<form:input path="givenName" cssClass="textbox" />
				<form:errors path="givenName" cssClass="warning" />
			</div>
			<div class="field">
				<label class="normal"><spring:message code="db.familyName" /></label>
				<form:input path="familyName" cssClass="textbox" />
				<form:errors path="familyName" cssClass="warning" />
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



