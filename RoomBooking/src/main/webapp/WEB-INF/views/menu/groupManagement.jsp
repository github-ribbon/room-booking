<%@page import="java.lang.NullPointerException"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>



<div class="small_imit">

	<nav id="top_menu_second_layer"
		style="position: absolute; margin-top: -5px;">

		<ul class="menu">

			<li class="sub"><a href="<c:url value="/person" />"
				class="submenu"><spring:message code="word.persons" /> <b>&or;</b></a>
				<ul style="white-space: nowrap">
					<li><a class="submenu" href="<c:url value="/person-group" />"><spring:message
								code="word.personGroups" /></a></li>
					<li><a class="submenu"
						href="<c:url value="/person-group-person" />"><spring:message
								code="word.personGroupPersons" /></a></li>


				</ul></li>


			<li class="sub"><a href="<c:url value="/usr" />" class="submenu"
				style="text-align: center"><spring:message code="word.users" />
					<b>&or;</b></a>
				<ul style="white-space: nowrap">
					<li><a class="submenu" href="<c:url value="/usr-group" />"><spring:message
								code="word.userGroups" /></a></li>
					<li><a class="submenu" href="<c:url value="/usr-group-usr" />"><spring:message
								code="word.userGroupUsers" /></a></li>


				</ul></li>



			<li class="sub"><a class="shine submenu"><spring:message
						code="word.newObject" /> <b>&or;</b></a>
				<ul style="white-space: nowrap">
					<li><a class="shine submenu"
						href="<c:url value="/person/new" />"><spring:message
								code="word.person" /></a></li>
					<li><a class="shine submenu"
						href="<c:url value="/person-group/new" />"><spring:message
								code="word.personGroup" /></a></li>
					<li><a class="shine submenu"
						href="<c:url value="/person-group-person/new" />"><spring:message
								code="word.personGroupPerson" /></a></li>
					<li style="border-top: solid 5px rgb(40, 132, 152)"></li>
					<li><a class="shine submenu" href="<c:url value="/usr/new" />"><spring:message
								code="word.user" /></a></li>
					<li><a class="shine submenu"
						href="<c:url value="/usr-group/new" />"><spring:message
								code="word.userGroup" /></a></li>
					<li><a class="shine submenu"
						href="<c:url value="/usr-group-usr/new" />"><spring:message
								code="word.userGroupUser" /></a></li>


				</ul></li>

		</ul>
	</nav>

</div>


