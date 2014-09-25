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
			<li class="sub"><a class="shine submenu"><spring:message
						code="word.objects" /> <b>&or;</b></a>
				<ul style="white-space: nowrap">

					<li><a class="shine submenu"
						href="<c:url value="/confirmer/campus" />"><spring:message
								code="word.campuses" /></a></li>

					<li><a class="shine submenu"
						href="<c:url value="/confirmer/building/" />"><spring:message
								code="word.buildings" /></a></li>
					<li><a class="shine submenu"
						href="<c:url value="/confirmer/room/" />"><spring:message
								code="word.rooms" /></a></li>

					<li><a class="shine submenu"
						href="<c:url value="/confirmer/room-attribute-type" />"><spring:message
								code="word.roomAttributeTypes" /></a></li>
				</ul></li>


			<li class="sub"><a class="shine submenu"><spring:message
						code="word.waitingRes" /> <b>&or;</b></a>
				<ul>

					<li><a class="shine submenu"
						href="<c:url value="/confirmer/reservations/waiting/previous" />"><spring:message
								code="word.previousRes" /></a></li>
					<li><a class="shine submenu"
						href="<c:url value="/confirmer/reservations/waiting/upcoming" />"><spring:message
								code="word.upcomingRes" /></a></li>


				</ul></li>

			<li class="sub"><a class="shine submenu"><spring:message
						code="word.bookedRes" /> <b>&or;</b></a>
				<ul>

					<li><a class="shine submenu"
						href="<c:url value="/confirmer/reservations/booked/previous" />"><spring:message
								code="word.previousRes" /></a></li>
					<li><a class="shine submenu"
						href="<c:url value="/confirmer/reservations/booked/upcoming" />"><spring:message
								code="word.upcomingRes" /></a></li>

				</ul></li>

			<li class="sub"><a class="shine submenu"><spring:message
						code="word.cancelledRes" /> <b>&or;</b></a>
				<ul>

					<li><a class="shine submenu"
						href="<c:url value="/confirmer/reservations/cancelled/previous" />"><spring:message
								code="word.previousRes" /></a></li>
					<li><a class="shine submenu"
						href="<c:url value="/confirmer/reservations/cancelled/upcoming" />"><spring:message
								code="word.upcomingRes" /></a></li>

				</ul></li>

			<li class="sub"><a
				href="<c:url value="/confirmer/reservation-list-by-day-form" />"
				class="shine submenu"><spring:message code="word.resListByDay" /></a>

			</li>
		</ul>
	</nav>

</div>



