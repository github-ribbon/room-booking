<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>






<div class="pagination">
	<ul>
		<c:choose>

			<c:when test="${lastPage>1}">

				<c:set var="pag_link" value="${pag_link}?" />


				<c:forEach items="${param}" var="currentParam" varStatus="counter">
					<c:if test='${!currentParam.key.equals("page")}'>
						<c:if test="${counter.count!=1}">
							<c:set var="pag_link" value="${pag_link}&" />
						</c:if>
						<c:set var="pag_link"
							value="${pag_link}${currentParam.key}=${currentParam.value}" />
					</c:if>
				</c:forEach>



				<c:if
					test="${(fn:length(param)>0)&&(!((fn:length(param)==1)&&(param.page!=null)))}">
					<c:set var="pag_link" value="${pag_link}&" />
				</c:if>


				<c:choose>
					<c:when test="${actualPage==1}">
						<li class="disabled"><a>←</a></li>
					</c:when>
					<c:otherwise>
						<li><a
							href='<c:out value="${pag_link}page=${actualPage-1}"/>'>←</a></li>
					</c:otherwise>

				</c:choose>

				<c:choose>
					<c:when test="${(actualPage-1)<=5}">


						<c:forEach begin="1" end="${actualPage-1}" var="i" step="1">
							<li><a href='<c:out value="${pag_link}page=${i}"/>'>${i}</a></li>
						</c:forEach>

						<li class="disabled"><a><c:out value="${actualPage}" /></a></li>

					</c:when>

					<c:otherwise>

						<li><a href='<c:out value="${pag_link}page=1"/>'>1</a></li>
						<li class="disabled"><a>...</a></li>

						<c:forEach begin="${actualPage-4}" end="${actualPage-1}" var="i"
							step="1">
							<li><a href='<c:out value="${pag_link}page=${i}"/>'>${i}</a></li>
						</c:forEach>

						<li class="disabled"><a><c:out value="${actualPage}" /></a></li>



					</c:otherwise>

				</c:choose>




				<c:choose>
					<c:when test="${(lastPage-actualPage)<=5}">


						<c:forEach begin="${actualPage+1}" end="${lastPage}" var="i"
							step="1">
							<li><a href='<c:out value="${pag_link}page=${i}" />'>${i}</a></li>
						</c:forEach>


					</c:when>

					<c:otherwise>


						<c:forEach begin="${actualPage+1}" end="${actualPage+4}" var="i"
							step="1">
							<li><a href='<c:out value="${pag_link}page=${i}"/>'>${i}</a></li>
						</c:forEach>


						<li class="disabled"><a>...</a></li>

						<li><a href='<c:out value="${pag_link}page=${lastPage}" />'><c:out
									value="${lastPage}" /></a></li>

					</c:otherwise>

				</c:choose>



				<c:choose>
					<c:when test="${actualPage==lastPage}">
						<li class="disabled"><a>→</a></li>
					</c:when>
					<c:otherwise>
						<li><a
							href='<c:out value="${pag_link}page=${actualPage+1}"/>'>→</a></li>
					</c:otherwise>

				</c:choose>




			</c:when>
			<c:otherwise>
			</c:otherwise>
		</c:choose>



	</ul>
</div>


