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

			<li class="sub"><a class="shine"
				href="<c:url value="/campus" />"><spring:message
						code="word.campuses" /></a></li>

			<li class="sub"><a class="shine"
				href="<c:url value="/building/" />"><spring:message
						code="word.buildings" /></a></li>


			<li class="sub"><a class="shine" href="<c:url value="/room/" />"><spring:message
						code="word.rooms" /></a></li>

			<li class="sub"><a class="shine"
				href="<c:url value="/room-attribute-type" />"><spring:message
						code="word.roomAttributeTypes" /></a></li>

			<li class="sub"><a class="shine submenu"><spring:message
						code="word.newObject" /> <b>&or;</b></a>
				<ul style="white-space: nowrap">

					<li><a class="shine submenu"
						href="<c:url value="/campus/new" />"><spring:message
								code="word.campus" /></a></li>

					<li><a class="shine submenu"
						href="<c:url value="/building/new" />"><spring:message
								code="word.building" /></a></li>


					<li><a class="shine submenu"
						href="<c:url value="/room/new" />"><spring:message
								code="word.room" /></a></li>

					<li><a class="shine submenu"
						href="<c:url value="/room-attribute-type/new" />"><spring:message
								code="word.rat" /></a></li>

				</ul></li>
		</ul>
	</nav>
</div>
