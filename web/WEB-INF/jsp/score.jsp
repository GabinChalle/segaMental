<%@ page import="java.util.ArrayList" %>
<%@ page import="bo.Expression" %>
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
        <h1>SegaMental - Score</h1>
    </div>
</div>
<div class="row small-5 small-centered">
    <jsp:useBean id="gameBean" class="model.GameBean" scope="request"/>
    <%
        ArrayList<Expression> expressions = (ArrayList<Expression>) request.getAttribute("expressions");
        int score = (int) request.getAttribute("score");

    %>
    <div class="row small-8 small-centered">
        <h2>Score : <%= score %></h2>
        <table class="unstriped">
            <thead>
            <tr>
                <th>Calcul</th>
                <th>Résultat donné</th>
                <th>Résultat attendu</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach var="item" items="${expressions}" varStatus="loop">
                <tr>
                    <td>${item.getLibelle()}</td>
                    <td>${item.getResDonnee()}</td>
                    <td>${item.getResAttendu()}</td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
        <form method="post" action="/segaMental/list">
            <button class="button expanded" type="submit"> Voir la liste des scores</button>
        </form>

        <div class="text-right">
            <a class="button align-right" href="<c:url value="/game"/>"><i
                    class="fa fa-user-plus"></i>Jouer une nouvelle partie</a>
        </div>
    </div>
</div>
<script src="${pageContext.request.contextPath}/vendor/foundation-6.5.1/js/vendor/jquery.js"></script>
<script src="${pageContext.request.contextPath}/vendor/foundation-6.5.1/js/vendor/foundation.min.js"></script>
<script>
    $(document).foundation();
    document.documentElement.setAttribute('data-useragent', navigator.userAgent);
</script>
</body>
</html>
