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
		<spring:message code="title.creatingUser" />
	</h1>
	<nav id="context_menu"></nav>
	<div class="content">
		<c:url value="/usr/new" var="url" />
		<form:form modelAttribute="usr" action="${url}" method="post">
			<div class="field">
				<label class="key">Login</label>
				<form:input path="id.usrId" cssClass="textbox" />
				<form:errors path="id.usrId" cssClass="warning" />
			</div>
			<div class="field">
				<label class="key"><spring:message code="word.person" /></label>
				<c:if test="${not empty usr.personId}">
					<div class="field_value">
						<c:out value="${person}"></c:out>
					</div>
					<button type="submit" class="button" name="delete_person">
						<spring:message code="word.delete" />
					</button>
				</c:if>
				<button type="submit" class="button" name="choose_person">
					<spring:message code="word.choose" />
				</button>
				<form:hidden path="personId" />
				<form:errors path="personId" cssClass="warning" />
			</div>
			<div class="field">
				<label class="key">E-mail</label>
				<form:input path="email" cssClass="textbox" />
				<form:errors path="email" cssClass="warning" />
			</div>
			<div class="notification">
				<spring:message code="sendingPasswordOnEmail" />
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


