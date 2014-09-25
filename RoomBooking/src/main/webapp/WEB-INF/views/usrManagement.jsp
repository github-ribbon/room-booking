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
		<spring:message code="title.userDetails" />
	</h1>
	<c:choose>
		<c:when test="${isUsrPKIncorrect.equals(true)}">
			<div class="error">
				<spring:message code="incorrect.usrPK" />
			</div>
		</c:when>
		<c:otherwise>
			<nav id="context_menu">
				<a
					href="<c:url value="/usr-group-usr/by-usr?usr_id=${usr.id.usrId}" />"><spring:message
						code="word.assignedUserGroups" /></a>

			</nav>
			<div class="content">
				<c:url value="/usr/manage" var="url" />
				<form:form modelAttribute="usr" action="${url}" method="post">
					<form:hidden path="id.usrId" />
					<div class="field">
						<label class="key">Login</label>
						<div class="field_value">
							<c:out value="${usr.id.usrId}"></c:out>
						</div>
					</div>
					<div class="field">
						<label class="key"><spring:message code="word.person" /></label>
						<c:if test="${not empty usr.personId}">
							<div class="field_value">
								<c:out value="${person}"></c:out>
							</div>
							<a
								href="<c:url value="/person/manage?person_id=${usr.personId}" />"><spring:message
									code="word.advance" /></a>
						</c:if>
						<form:hidden path="personId" />
						<form:errors path="personId" cssClass="warning" />
					</div>
					<div class="button_container">
						<c:choose>
							<c:when test="${usr.isEnabled.equals(true)}">
								<div class="notification">
									<spring:message code="accountEnabled" />
								</div>
								<button type="submit" name="disable">
									<spring:message code="word.disable" />
								</button>
							</c:when>
							<c:when test="${usr.isEnabled.equals(false)}">
								<div class="error">
									<spring:message code="accountDisabled" />
								</div>
								<button type="submit" name="enable">
									<spring:message code="word.enable" />
								</button>
							</c:when>
						</c:choose>
					</div>
				</form:form>
			</div>
		</c:otherwise>
	</c:choose>
</div>

<jsp:include page="footer.jsp"></jsp:include>



