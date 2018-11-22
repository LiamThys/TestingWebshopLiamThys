<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
    <meta name="viewport" content="width=device-width, initial-scale=1" />
    <meta charset="UTF-8">
    <title>Overview</title>
    <link rel="stylesheet" type="text/css" href="css/style.css">
</head>
<body>
<div id="container">
    <header>
        <h1><span>Web shop</span></h1>

        <jsp:include page="header.jsp"/>

        <h2>
            Product Overview
        </h2>
    </header>
    <main>
        <table>
            <tr>
                <th>Name</th>
                <th>Description</th>
                <th>Price</th>
            </tr>
            <c:forEach var="product" items="${products}">
                <tr>
                    <td><a href="Controller?action=updateForm&productId=${product.productId}">${product.name}</a></td>
                    <td>${product.description}</td>
                    <td>${product.price}</td>
                    <td><a href="Controller?action=deleteForm&productId=${product.productId}">Delete</a></td>
                    <td><a href="Controller?action=addToCart&productId=${product.productId}">Add To Cart</a></td>
                </tr>
            </c:forEach>

            <caption>products Overview</caption>
        </table>
    </main>
    <footer>
        &copy; Webontwikkeling 3, UC Leuven-Limburg
    </footer>
</div>
</body>
</html>