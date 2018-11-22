<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1" />
<title>Home</title>
<link rel="stylesheet" type="text/css" href="css/style.css">
</head>
<body>
	<div id="container">
		<header>
			<h1>
				<span>Web shop</span>
			</h1>

			<jsp:include page="header.jsp"/>

			<h2>Home</h2>

		</header>
		<main> Sed ut perspiciatis unde omnis iste natus error sit
		voluptatem accusantium doloremque laudantium, totam rem aperiam, eaque
		ipsa quae ab illo inventore veritatis et quasi architecto beatae vitae
		dicta sunt explicabo. Nemo enim ipsam voluptatem quia voluptas sit
		aspernatur aut odit aut fugit, sed quia consequuntur magni dolores eos
		qui ratione voluptatem sequi nesciunt. </main>

		<form method="post" action="Controller?action=setChoice" novalidate="novalidate">
			<input type="radio" name="choice" value="yes" ${choice.getValue().equals("yes") ? 'checked="checked"' : ''}> Yes <br>
			<input type="radio" name="choice" value="no" ${choice.getValue().equals("yes") ? '' : 'checked="checked"'}> No <br>
			<%--<input type="radio" name="choice" value="yes" > Yes <br>
			<input type="radio" name="choice" value="no" checked="checked"> No <br> --%>
			<input type="submit" id="send" value="Send">
		</form>

		<form method="post" action="Controller?action=indexhandler" novalidate="novalidate"><input type="submit" id="refresh" value="Refresh"></form>

		<c:if test="${choice!=null}">
			<p>${quoteList}</p>
		</c:if>

		<footer> &copy; Webontwikkeling 3, UC Leuven-Limburg </footer>
	</div>
</body>
</html>