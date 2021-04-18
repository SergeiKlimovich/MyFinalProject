<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<fmt:setLocale value="${currentLocale}" />
<fmt:setBundle basename="property/local" />

<html>
<head>
<title>Koнтакты</title>

<meta name="viewport" content="width=device-width, initial-scale=1.0">
</head>
<body style="color:Black; background-color:#fff1e4">
	<header>
		<jsp:include page="header.jsp" />
	</header>
	<main>
		<div class="container">
		<div style="text-align: center;">
			<h1>Контакты:</h1>
			<h10>Связаться с нами можно одним из следующих способов:</h10>
			<ul class="list-group">
				<h10><strong>Шоу-рум</strong>: г. Минск, ул. Ангарская, 2, корпус
					2, офис 2</h10>
				<h10><strong>Склад</strong>: г. Минск, ул. Ангарская, 1</h10>
				<h10><strong>Электронная почта</strong>: <a
							href="mailto:RockStone@gmail.com" target="_blank">RockStone@gmail.com</a></h10>
				<h10><strong>Телефон</strong>: +375(29)2331122.</h10>
				<iframe
						src="https://www.google.com/maps/embed?pb=!1m18!1m12!1m3!1d2352.8665352425546!2d27.671704015751!3d53.863021180090456
        !2m3!1f0!2f0!3f0!3m2!1i1024!2i768!4f13.1!3m3!1m2!1s0x46dbcdf2de7b3ef9%3A0xc3c43bda1a95657!2svulica%20Anharskaja%201%2C%20Minsk
        !5e0!3m2!1sen!2sby!4v1616703590189!5m2!1sen!2sby"
						width="1100" height="450" style="border: 1;" allowfullscreen=""loading="lazy"></iframe>
			</ul>
</div>
		</div>
	</main>
	<footer>
		<jsp:include page="footer.jsp" />
	</footer>
</body>
</html>