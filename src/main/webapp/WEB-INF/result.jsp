<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>BattleShip Game</title>
</head>
<body>
	<center>
		<c:if test="${not empty result}">
		<h2>${result}</h2>
		</c:if>
		<c:url value="/" var="playAgain" />
		<a href="${playAgain}">Play Again</a> 
	</center>
</body>
</html>