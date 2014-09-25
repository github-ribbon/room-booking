<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<jsp:include page="header.jsp">
	<jsp:param value="roomBooking" name="appName" />
</jsp:include>




<div id="main_container">
	<h1 id="page_title">
		<spring:message code="title.roomAttributeTypeDetails" />
	</h1>
	<c:choose>
		<c:when test="${isRoomAttributeTypePKIncorrect.equals(true)}">
			<div class="error">
				<spring:message code="incorrect.ratPK" />
			</div>
		</c:when>
		<c:otherwise>
			<nav id="context_menu">
				<a
					href="<c:url value="/client/room/by-room-attribute-type?room_attribute_type_id=${roomAttributeType.id.roomAttributeTypeId}" />"><spring:message
						code="word.rooms" /></a>

			</nav>
			<div class="content">
				<c:url value="/client/room-attribute-type/manage" var="url" />
				<form:form modelAttribute="roomAttributeType" action="${url}"
					method="post">
					<div class="field">
						<label class="key"><spring:message
								code="db.roomAttributeId" /></label>
						<div class="field_value">
							<c:out value="${roomAttributeType.id.roomAttributeTypeId}"></c:out>
						</div>
					</div>
					<div class="field">
						<label class="normal"><spring:message
								code="db.description" /></label>
						<div class="field_value">
							<c:out value="${roomAttributeType.description}"></c:out>
						</div>
					</div>
					<div class="button_container"></div>
				</form:form>
			</div>
		</c:otherwise>
	</c:choose>
</div>

<jsp:include page="footer.jsp"></jsp:include>


