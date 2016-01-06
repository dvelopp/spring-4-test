<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <c:import url="myAppJavaScripts.jsp"/>
    <script src="/resources/scripts/homeController.js"></script>
</head>
<body ng-app="myApp">
    <div ng-controller="HomeController as homeCtrl">
        <table>
            <td></td>
        </table>
        <ul class="jquery-menu">
            <li ng-show="model.hasUserViewAccess">
                <c:url value="user/list" var="userListUrl"/>
                <a href="${userListUrl}">Users</a>
            </li>
            <li>
                <c:url value="logout" var="logoutUrl"/>
                <a href="${logoutUrl}">Logout</a>
            </li>
        </ul>
    </div>

</body>
</html>