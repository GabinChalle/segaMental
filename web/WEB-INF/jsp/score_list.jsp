<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Liste des scores</title>
</head>
<body>
<jsp:useBean id="gameBean" class="model.GameBean" scope="request"/>
<div class="callout large primary">
    <div class="row column text-center">
        <h1>Liste des scores</h1>
    </div>
</div>
<div class="row small-8 small-centered">
    <table class="unstriped">
        <thead>
        <tr>
            <th>N°</th>
            <th>Id</th>
            <th>Score</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach var="item" items="${gameBean.operations}" varStatus="loop">
            <tr>
                <td>${loop.count}</td>
                <td>${item.value.id}</td>
                <td>${item.value.score}</td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
    <div class="text-right">
        <a class="button align-right" href="<c:url value="/game"/>"><i
                class="fa fa-user-plus"></i>Jouer une nouvelle partie</a>
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
