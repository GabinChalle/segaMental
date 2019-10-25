<!DOCTYPE html>
<html lang="en">
	<head>
		<meta charset="UTF-8">
		<meta name="viewport" content="width=device-width, initial-scale=1.0"/>
		<title>SegaMental</title>
		<link rel="stylesheet" type="text/css"
		      href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.css"/>
		<link rel="stylesheet" type="text/css"
		      href="${pageContext.request.contextPath}/vendor/foundation-6.5.1/css/foundation.min.css"/>
		<link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css"/>
	</head>
	<body>
	<jsp:useBean id="userBean" class="model.UserBean" scope="request"/>
	<jsp:useBean id="gameBean" class="model.GameBean" scope="request"/>
		<div class="callout large primary">
			<div class="row column text-center">
				<h1>Page de lancement du jeu</h1>
			</div>
		</div>
		<div class="row small-5 small-centered">
			<form action="${pageContext.request.contextPath}/game" method="post">
				<button type="submit" name="start" value="start">Commencer le jeu</button>
			</form>

			<form action="${pageContext.request.contextPath}/game/${id}" method="post">
				<h2>${gameBean.getBinaryExp()} =</h2>
				<input type="text" name="result">
				<button type="submit" name="next" value="next">Prochain</button>
			</form>

		</div>
	</body>
</html>
