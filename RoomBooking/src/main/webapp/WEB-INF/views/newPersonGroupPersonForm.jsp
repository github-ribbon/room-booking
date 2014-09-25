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
		<spring:message code="title.creatingPersonGroupPerson" />
	</h1>
	<nav id="context_menu"></nav>
	<div class="content">
		<c:url value="/person-group-person/new" var="url" />
		<form:form modelAttribute="personGroupPerson" action="${url}"
			method="post">
			<form:hidden path="id.personGroupId" />
			<form:hidden path="id.personId" />
			<div class="field">
				<label class="key"><spring:message
						code="word.personGroupName" /></label>
				<c:if test="${not empty personGroupPerson.id.personGroupId}">
					<div class="field_value">
						<c:out value="${personGroupPerson.id.personGroupId}"></c:out>
					</div>
				</c:if>
				<button type="submit" class="button" name="choose_person_group">
					<spring:message code="word.choose" />
				</button>
				<form:errors path="id.personGroupId" cssClass="warning" />
			</div>
			<div class="field">
				<label class="key"><spring:message code="word.person" /></label>
				<c:if test="${not empty person}">
					<div class="field_value">
						<c:out value="${person}"></c:out>
					</div>
				</c:if>
				<button type="submit" class="button" name="choose_person">
					<spring:message code="word.choose" />
				</button>
				<form:errors path="id.personId" cssClass="warning" />
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


