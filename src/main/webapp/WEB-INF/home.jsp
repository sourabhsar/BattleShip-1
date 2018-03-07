<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>BattleShip Game</title>
</head>
<body>
	Lets Battle!!
	<p>Either Upload file or provide input in box.</p>
	<br>
	<c:if test="${not empty exception}">
		<h3 style="color:red">${exception}</h3>
	</c:if>
	<br>
	<table>
		<tr>
			<td>
				<fieldset style="width: fit-content;">
					<legend>Upload Input file here.</legend>
					<c:url value="/uploadFile" var="uploadFile" />
					<form action="${uploadFile}" method="post"
						enctype="multipart/form-data">
						<input type="file" name="file"></input> 
						<input type="submit" value="upload" />
					</form>
				</fieldset>
			</td>
		</tr>
	</table>

	<br />
	<br />
	<fieldset style="width: fit-content;">
		<legend>Provide Input Here</legend>
		<c:url value="/input" var="inputURL" />
		<form action="${inputURL}" method="post">
			<table>
				<tr>
					<td><textarea name="input" cols="20" rows="10"></textarea></td>
				</tr>
				<tr>
					<td><input type="submit"></td>
				</tr>
			</table>
		</form>
	</fieldset>
</body>
</html>