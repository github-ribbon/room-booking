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
		<spring:message code="title.userGroupUserDetails" />
	</h1>
	<c:choose>
		<c:when test="${isUsrGroupUsrPKIncorrect.equals(true)}">
			<div class="error">
				<spring:message code="incorrect.usrGroupUsrPK" />
			</div>
		</c:when>
		<c:otherwise>
			<nav id="context_menu"></nav>
			<div class="content">
				<c:url value="/usr-group-usr/manage" var="url" />
				<form:form modelAttribute="usrGroupUsr" action="${url}"
					method="post">
					<form:hidden path="id.usrGroupId" />
					<form:hidden path="id.usrId" />
					<div class="field">
						<label class="key"><spring:message
								code="word.userGroupName" /></label>
						<div class="field_value">
							<c:out value="${usrGroupUsr.id.usrGroupId}"></c:out>
						</div>
						<a class="button"
							href="<c:url value="/usr-group/manage?usr_group_id=${usrGroupUsr.id.usrGroupId}" />"><spring:message
								code="word.advance" /></a>
						<form:errors path="id.usrGroupId" cssClass="warning" />
					</div>
					<div class="field">
						<label class="normal"><spring:message code="word.user" /></label>
						<div class="field_value">
							<c:out value="${usrGroupUsr.id.usrId}"></c:out>
						</div>
						<a class="button"
							href="<c:url value="/usr/manage?usr_id=${usrGroupUsr.id.usrId}" />"><spring:message
								code="word.advance" /></a>
						<form:errors path="id.usrId" cssClass="warning" />
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


