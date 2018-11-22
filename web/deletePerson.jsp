<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta name="viewport" content="width=device-width, initial-scale=1" />
    <meta charset="UTF-8">
    <title>Sign Up</title>
    <link rel="stylesheet" type="text/css" href="css/style.css">
</head>
<body>
<div id="container">
    <header>
        <h1><span>Web shop</span></h1>

        <jsp:include page="header.jsp"/>

        <h2>
            Sign Up
        </h2>

    </header>
    <main>
        <form method="post" action="Controller?action=deletePerson" novalidate="novalidate">
            <input type="hidden" id="userid" name="userid" value="${userid}">
            <p><input type="submit" id="deletePerson" value="Delete"></p>
            <p><input type="submit" formaction="Controller?action=personoverview" id="cancel" value="Cancel"></p>
        </form>
    </main>
    <footer>
        &copy; Webontwikkeling 3, UC Leuven-Limburg
    </footer>
</div>
</body>
</html>
