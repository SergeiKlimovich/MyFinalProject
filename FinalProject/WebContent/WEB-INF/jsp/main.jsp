<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<fmt:setLocale value="${currentLocale}" />
<fmt:setBundle basename="property/local" />

<html>
<head>
<title>Главная</title>


<meta name="viewport" content="width=device-width, initial-scale=1.0">

</head>
<body style="color: Black; background-color: #fff1e4">
	<header>
		<jsp:include page="header.jsp" />
	</header>
	<main>
		<div class="container">
			<h1 class="display-3" align="center">
				<fmt:message key="main.title" />
			</h1>
		</div>
		
		<center><img src="${pageContext.request.contextPath}/images/image.jpeg"
			alt="Качество"></center>
	
	</main>

	<footer>
		<jsp:include page="footer.jsp" />
	</footer>
</body>
</html>