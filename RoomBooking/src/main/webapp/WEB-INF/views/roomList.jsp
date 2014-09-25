<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>

<jsp:include page="header.jsp">
	<jsp:param value="objectManagement" name="appName" />
</jsp:include>



<div id="main_container">
	<h1 id="page_title">
		<spring:message code="word.rooms" />
	</h1>
	<c:choose>
		<c:when test="${isBuildingPKIncorrect.equals(true)}">
			<div class="error">
				<spring:message code="incorrect.buildingPK" />
			</div>
		</c:when>
		<c:otherwise>
			<nav id="context_menu">

				<a href="<c:url value="/campus/manage?campus_id=${campusId}" />"><spring:message
						code="word.campus" /></a> &raquo; <a
					href="<c:url value="/building?campus_id=${campusId}" />"><spring:message
						code="word.buildings" /></a> &raquo; <a
					href="<c:url value="/building/manage?campus_id=${campusId}&building_id=${buildingId}" />"><spring:message
						code="word.building" /></a> &raquo; <a
					href="<c:url value="/room/new?campus_id=${campusId}&building_id=${buildingId}" />"><spring:message
						code="word.newRoom" /></a>
			</nav>
			<div class="content">
				<c:choose>
					<c:when test="${empty rooms}">
						<div class="notification">
							<spring:message code="empty.rooms" />
						</div>
					</c:when>
					<c:otherwise>



						<!-- Pagination -->
						<c:url var="pag_link" value="${pageContext.request.requestURI}" />
						<jsp:include page="pagination.jsp"></jsp:include>





						<div class="table_container">
							<table class="buttons_included">
								<tr>
									<td></td>
									<td class="key"><spring:message code="db.campusId" /></td>
									<td class="key"><spring:message code="db.buildingId" /></td>
									<td class="key"><spring:message code="db.roomId" /></td>
									<td><spring:message code="db.description" /></td>
								</tr>
								<c:forEach items="${rooms}" var="item">
									<tr>
										<td>
											<form action="<c:url value="/room/manage" />" method="get">
												<input type="hidden" name="campus_id"
													value="<c:out value="${item.id.campusId}"></c:out>" /> <input
													type="hidden" name="building_id"
													value="<c:out value="${item.id.buildingId}"></c:out>" /> <input
													type="hidden" name="room_id"
													value="<c:out value="${item.id.roomId}"></c:out>" />

												<button type="submit">
													<spring:message code="word.advance" />
												</button>
											</form>
										</td>
										<td class="dark_value"><c:out value="${item.id.campusId}"></c:out>
										</td>
										<td class="dark_value"><c:out
												value="${item.id.buildingId}"></c:out></td>
										<td class="dark_value"><c:out value="${item.id.roomId}"></c:out></td>
										<td><c:out value="${item.description}"></c:out></td>
									</tr>
								</c:forEach>
							</table>
						</div>
						<!-- table_container -->


						<!-- Pagination -->
						<c:url var="pag_link" value="${pageContext.request.requestURI}" />
						<jsp:include page="pagination.jsp"></jsp:include>



					</c:otherwise>
				</c:choose>
			</div>
		</c:otherwise>
	</c:choose>
</div>

<jsp:include page="footer.jsp"></jsp:include>

