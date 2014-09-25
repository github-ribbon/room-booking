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
		<spring:message code="title.creatingUserGroupUser" />
	</h1>
	<nav id="context_menu"></nav>
	<div class="content">
		<c:url value="/usr-group-usr/new" var="url" />
		<form:form modelAttribute="usrGroupUsr" action="${url}" method="post">
			<form:hidden path="id.usrGroupId" />
			<form:hidden path="id.usrId" />
			<div class="field">
				<label class="key"><spring:message code="word.userGroupName" /></label>
				<c:if test="${not empty usrGroupUsr.id.usrGroupId}">
					<div class="field_value">
						<c:out value="${usrGroupUsr.id.usrGroupId}"></c:out>
					</div>
				</c:if>
				<button type="submit" class="button" name="choose_usr_group">
					<spring:message code="word.choose" />
				</button>
				<form:errors path="id.usrGroupId" cssClass="warning" />
			</div>
			<div class="field">
				<label class="key"><spring:message code="word.user" /></label>
				<c:if test="${not empty usrGroupUsr.id.usrId}">
					<div class="field_value">
						<c:out value="${usrGroupUsr.id.usrId}"></c:out>
					</div>
				</c:if>
				<button type="submit" class="button" name="choose_usr">
					<spring:message code="word.choose" />
				</button>
				<form:errors path="id.usrId" cssClass="warning" />
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


