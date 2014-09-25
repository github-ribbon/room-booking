<%@page import="java.lang.NullPointerException"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>


<div class="small_imit">
	<nav id="top_menu">
		<a class="shine" href="<c:url value="/panel" />"><spring:message
				code="title.welcome" /></a><a class="shine"
			href="<c:url value="/panel/my-user-groups/" />"><spring:message
				code="title.myUserGroups" /></a><a class="shine"
			href="<c:url value="/panel/account-settings" />"><spring:message
				code="title.accountSettings" /></a><a class="shine"
			href="<c:url value="/panel/support" />"><spring:message
				code="title.support" /></a>
	</nav>
</div>