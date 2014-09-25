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
		<spring:message code="title.personGroupDetails" />
	</h1>
	<c:choose>
		<c:when test="${isPersonGroupPKIncorrect.equals(true)}">
			<div class="error">
				<spring:message code="incorrect.personGroupPK" />
			</div>
		</c:when>
		<c:otherwise>
			<nav id="context_menu">
				<a class="button"
					href="<c:url value="/person-group-person/by-person-group?person_group_id=${personGroup.id.personGroupId}" />"><spring:message
						code="word.personGroupPersons" /></a>
			</nav>
			<div class="content">
				<c:url value="/person-group/manage" var="url" />
				<form:form modelAttribute="personGroup" action="${url}"
					method="post">
					<div class="field">
						<label class="key"><spring:message code="db.name" /></label>
						<div class="field_value">
							<c:out value="${personGroup.id.personGroupId}"></c:out>
						</div>
						<form:hidden path="id.personGroupId" />
						<form:errors path="id.personGroupId" cssClass="warning" />
					</div>
					<div class="field">
						<label class="normal"><spring:message
								code="db.description" /></label>
						<form:textarea path="description" />
						<form:errors path="description" cssClass="warning" />
					</div>
					<div class="button_container">
						<button type="submit" name="update">
							<spring:message code="word.save" />
						</button>
						<c:if
							test="${empty personGroup.personGroupPersons&&empty personGroup.reservations}">
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


