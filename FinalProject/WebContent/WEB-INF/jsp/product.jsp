<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<fmt:setLocale value="${currentLocale}" />
<fmt:setBundle basename="property/local" />
<html>
<head>
<title><fmt:message key="product_page.title" /></title>

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
			<div class="col-8 mx-auto my-lg-4 p-3 bg-light">
				<form method="post" action="controller" autocomplete="off">
					<img class="card-img-top"
						src="image/${product.getImage().getName()}.jpg"
						alt="Card image cap">

					<h1 style="text-align: center" class="display-3">${product.getTitle()}</h1>
					<h3 align="center"><fmt:message key="product_actions.price"/></h3>
					<h5 style="text-align: center" class="lead">${product.getPrice()}</h5>
					<h3 align="center"><fmt:message key="product_actions.facture"/></h3>
					<h5 style="text-align: center" class="lead">${product.getFacture()}</h5>
					<h3 align="center"><fmt:message key="product_actions.applicationArea"/></h3>
					<h5 style="text-align: center" class="lead">${product.getApplicationArea()}</h5>
					<c:if test="${user.getRole().toString().equals(\"USER\")}">
						<div class="form-row text-center">
							<div class="col-12">
								<form method="post" action="controller" autocomplete="off">
									<input type="hidden" name="commandName"
										value="add_product_to_basket_command">
									<button class="btn btn-secondary nav-link btn-block"
										name="productId" value="${product.getProductId()}">
										<fmt:message key="product_page.add_product_to_basket" />
									</button>
								</form>
							</div>
						</div>
					</c:if>
					<c:if test="${user.getRole().toString().equals(\"ADMIN\")}">
						<form method="post" action="controller" autocomplete="off">
							<input type="hidden" name="commandName"
								value="product_actions_page">
							<button class="btn btn-secondary nav-link btn-block"
								name="productId" value="${product.getProductId()}">
								<fmt:message key="product_page.edit_product" />
							</button>
						</form>
					</c:if>
				</form>
			</div>
		</div>
	</main>
	<footer>
		<jsp:include page="footer.jsp" />
	</footer>
</body>
</html>