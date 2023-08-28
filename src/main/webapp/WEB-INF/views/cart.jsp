<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>MY CART</title>
    <style>
        div {
            display: flex;
            gap: 5px;
        }

        .cart {
            display: flex;
            flex-direction: column;
        }

        fieldset {
            display: inline;
            border-color: #fa923f;
        }

        hr {
            width: 20%;
            margin-left: 0;
        }
        button{
            background-color: #fa923f;
        }
        a{
            text-decoration: none;
        }
    </style>
</head>
<body>
<%--widok z CartController--%>
<fieldset>
    <legend>YOUR CART</legend>
    <div class="cart">
        <c:forEach items="${cart}" var="cartItem">
            <p>${cartItem.quantity} x ${cartItem.product.name} ${cartItem.product.price}
                <button><a href="/deleteProduct/${cartItem.product.id}">usuń</a></button>
                <button><a href="/addProduct/${cartItem.product.id}">dodaj</a></button>
            </p>
        </c:forEach>
        <c:set var="sum" value="0"/>
        <c:forEach items="${cart}" var="cartItem">
            <c:set var="sum" value="${sum + cartItem.quantity * cartItem.product.price}"/>
        </c:forEach>
        <p><strong>total order value: <fmt:formatNumber value="${sum}" type="number" minFractionDigits="2"/> </strong>
        </p>
    </div>
    <button><a href="/deleteAll">remove all from cart</a> </button>
</fieldset>

<hr/>

<p>There are ${cart.size()} records in your cart</p>

<p>There are ${allQuantity} products</p>

</body>
</html>
