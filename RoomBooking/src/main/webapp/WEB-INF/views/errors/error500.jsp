<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>



<jsp:include page="../header.jsp">
	<jsp:param value="" name="appName" />
</jsp:include>


<div id="main_container">


	<h1 id="page_title">
		<spring:message code="appError" />
	</h1>

	<nav id="context_menu"></nav>



	<div class="content">




		<div class="error">
			<spring:message code="error.500" />
		</div>

	</div>
</div>

<jsp:include page="../footer.jsp"></jsp:include>

