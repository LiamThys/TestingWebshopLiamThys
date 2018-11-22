<%@ page import="java.util.Map" %>
<%@ page import="java.util.HashMap" %>
<%@ page import="domain.model.Product" %>
<%@ page import="java.util.Iterator" %>
<%@ page import="domain.model.ShopService" %>
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
            My Cart
        </h2>
    </header>
    <main>
        <table>
            <tr>
                <th>Name</th>
                <th>Description</th>
                <th>Price</th>
            </tr>
            <%
                Map<Integer, Integer> mp =
                        (HashMap<Integer, Integer>)session.getAttribute("list");
                ShopService serv = (ShopService)request.getAttribute("service");
                try{
                    Iterator it = mp.entrySet().iterator();
                    while (it.hasNext()) {
                        HashMap.Entry pair = (HashMap.Entry)it.next();
                        int productId = (int) pair.getKey();
                        Product prod = serv.getProduct(productId);
                        int amount = (int) pair.getValue();

            %>
            <tr>
                <td><%=productId %></td>
                <td><%=prod.getName() %></td>
                <td><%=prod.getDescription() %></td>
                <td><%=prod.getPrice()%></td>
                <td><%=amount %></td>
                <td><a href="Controller?action=deleteFromCart&id=<%=productId %>">delete from cart</a></td>
                <br>
            </tr>
            <%
                    }
                }catch (Exception e){
                    e.printStackTrace();
                }%>
        </table>
    </main>
    <footer> &copy; Webontwikkeling 3, UC Leuven-Limburg </footer>
</div>
</body>
</html>