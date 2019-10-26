<%@ page import="org.w3c.dom.ls.LSOutput" %>
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
		<h1> Faites le test </h1>
	</div>
</div>

<div class="row small-5 small-centered">
	<form method="post" action="/segaMental/list">
		<button class="button expanded" type="submit"> Voir la liste des scores </button>
	</form>

	<%
		if(request.getParameter("page") != null){
			if(Integer.parseInt(request.getParameter("page")) > 0){
	%>
		<form method="post" action="/segaMental/test?page=<%= request.getParameter("page")%>">
			<p> Affichage du calcul </p>
			<input type="text" name="result">

			<button class="button expanded" type="submit" name="precedent"> Précédent </button>
			<button class="button expanded" type="submit" name="next"> Suivant </button>
		</form>
	<%
			}else{
	%>
		<form method="post" action="/segaMental/test?page=1">
			<button class="button expanded" type="submit"> Commencer le test </button>
		</form>
	<%
			}
		}
		else{
	%>
		<form method="post" action="/segaMental/test?page=1">
			<button class="button expanded" type="submit"> Commencer le test </button>
		</form>
	<%
		}
	%>



</div>
<script src="${pageContext.request.contextPath}/vendor/foundation-6.5.1/js/vendor/jquery.js"></script>
<script src="${pageContext.request.contextPath}/vendor/foundation-6.5.1/js/vendor/foundation.min.js"></script>
<script>
	$(document).foundation();
	document.documentElement.setAttribute('data-useragent', navigator.userAgent);
</script>
</body>
</html>
