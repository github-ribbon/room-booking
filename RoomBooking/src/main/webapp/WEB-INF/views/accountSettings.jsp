<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<jsp:include page="header.jsp">
	<jsp:param value="userPanel" name="appName" />
</jsp:include>



<div id="main_container">
	<h1 id="page_title">
		<spring:message code="title.accountSettings" />
	</h1>
	<div class="content">
		<c:url value="/panel/update-person" var="url" />
		<form:form modelAttribute="person" action="${url}" method="post">
			<form:hidden path="personId" />
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
				<button type="submit">
					<spring:message code="word.savePersonalDetails" />
				</button>
			</div>
		</form:form>
	</div>

	<div class="content">
		<c:url value="/panel/update-user" var="url" />
		<form:form modelAttribute="usr" action="${url}" method="post">
			<form:hidden path="id.usrId" />
			<form:hidden path="email" />
			<div class="field">
				<label class="key">Login</label>
				<div class="field_value">
					<c:out value="${usr.id.usrId}"></c:out>
				</div>
			</div>
			<div class="field">
				<label class="normal"><spring:message code="db.password" /></label>
				<form:password path="password" cssClass="textbox" />
				<form:errors path="password" cssClass="warning" />
			</div>
			<div class="field">
				<label class="key">E-mail</label>
				<div class="field_value">
					<c:out value="${usr.email}"></c:out>
				</div>
			</div>
			<div class="button_container">
				<button type="submit">
					<spring:message code="word.saveUserDetails" />
				</button>
			</div>
		</form:form>
	</div>
</div>

<jsp:include page="footer.jsp"></jsp:include>



