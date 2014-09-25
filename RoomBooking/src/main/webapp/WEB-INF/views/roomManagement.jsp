<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<jsp:include page="header.jsp">
	<jsp:param value="objectManagement" name="appName" />
</jsp:include>


<div id="main_container">
	<h1 id="page_title">
		<spring:message code="title.roomDetails" />
	</h1>
	<c:choose>
		<c:when test="${isRoomPKIncorrect.equals(true)}">
			<div class="error">
				<spring:message code="incorrect.roomPK" />
			</div>
		</c:when>
		<c:otherwise>
			<nav id="context_menu">
				<a class="button"
					href="<c:url value="/campus/manage?campus_id=${room.id.campusId}" />"><spring:message
						code="word.campus" /></a> &raquo; <a class="button"
					href="<c:url value="/building?campus_id=${room.id.campusId}" />"><spring:message
						code="word.buildings" /></a> &raquo; <a class="button"
					href="<c:url value="/building/manage?campus_id=${room.id.campusId}&building_id=${room.id.buildingId}" />"><spring:message
						code="word.building" /></a> &raquo; <a class="button"
					href="<c:url value="/room?campus_id=${room.id.campusId}&building_id=${room.id.buildingId}" />"><spring:message
						code="word.rooms" /></a> &raquo; <a class="button"
					href="<c:url value="/room-attribute?campus_id=${room.id.campusId}&building_id=${room.id.buildingId}&room_id=${room.id.roomId}" />"><spring:message
						code="word.roomAttributes" /></a> &raquo; <a
					href="<c:url value="/room-manager/by-room?campus_id=${room.id.campusId}&building_id=${room.id.buildingId}&room_id=${room.id.roomId}" />"><spring:message
						code="word.roomManagers" /></a>

			</nav>
			<div class="content">
				<c:url value="/room/manage" var="url" />
				<form:form modelAttribute="room" action="${url}" method="post">
					<form:hidden path="id.campusId" />
					<form:hidden path="id.buildingId" />
					<form:hidden path="id.roomId" />
					<div class="field">
						<label class="key"><spring:message code="db.campusId" /></label>
						<div class="field_value">
							<c:out value="${room.id.campusId}"></c:out>
						</div>
					</div>
					<div class="field">
						<label class="key"><spring:message code="db.buildingId" /></label>
						<div class="field_value">
							<c:out value="${room.id.buildingId}"></c:out>
						</div>
					</div>
					<div class="field">
						<label class="key"><spring:message code="db.roomId" /></label>
						<div class="field_value">
							<c:out value="${room.id.roomId}"></c:out>
						</div>
					</div>
					<div class="field">
						<label class="normal"><spring:message
								code="db.description" /></label>
						<form:textarea path="description" />
						<form:errors path="description" cssClass="warning" />
					</div>
					<div class="button_container">
						<button type="submit" name="update">
							<spring:message code="word.save" />
						</button>
						<c:if test="${empty room.roomAttributes&&empty room.bookableRoom}">
							<button type="submit" name="delete">
								<spring:message code="word.delete" />
							</button>
						</c:if>
					</div>
				</form:form>
				<c:choose>
					<c:when test="${isRoomBookable.equals(true)}">
						<div class="notification">
							<spring:message code="bookable" />
						</div>
						<c:url value="/room/bookable/manage" var="url" />
						<form:form modelAttribute="bookableRoom" action="${url}"
							method="post">
							<form:hidden path="id.campusId" />
							<form:hidden path="id.buildingId" />
							<form:hidden path="id.roomId" />
							<c:choose>
								<c:when test="${bookableRoom.isRobot.equals(true)}">
									<!-- <div class="notification">
										<spring:message code="autoBookableRoom" />
									</div>-->
								</c:when>
								<c:otherwise>
									<div class="notification">
										<spring:message code="notAutoBookableRoom" />
									</div>
								</c:otherwise>
							</c:choose>
							<c:if test="${not empty bookableRoom.roomManagers}">
								<div class="field">
									<label class="normal"><spring:message
											code="word.autoBookable" /></label>
									<form:radiobutton path="isRobot" value="true" />
									<spring:message code="word.yes" />
									<form:radiobutton path="isRobot" value="false" />
									<spring:message code="word.no" />
								</div>
							</c:if>

							<div class="button_container">
								<c:if test="${not empty bookableRoom.roomManagers}">
									<button type="submit" name="update">
										<spring:message code="word.save" />
									</button>
								</c:if>
								<c:if
									test="${empty bookableRoom.reservations&&empty bookableRoom.roomManagers}">
									<button type="submit" name="delete">
										<spring:message code="word.setAsNotBookable" />
									</button>
								</c:if>
							</div>
						</form:form>
					</c:when>
					<c:otherwise>
						<div class="error">
							<spring:message code="notBookable" />
						</div>
						<c:url value="/room/bookable/new" var="url" />
						<form:form modelAttribute="bookableRoom" action="${url}"
							method="post">
							<form:hidden path="id.campusId" />
							<form:hidden path="id.buildingId" />
							<form:hidden path="id.roomId" />
							<form:hidden path="isRobot" />
							<div class="button_container">
								<button type="submit" name="create">
									<spring:message code="word.setAsBookable" />
								</button>
							</div>
						</form:form>
					</c:otherwise>
				</c:choose>
			</div>
		</c:otherwise>
	</c:choose>
</div>

<jsp:include page="footer.jsp"></jsp:include>


