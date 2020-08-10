<%@ page import="com.model.User" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<c:url value="/view/client/static" var="url"></c:url>
<div class="topbar-v3">
	<div class="container">
		<div class="row">
			<c:choose>
				<c:when test="${sessionScope.account == null}">
					<div class="col-sm-12">
						<ul class="list-inline right-topbar pull-right">
							<li><a href="${pageContext.request.contextPath }/login">Login</a>
								| <a href="${pageContext.request.contextPath }/register">Register</a></li>
							<li><a href="/product/seach"><i class="search fa fa-search search-button"></i></a></li>
						</ul>
					</div>
				</c:when>
				<c:otherwise>
					<% User u=(User) session.getAttribute("account");
						if(u.getRoleId()==1) {%>
					<div class="col-sm-12">
						<ul class="list-inline right-topbar pull-right">
							<li><a href="${pageContext.request.contextPath }/admin">Dashboard</a> | <a
									href="${pageContext.request.contextPath }/logout">Logout</a></li>
							<li><i class="search fa fa-search search-button"></i></li>
						</ul>
					</div>
					<%} else {%>
					<div class="col-sm-12">
						<ul class="list-inline right-topbar pull-right">
							<li><a href="${pageContext.request.contextPath }/member/myaccount">My Account</a> | <a
									href="${pageContext.request.contextPath }/logout">Logout</a></li>
							<li><i class="search fa fa-search search-button"></i></li>
						</ul>
					</div>
					<%}%>

				</c:otherwise>
			</c:choose>



		</div>
	</div>
	<!--/container-->
</div>
