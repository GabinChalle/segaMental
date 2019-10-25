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
		<div class="callout large primary">
			<div class="row column text-center">
				<h1>SegaMental - Login</h1>
			</div>
		</div>
		<div class="row small-5 small-centered">
			<jsp:useBean id="userBean" class="model.UserBean" scope="request"/>
			<c:if test="${ !empty userBean.authResult }">
				<div class="callout alert">
					<p>${ userBean.authResult }</p>
				</div>
			</c:if>
			<!-- connection-->
			<c:if test="${ !userBean.isConnected( pageContext.request ) }">
				<form method="POST" action="/segaMental/login">
					<div class="form-icons">

						<h4>Formulaire de login</h4>
						<div class="input-group">
							<span class="input-group-label">
								<i class="fa fa-user"></i>
							</span>
							<input class="input-group-field" type="text" placeholder="Login" name="form-username"
							       value="${ userBean.user.login }"/>
						</div>
						<div class="input-group">
							<span class="input-group-label">
								<i class="fa fa-key"></i>
							</span>
							<input class="input-group-field" type="password" placeholder="Mot de passe"
							       name="form-password"
							       value=""/>
						</div>
					</div>
					<button class="button expanded">Valider</button>
				</form>
			</c:if>
			<!-- fin de connection-->
			<div class="text-center">
			<h3>OU</h3>
			</div>			<!-- début de Création de compte -->
			<form method="POST" action="/segaMental/signup">
			<input type="hidden" value="${requestScope.currentPerson.id}" name="form-id"/>
			<div class="form-icons">
				<h4>Création d'un compte</h4>
				<div class="input-group">
						<span class="input-group-label">
							<i class="fa fa-user"></i>
						</span>
					<input class="input-group-field" type="text" placeholder="Login" name="form-name"
						   value=""/>
				</div>
				<div class="input-group">
						<span class="input-group-label">
							<i class="fa fa-key"></i>
						</span>
					<input class="input-group-field" type="text" placeholder="password" name="form-password"
						   value=""/>
				</div>
			</div>
			<button class="button expanded">Valider</button>
			</form>
			<!-- fin de création de compte-->

			<form method="POST" action="/segaMental/admin">
				<input type="hidden" name="form-username" value="admin"/>
				<div class="form-icons">
					<div class="input-group">
							<span class="input-group-label">
								<i class="fa fa-key"></i>
							</span>
						<input class="input-group-field" type="password" placeholder="password" name="form-password" value=""/>
					</div>
					<button class="button expanded">Accès Admin</button>
				</div>
			</form>
		</div>
		<script src="${pageContext.request.contextPath}/vendor/foundation-6.5.1/js/vendor/jquery.js"></script>
		<script src="${pageContext.request.contextPath}/vendor/foundation-6.5.1/js/vendor/foundation.min.js"></script>
		<script>
			$(document).foundation();
			document.documentElement.setAttribute('data-useragent', navigator.userAgent);
		</script>
	</body>
</html>
