<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0"/>
    <title>Gestion des Contacts - Détails</title>
    <link rel="stylesheet" type="text/css"
          href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.css"/>
    <link rel="stylesheet" type="text/css"
          href="${pageContext.request.contextPath}/vendor/foundation-6.5.1/css/foundation.min.css"/>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css"/>
</head>
<body>
<div class="callout large primary">
    <div class="row column text-center">
        <h1>Gestion des contacts - Détails</h1>
    </div>
</div>
<div class="row small-5 small-centered">


    <form method="POST">
        <div class="form-icons">
            <h4>Détail de la fiche</h4>
            <div class="input-group">
                            <span class="input-group-label">
                                <i class="fa fa-user"></i>
                            </span>
                <input class="input-group-field" type="text" placeholder="Name" name="form-name"
                       value="${contactBean.currentContact.name}"/>
            </div>
            <div class="input-group">
                            <span class="input-group-label">
                                <i class="fa fa-envelope"></i>
                            </span>
                <input class="input-group-field" type="text" placeholder="Email" name="form-email"
                       value="${contactBean.currentContact.email}"/>
            </div>
            <div class="input-group">
                            <span class="input-group-label">
                                <i class="fa fa-phone"></i>
                            </span>
                <input class="input-group-field" type="text" placeholder="Phone" name="form-phone"
                       value="${contactBean.currentContact.phone}"/>
            </div>
        </div>
        <button class="button expanded">Valider</button>
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
