<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<fmt:setLocale value="${currentLocale}" />
<fmt:setBundle basename="property/local" />

<html>
<head>
<script> 
document.onkeydown = function(e) {
    if (e.keyCode == 116) {
        e.preventDefault();
    }
};
</script> 

<title>Main</title>
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<link rel="stylesheet"
	href="https://pro.fontawesome.com/releases/v5.10.0/css/all.css"
	integrity="sha384-AYmEC3Yw5cVb3ZcuHtOA93w35dYTsvhLPVnYs9eStHfGJvOvKxVfELGroGkvsg+p"
	crossorigin="anonymous" />
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/bootstrap/bootstrap.css">
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/css/main.css">
</head>
<body>

	<header>
		<div class="navbar navbar-inverse navbar-fixed-top">
			<div class="container">
				<div class="navbar-header">
					<form method="post" action="controller">
						<button class="btn btn-secondary nav-link button-margin"
							name="commandName" value="main_page">
							<fmt:message key="header.main_button" />
						</button>
					</form>
				</div>
				<div>

					<ul class="nav">
						<li class="nav-item">
							<form method="post" action="controller">
								<button class="btn btn-secondary nav-link button-margin"
									name="commandName" value="contact_page">
									<fmt:message key="header.contact_button" />
								</button>
							</form>
						</li>
						<li class="nav-item">
							<form method="post" action="controller">
								<button class="btn btn-secondary nav-link button-margin"
									name="commandName" value="catalog_page">
									<fmt:message key="header.catalog_button" />
								</button>
							</form>
						</li>

						<c:if test="${user.role.toString().equals(\"USER\")}">
							<li class="nav-item">
								<form method="post" action="controller">
									<button class="btn btn-secondary nav-link button-margin"
										name="commandName" value="basket_page">
										<fmt:message key="header.basket_button" />
									</button>
								</form>
							</li>
						</c:if>
						<c:if test="${user != null}">
							<li class="nav-item">
								<form method="post" action="controller">
									<button class="btn btn-secondary nav-link button-margin"
										name="commandName" value="personal_account_page">
										<fmt:message key="header.personal_account_button" />
									</button>
								</form>
							</li>
							<li class="nav-item">
								<form method="post" action="controller">
									<button class="btn btn-secondary nav-link button-margin"
										name="commandName" value="logout_command">
										<fmt:message key="header.logout_button" />
									</button>
								</form>
							</li>
						</c:if>
						<c:if test="${user == null }">
							<li class="nav-item">
								<form method="post" action="controller">
									<button class="btn btn-secondary nav-link button-margin"
										name="commandName" value="login_page">
										<fmt:message key="header.login_button" />
									</button>
								</form>
							</li>
							<li class="nav-item">
								<form method="post" action="controller">
									<button class="btn btn-secondary nav-link button-margin"
										name="commandName" value="registration_page">
										<fmt:message key="header.registration_button" />
									</button>
								</form>
							</li>
						</c:if>
						<c:if test="${user.role.toString().equals(\"ADMIN\")}">
							<li class="nav-item">
								<form method="post" action="controller">
									<button class="btn btn-secondary nav-link button-margin"
										name="commandName" value="admin_page">
										<fmt:message key="header.admin_button" />
									</button>
								</form>
							</li>
						</c:if>
						<li class="nav-item">
							<form action="controller" method="post">
								<input type="hidden" name="commandName"
									value="switch_language_command" /> <input type="hidden"
									name="newLocale" value="en" />
								<button class="btn btn-secondary nav-link button-margin">
									<fmt:message key="local.en" />
								</button>
							</form>
						</li>
						<li class="nav-item">
							<form action="controller" method="post">
								<input type="hidden" name="commandName"
									value="switch_language_command" /> <input type="hidden"
									name="newLocale" value="ru" />
								<button class="btn btn-secondary nav-link button-margin">
									<fmt:message key="local.ru" />
								</button>
							</form>
						</li>
					</ul>
				</div>
			</div>
		</div>
	</header>
</body>
</html>