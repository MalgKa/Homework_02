<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<p>I'm first.jsp view</p>
<a href="<c:out value='/second'/>">Go to third using RedirectController ->action /second</a>
<%--<a href="/second">Go to third using RedirectController ->action /second</a>--%>
</body>
</html>
