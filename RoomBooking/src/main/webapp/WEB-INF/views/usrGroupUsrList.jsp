<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>

<jsp:include page="header.jsp">
	<jsp:param value="groupManagement" name="appName" />
</jsp:include>



<div id="main_container">
	<h1 id="page_title">
		<spring:message code="word.userGroupUsers" />
	</h1>
	<c:choose>
		<c:when test="${isUsrGroupPKIncorrect.equals(true)}">
			<div class="error">
				<spring:message code="incorrect.usrGroupPK" />
			</div>
		</c:when>
		<c:otherwise>
			<nav id="context_menu">
				<a class="button"
					href="<c:url value="/usr-group/manage?usr_group_id=${usrGroupId}" />"><spring:message
						code="word.userGroup" /></a> &raquo; <a
					href="<c:url value="/usr-group-usr/new?usr_group_id=${usrGroupId}" />"><spring:message
						code="word.newUserGroupUser" /></a>

			</nav>
			<div class="content">
				<c:choose>
					<c:when test="${empty usrGroupUsers}">
						<div class="notification">
							<spring:message code="empty.userGroupUsers" />
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
									<td class="key"><spring:message code="word.userGroupName" /></td>
									<td>Login</td>
								</tr>
								<c:forEach items="${usrGroupUsers}" var="item">
									<tr>
										<td>
											<form action="<c:url value="/usr-group-usr/manage" />"
												class="small" method="get">
												<input type="hidden" name="usr_group_id"
													value="<c:out value="${item.id.usrGroupId}"></c:out>" /> <input
													type="hidden" name="usr_id"
													value="<c:out value="${item.id.usrId}"></c:out>" />
												<button title="" type="submit" class="button">
													<spring:message code="word.advance" />
												</button>
											</form>
										</td>
										<td class="dark_value"><c:out
												value="${item.id.usrGroupId}"></c:out></td>
										<td><c:out value="${item.id.usrId}"></c:out></td>
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

