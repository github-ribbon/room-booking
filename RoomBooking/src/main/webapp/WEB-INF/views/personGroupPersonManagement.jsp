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
		<spring:message code="title.personGroupPersonDetails" />
	</h1>
	<c:choose>
		<c:when test="${isPersonGroupPersonPKIncorrect.equals(true)}">
			<div class="error">
				<spring:message code="incorrect.personGroupPersonPK" />
			</div>
		</c:when>
		<c:otherwise>
			<nav id="context_menu"></nav>
			<div class="content">
				<c:url value="/person-group-person/manage" var="url" />
				<form:form modelAttribute="personGroupPerson" action="${url}"
					method="post">
					<form:hidden path="id.personGroupId" />
					<form:hidden path="id.personId" />
					<div class="field">
						<label class="key"><spring:message
								code="word.personGroupName" /></label>
						<div class="field_value">
							<c:out value="${personGroupPerson.id.personGroupId}"></c:out>
						</div>

						<a class="button"
							href="<c:url value="/person-group/manage?person_group_id=${personGroupPerson.id.personGroupId}" />"><spring:message
								code="word.advance" /></a>
						<form:errors path="id.personGroupId" cssClass="warning" />
					</div>

					<div class="field">
						<label class="normal"><spring:message code="word.person" /></label>
						<div class="field_value">
							<c:out value="${person}"></c:out>
						</div>
						<a class="button"
							href="<c:url value="/person/manage?person_id=${personGroupPerson.id.personId}" />"><spring:message
								code="word.advance" /></a>
						<form:errors path="id.personId" cssClass="warning" />
					</div>
					<div class="button_container">
						<button type="submit" class="button" name="delete">
							<spring:message code="word.delete" />
						</button>
					</div>
				</form:form>
			</div>
		</c:otherwise>
	</c:choose>
</div>

<jsp:include page="footer.jsp"></jsp:include>


