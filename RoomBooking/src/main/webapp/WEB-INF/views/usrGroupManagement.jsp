<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>

<jsp:include page="header.jsp">
	<jsp:param value="groupManagement" name="appName" />
</jsp:include>



<div id="main_container">
	<h1 id="page_title">
		<spring:message code="title.userGroupDetails" />
	</h1>
	<c:choose>
		<c:when test="${isUsrGroupPKIncorrect.equals(true)}">
			<div class="error">
				<spring:message code="incorrect.usrGroupPK" />
			</div>
		</c:when>
		<c:otherwise>
			<nav id="context_menu">
				<a class="button"
					href="<c:url value="/usr-group-usr/by-usr-group?usr_group_id=${usrGroup.id.usrGroupId}" />"><spring:message
						code="word.userGroupUsers" /></a>

				<sec:authorize access="hasRole('ROLE_ADMIN')">	
				&raquo;

				<a class="button"
						href="<c:url value="/room-manager/by-usr-group?usr_group_id=${usrGroup.id.usrGroupId}" />"><spring:message
							code="word.managedRooms" /></a>
				</sec:authorize>

			</nav>

			<div class="content">
				<c:url value="/usr-group/manage" var="url" />
				<form:form modelAttribute="usrGroup" action="${url}" method="post">
					<div class="field">
						<label class="key"><spring:message code="db.name" /></label>
						<div class="field_value">
							<c:out value="${usrGroup.id.usrGroupId}"></c:out>
						</div>
						<form:hidden path="id.usrGroupId" />
						<form:errors path="id.usrGroupId" cssClass="warning" />
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
							test="${empty usrGroup.usrGroupUsers&&empty usrGroup.usrGroupAuths&&empty usrGroup.roomManagers}">
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


