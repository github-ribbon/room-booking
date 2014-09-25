<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>

<jsp:include page="header.jsp">
	<jsp:param value="" name="appName" />
</jsp:include>

<div id="main_container">
	<h1 id="page_title">
		<spring:message code="title.resetingPassword" />
	</h1>
	<nav id="context_menu"></nav>
	<div class="content">
		<c:choose>
			<c:when test="${isSuccess.equals(true)}">
				<div class="notification">
					<spring:message code="resetingPasswordSuccess" />
				</div>
			</c:when>
			<c:when test="${isFailure.equals(true)}">
				<div class="error">
					<spring:message code="resetingPasswordFailure" />
				</div>
				<c:url value="/forgotten-password" var="url" />
				<form action="${url}" method="post">
					<div class="field">
						<label class="normal">Login</label> <input type="text"
							name="login" value="<c:out value="${login}"></c:out>" />
					</div>
					<div class="field">
						<label class="normal">Email</label> <input type="text"
							name="email" value="<c:out value="${email}"></c:out>" />
					</div>
					<div class="button_container">
						<button type="submit">
							<spring:message code="word.reset" />
						</button>
					</div>
				</form>
			</c:when>
			<c:otherwise>
				<c:url value="/forgotten-password" var="url" />
				<form action="${url}" method="post">

					<input type="hidden" name="${_csrf.parameterName}"
						value="${_csrf.token}" />

					<div class="field">
						<label class="normal">Login</label> <input type="text"
							name="login" />
					</div>
					<div class="field">
						<label class="normal">Email</label> <input type="text"
							name="email" />
					</div>
					<div class="button_container">
						<button type="submit">
							<spring:message code="word.reset" />
						</button>
					</div>
				</form>
			</c:otherwise>
		</c:choose>
	</div>
</div>

<jsp:include page="footer.jsp"></jsp:include>



