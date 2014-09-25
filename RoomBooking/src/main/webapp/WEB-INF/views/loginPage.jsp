<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>

<jsp:include page="header.jsp">
	<jsp:param value="" name="appName" />
</jsp:include>

<div id="main_container">
	<h1 id="page_title">
		<spring:message code="title.login" />
	</h1>
	<nav id="context_menu"></nav>
	<div class="content">
		<c:choose>
			<c:when test="${isAuthenticationError.equals(true)}">
				<div class="error">
					<spring:message code="authenticationError" />
				</div>
			</c:when>
			<c:when test="${isLogout.equals(true)}">
				<div class="notification">
					<spring:message code="logoutSuccess" />
				</div>
			</c:when>
		</c:choose>
		<form class="login-form" action="j_spring_security_check"
			method="post">
			<div class="field">
				<label class="normal">Login</label> <input id="j_username"
					name="j_username" type="text" />
			</div>
			<div class="field">
				<label class="normal"><spring:message code="db.password" /></label>
				<input id="j_password" name="j_password" type="password" />
			</div>
			<div class="field">

				<input id="j_remember" name="_spring_security_remember_me"
					type="checkbox" />
				<spring:message code="rememberMe" />
			</div>

			<input type="hidden" name="${_csrf.parameterName}"
				value="${_csrf.token}" />

			<div class="button_container">
				<button type="submit">
					<spring:message code="word.logIn" />
				</button>
			</div>
			<a href="<c:url value="/forgotten-password" />"><spring:message
					code="word.resetPassword" /></a>
		</form>
	</div>
</div>

<jsp:include page="footer.jsp"></jsp:include>
