<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<fmt:setLocale value="${currentLocale}" />
<fmt:setBundle basename="property/local" />

<html>
<head>
<title><fmt:message key="fill_up_balance.title" /></title>

</head>
<body style="color: Black; background-color: #fff1e4">
	
	<script>
        document.addEventListener('keydown', function (event) {
            if (event.code === 'F5') {
            	event.preventDefault();
            }
        })
    </script>


	<jsp:include page="header.jsp" />

	<main>
		<div class="container">
			<div class="col-4 mx-auto my-lg-4 p-3 bg-light">
				<form method="post" action="controller" autocomplete="off">
					<div>
						<label for="amount"><fmt:message
								key="fill_up_balance.amount" /></label> <input id="amount"
							class="form-control" required type="text" name="moneyAmount"
							maxlength="7"
							oninvalid="this.setCustomValidity('<fmt:message key="fill_up_balance.price_validator"/>')"
							onchange="this.setCustomValidity('')"
							pattern="^[1-9]\d{0,4}(\.\d{0,2})?$"
							title='<fmt:message key="fill_up_balance.price_validator"/>'><br />
					</div>
					<c:if test="${incorrectMoneyAmount}">
						<div style="color: red">
							<p>
								<fmt:message key="fill_up_balance.incorrect_money_amount" />
							</p>
						</div>
					</c:if>
					<div class="form-row text-center">
						<div class="col-12">
							<button type="submit" class="btn btn-secondary"
								name="commandName" value="fill_up_balance_command">
								<fmt:message key="fill_up_balance.fill_up_button" />
							</button>
						</div>
					</div>
				</form>
			</div>
		</div>
	</main>
	<footer>
		<jsp:include page="footer.jsp" />
	</footer>
</body>
</html>