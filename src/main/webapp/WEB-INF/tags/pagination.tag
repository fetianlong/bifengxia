<%@tag pageEncoding="UTF-8"%>
<%@ attribute name="page" type="org.springframework.data.domain.Page" required="true"%>
<%@ attribute name="paginationSize" type="java.lang.Integer" required="true"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<%
int current =  page.getNumber();
int begin = Math.max(1, current - paginationSize/2);
int end = Math.min(begin + (paginationSize - 1), page.getTotalPages());

request.setAttribute("current", current);
request.setAttribute("begin", begin);
request.setAttribute("end", end);
%>
	<div class="pages">
		<% if (page.hasPreviousPage()){%>
		<a href="?page=${current-1}${searchParams}" class="prev"></a>
	       <%}else{%>
		<a href="#" class="prev"></a>
	       <%} %>
	       <c:forEach var="i" begin="${begin}" end="${end}">
	          <c:choose>
	              <c:when test="${i == current}">
					<a href="?page=${i}${searchParams}" id="page${i}" class="page active">${i}</a>
	              </c:when>
	              <c:otherwise>
					<a href="?page=${i}${searchParams}" id="page${i}" class="page">${i}</a>
	              </c:otherwise>
	          </c:choose>
	      </c:forEach>
	 
	 	 <% if (page.hasNextPage()){%>
		<a class="next" href="?page=${current+1}${searchParams}"></a>
	       <%}else{%>
		<a href="?page=${current+1}${searchParams}" class="next"></a>
       <%} %>
	</div>
       
   