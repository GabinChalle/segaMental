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

	<form method="post" action="/segaMental/test">
		<button class="button expanded" type="submit"> Commencer le test </button>
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
