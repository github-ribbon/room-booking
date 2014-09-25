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
		<spring:message code="title.personDetails" />
	</h1>
	<c:choose>
		<c:when test="${isPersonPKIncorrect.equals(true)}">
			<div class="error">
				<spring:message code="incorrect.personPK" />
			</div>
		</c:when>
		<c:otherwise>
			<nav id="context_menu">
				<c:choose>
					<c:when test="${not empty usrId}">
						<a class="button"
							href="<c:url value="/usr/manage?usr_id=${usrId}" />"><spring:message
								code="word.user" /></a>

					</c:when>

					<c:otherwise>
						<a class="button"
							href="<c:url value="/usr/new?person_id=${person.personId}" />"><spring:message
								code="word.newUser" /></a>

					</c:otherwise>
				</c:choose>
				&raquo; <a class="button"
					href="<c:url value="/person-group-person/by-person?person_id=${person.personId}" />"><spring:message
						code="word.assignedPersonGroups" /></a>

			</nav>
			<div class="content">
				<c:url value="/person/manage" var="url" />
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
						<button type="submit" name="update">
							<spring:message code="word.save" />
						</button>
						<c:if test="${empty person.usr&&empty person.personGroupPersons}">
							<button type="submit" name="delete">
								<spring:message code="word.delete" />
							</button>
						</c:if>
					</div>
				</form:form>
			</div>
		</c:otherwise>
	</c:choose>
</div>

<jsp:include page="footer.jsp"></jsp:include>



