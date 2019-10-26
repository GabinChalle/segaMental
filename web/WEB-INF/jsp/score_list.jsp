<%--
  Created by IntelliJ IDEA.
  User: Gabin Challe
  Date: 26/10/2019
  Time: 15:30
  To change this template use File | Settings | File Templates.
--%>
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
<div class="callout large primary">
    <div class="row column text-center">
        <h1>Admin - Liste des utilisateurs</h1>
    </div>
</div>
<div class="row small-8 small-centered">
    <table class="unstriped">
        <thead>
        <tr>
            <th>N°</th>
            <th>Id</th>
            <th>Score</th>
            <th class="text-center">Actions</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach var="item" items="${gameBean.operations}" varStatus="loop">
            <tr>
                <td>${loop.count}</td>
                <td>${item.value.id}</td>
                <td>${item.value.score}</td>
                <td class="text-center">
                    <a href="<c:url value="/users/details?id=${item.key}" />">Edit</a>
                    <a href="<c:url value="/users/delete?id=${item.key}" />">Delete</a>
                </td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
    <div class="text-right">
        <a class="button align-right" href="<c:url value="/users/details"/>"><i
                class="fa fa-user-plus"></i> Ajouter</a>
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
