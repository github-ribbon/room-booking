<%@page import="java.lang.NullPointerException"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ page
	import="org.springframework.security.core.context.SecurityContextHolder"%>

<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title><spring:message code="roomBooking" /></title>
<link rel="stylesheet"
	href="<c:url value="/resources/css/styles.css" />" />
<link rel="Shortcut icon"
	href="<c:url value="/resources/images/favicon.ico" />"
	type="image/x-icon" />
<script src="<c:url value="/resources/scripts/jquery.js" />"></script>
<script src="<c:url value="/resources/scripts/script.js" />"></script>
<link rel="stylesheet" type="text/css"
	href="<c:url value="/resources/scripts/css/start/jquery-ui-1.8.23.custom.css" />" />
</head>
<body>
	<header>
		<div id="logo">
			<spring:message code="roomBooking" />
		</div>
	</header>
	<sec:authorize access="authenticated">
		<div id="log_info_box">
			<b><%=request.getRemoteUser()%></b> <a
				href="<c:url value="/logout" />" class="shine"> <img
				src="<c:url value="/resources/images/logout.png" />" />
			</a>
		</div>
		<div id="app_name">
			<c:choose>
				<c:when test="${param.appName.equals('roomBooking')}">
					<nav id="top_menu_second_layer">
						<ul class="menu">
							<li class="sub"><a
								href="<c:url value="/client/my-reservations/booked" />"
								class="shine submenu"><spring:message
										code="appName.roomBooking" /></a>
								<ul id="sub_app_menu">
									<sec:authorize access="hasRole('ROLE_CONFIRMER')">
										<li><a class="shine submenu"
											href="<c:url value="/confirmer/reservations/waiting/upcoming" />"><spring:message
													code="appName.bookingConfirmation" /></a></li>
									</sec:authorize>
									<sec:authorize access="hasRole('ROLE_GROUP')">
										<li><a class="shine submenu"
											href="<c:url value="/usr" />"><spring:message
													code="appName.groupManagement" /></a></li>
									</sec:authorize>
									<sec:authorize access="hasRole('ROLE_ADMIN')">
										<li><a class="shine submenu"
											href="<c:url value="/campus" />"><spring:message
													code="appName.admin" /></a></li>
									</sec:authorize>
									<li><a class="shine submenu"
										href="<c:url value="/panel" />"><spring:message
												code="appName.userPanel" /></a></li>
								</ul></li>
						</ul>
					</nav>
				</c:when>

				<c:when test="${param.appName.equals('userPanel')}">
					<nav id="top_menu_second_layer">
						<ul class="menu">
							<li class="sub"><a href="<c:url value="/panel" />"
								class="shine submenu"><spring:message
										code="appName.userPanel" /></a>
								<ul id="sub_app_menu">
									<sec:authorize access="hasRole('ROLE_CLIENT')">
										<li><a class="shine submenu"
											href="<c:url value="/client/my-reservations/booked" />"><spring:message
													code="appName.roomBooking" /></a></li>
									</sec:authorize>
									<sec:authorize access="hasRole('ROLE_CONFIRMER')">
										<li><a class="shine submenu"
											href="<c:url value="/confirmer/reservations/waiting/upcoming" />"><spring:message
													code="appName.bookingConfirmation" /></a></li>
									</sec:authorize>
									<sec:authorize access="hasRole('ROLE_GROUP')">
										<li><a class="shine submenu"
											href="<c:url value="/usr" />"><spring:message
													code="appName.groupManagement" /></a></li>
									</sec:authorize>
									<sec:authorize access="hasRole('ROLE_ADMIN')">
										<li><a class="shine submenu"
											href="<c:url value="/campus" />"><spring:message
													code="appName.admin" /></a></li>
									</sec:authorize>
								</ul></li>
						</ul>
					</nav>
				</c:when>

				<c:when test="${param.appName.equals('bookingConfirmation')}">
					<nav id="top_menu_second_layer">
						<ul class="menu">
							<li class="sub"><a
								href="<c:url value="/confirmer/reservations/waiting/upcoming" />"
								class="shine submenu"><spring:message
										code="appName.bookingConfirmation" /></a>
								<ul id="sub_app_menu">
									<sec:authorize access="hasRole('ROLE_CLIENT')">
										<li><a class="shine submenu"
											href="<c:url value="/client/my-reservations/booked" />"><spring:message
													code="appName.roomBooking" /></a></li>
									</sec:authorize>
									<sec:authorize access="hasRole('ROLE_GROUP')">
										<li><a class="shine submenu"
											href="<c:url value="/usr" />"><spring:message
													code="appName.groupManagement" /></a></li>
									</sec:authorize>
									<sec:authorize access="hasRole('ROLE_ADMIN')">
										<li><a class="shine submenu"
											href="<c:url value="/campus" />"><spring:message
													code="appName.admin" /></a></li>
									</sec:authorize>
									<li><a class="shine submenu"
										href="<c:url value="/panel" />"><spring:message
												code="appName.userPanel" /></a></li>
								</ul></li>
						</ul>
					</nav>

				</c:when>
				<c:when test="${param.appName.equals('objectManagement')}">
					<nav id="top_menu_second_layer">
						<ul class="menu">
							<li class="sub"><a href="<c:url value="/campus" />"
								class="shine submenu"><spring:message code="appName.admin" /></a>
								<ul id="sub_app_menu">
									<sec:authorize access="hasRole('ROLE_CLIENT')">
										<li><a class="shine submenu"
											href="<c:url value="/client/my-reservations/booked" />"><spring:message
													code="appName.roomBooking" /></a></li>
									</sec:authorize>
									<sec:authorize access="hasRole('ROLE_CONFIRMER')">
										<li><a class="shine submenu"
											href="<c:url value="/confirmer/reservations/waiting/upcoming" />"><spring:message
													code="appName.bookingConfirmation" /></a></li>
									</sec:authorize>
									<sec:authorize access="hasRole('ROLE_GROUP')">
										<li><a class="shine submenu"
											href="<c:url value="/usr" />"><spring:message
													code="appName.groupManagement" /></a></li>
									</sec:authorize>
									<li><a class="shine submenu"
										href="<c:url value="/panel" />"><spring:message
												code="appName.userPanel" /></a></li>

								</ul></li>
						</ul>
					</nav>
				</c:when>

				<c:when test="${param.appName.equals('groupManagement')}">
					<nav id="top_menu_second_layer">
						<ul class="menu">
							<li class="sub"><a href="<c:url value="/usr" />"
								class="shine submenu"><spring:message
										code="appName.groupManagement" /></a>
								<ul id="sub_app_menu">
									<sec:authorize access="hasRole('ROLE_CLIENT')">
										<li><a class="shine submenu"
											href="<c:url value="/client/my-reservations/booked" />"><spring:message
													code="appName.roomBooking" /></a></li>
									</sec:authorize>
									<sec:authorize access="hasRole('ROLE_CONFIRMER')">
										<li><a class="shine submenu"
											href="<c:url value="/confirmer/reservations/waiting/upcoming" />"><spring:message
													code="appName.bookingConfirmation" /></a></li>
									</sec:authorize>
									<sec:authorize access="hasRole('ROLE_ADMIN')">
										<li><a class="shine submenu"
											href="<c:url value="/campus" />"><spring:message
													code="appName.admin" /></a></li>
									</sec:authorize>
									<li><a class="shine submenu"
										href="<c:url value="/panel" />"><spring:message
												code="appName.userPanel" /></a></li>

								</ul></li>
						</ul>
					</nav>
				</c:when>
			</c:choose>
		</div>


		<c:if
			test="${param.appName.equals('groupManagement')||param.appName.equals('objectManagement')
	||param.appName.equals('roomBooking')||param.appName.equals('bookingConfirmation')
	||param.appName.equals('userPanel')}">
			<jsp:include page="menu/${param.appName}.jsp" />
		</c:if>
	</sec:authorize>