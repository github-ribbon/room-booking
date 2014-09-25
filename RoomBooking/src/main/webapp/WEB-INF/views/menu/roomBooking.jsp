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
						href="<c:url value="/client/campus" />"><spring:message
								code="word.campuses" /></a></li>

					<li><a class="shine submenu"
						href="<c:url value="/client/building/" />"><spring:message
								code="word.buildings" /></a></li>


					<li><a class="shine submenu"
						href="<c:url value="/client/room/" />"><spring:message
								code="word.rooms" /></a></li>

					<li><a class="shine submenu"
						href="<c:url value="/client/room-attribute-type" />"><spring:message
								code="word.roomAttributeTypes" /></a></li>




				</ul></li>


			<li class="sub"><a href="<c:url value="/client/new" />"
				class="shine submenu"><spring:message code="word.newReservation" /></a>

			</li>


			<li class="sub"><a class="shine submenu"><spring:message
						code="word.myReservations" /> <b>&or;</b></a>
				<ul>

					<li><a class="shine submenu"
						href="<c:url value="/client/my-reservations/booked" />"><spring:message
								code="word.myBookedRes" /></a></li>
					<li><a class="shine submenu"
						href="<c:url value="/client/my-reservations/waiting" />"><spring:message
								code="word.myWaitingRes" /></a></li>
					<li><a class="shine submenu"
						href="<c:url value="/client/my-reservations/cancelled" />"><spring:message
								code="word.myCancelledRes" /></a></li>
					<li><a class="shine submenu"
						href="<c:url value="/client/my-reservations" />"><spring:message
								code="word.all" /></a></li>

				</ul></li>

			<li class="sub"><a class="shine submenu"
				href="<c:url value="/client/room-statuses-form" />"><spring:message
						code="word.roomStatuses" /></a></li>

			<li class="sub"><a
				href="<c:url value="/client/reservation-list-by-day-form" />"
				class="shine submenu"><spring:message code="word.resListByDay" /></a>

			</li>

		</ul>

	</nav>

</div>
