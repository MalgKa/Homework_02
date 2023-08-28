<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <style>
        p{
            display: inline;
        }
    </style>
</head>
<body>
<fieldset>
    <legend>
        <h1>The name and value of cookies only from ListOfCookies</h1>
    </legend>
    <c:forEach items="${listOfCookies}" var="myCookie">
        <div>
            <strong>Cookie name: </strong><p>${myCookie.name}</p>
           <strong>Cookie value: </strong><p> ${myCookie.value}</p><br><br>
        </div>
    </c:forEach>
</fieldset>

<hr style="border: solid 4px olivedrab">
<%--JSTL i jej EL mają niejawny obiekt cookie (zmienna, która jest automatycznie dostępna dla kodu JSP lub EL bez przekazywania z Controllera),
którego można używać na stronach JSP do wyświetlania dowolnych nazw i wartości plików cookie.Dostęp do tego obiektu poniżej:
var atrybut zawiera Map.Entry object, gdzie key to nazwa ciasteczka a value to object Cookie. Dlatego cookieVal.value wyrażone jest jako:javax.servlet.http.Cookie object
stąd też pełna fraza ${cookieVal.value.value} jest odpowiednikiem wywołania Cookie.getValue--%>

   <fieldset>
       <legend>
           <h2>The name and value of each found cookie (not only from the ListOfCookies)</h2>
       </legend>
       <c:forEach  items="${cookie}" var="cookieVal">
           <strong>Cookie name:</strong> <c:out value="${cookieVal.key}"/><br>
           <strong>Cookie value:</strong> <c:out value="${cookieVal.value.value}"/><br><br>
       </c:forEach>
   </fieldset>




</body>
</html>
