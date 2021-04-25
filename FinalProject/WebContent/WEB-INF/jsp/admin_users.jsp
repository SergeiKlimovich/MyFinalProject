<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="tag" uri="customTags"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<fmt:setLocale value="${currentLocale}" />
<fmt:setBundle basename="property/local" />

<html>
<head>
<title><fmt:message key="admin_users_page.title" /></title>

<meta name="viewport" content="width=device-width, initial-scale=1.0">

<link rel="stylesheet"
	href="${pageContext.request.contextPath}/bootstrap/bootstrap.css">
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/css/styles.css">

</head>
<body style="color: Black; background-color: #fff1e4">
	<header>
		<jsp:include page="header.jsp" />
	</header>
	<main>
		<div class="container">
			<div class="col-12 mx-auto my-lg-4 p-3 bg-light">
				<c:if test="${users.size() != 0}">
					<div class="col-6">
						<form method="post" action="controller">
							<input type="text" class="form-control" name="searchUsersQuery"
								placeholder="<fmt:message key="admin_users_page.user_search"/>">
							<div class="form-row text-center">
								<div class="col-12">
									<button class="btn btn-secondary button-margin"
										name="commandName" value="find_users_command">
										<fmt:message key="admin_users_page.search" />
									</button>
								</div>
							</div>
						</form>
					</div>
					<table class="table">
						<thead class="thead-light">
							<tr>
								<th><fmt:message key="admin_users_page.name" /></th>
								<th><fmt:message key="admin_users_page.surname" /></th>
								<th><fmt:message key="admin_users_page.patronymic" /></th>
								<th><fmt:message key="admin_users_page.role" /></th>
								<th><fmt:message key="admin_users_page.status" /></th>
								<th><fmt:message key="admin_users_page.email" /></th>
								<th><fmt:message key="admin_users_page.blocking" /></th>
								<th><fmt:message key="admin_users_page.history" /></th>
							</tr>
						</thead>
						<tag:user-list-custom-tag />
					</table>
				</c:if>
				<c:if test="${users.size() == 0}">
					<h3>
						<fmt:message key="admin_users_page.no_result_search" />
					</h3>
				</c:if>
			</div>
		</div>
	</main>
	<footer>
		<jsp:include page="footer.jsp" />
	</footer>
</body>
</html>