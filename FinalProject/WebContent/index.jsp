<%@ page language="java" contentType="text/html;
    charset=utf-8"
         pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<body>
<jsp:forward page="controller">
    <jsp:param name="commandName" value="main_page"/>
</jsp:forward>
</body>
</html>