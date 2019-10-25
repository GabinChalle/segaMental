<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0"/>
    <title>Admin - Liste des utilisateurs</title>
    <link rel="stylesheet" type="text/css"
          href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.css"/>
    <link rel="stylesheet" type="text/css"
          href="${pageContext.request.contextPath}/vendor/foundation-6.5.1/css/foundation.min.css"/>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css"/>
</head>
<body>
<div class="callout large primary">
    <div class="row column text-center">
        <h1>Admin - Liste des utilisateurs</h1>
    </div>
</div>
<jsp:useBean id="contactBean" class="model.UserBean" scope="request"/>
<div class="row small-8 small-centered">
    <table class="unstriped">
        <thead>
        <tr>
            <th>N°</th>
            <th>Id</th>
            <th>Nom</th>
            <th>Password</th>
            <th class="text-center">Actions</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach var="item" items="${contactBean.users}" varStatus="loop">
            <tr>
                <td>${loop.count}</td>
                <td>${item.key}</td>
                <td>${item.value.login}</td>
                <td>${item.value.password}</td>
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
